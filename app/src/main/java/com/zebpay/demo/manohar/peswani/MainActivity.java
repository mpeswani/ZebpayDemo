package com.zebpay.demo.manohar.peswani;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zebpay.demo.manohar.peswani.database.entity.MarketInfo;
import com.zebpay.demo.manohar.peswani.database.entity.ZebPayFeed;
import com.zebpay.demo.manohar.peswani.presenter.ZebPayPresenter;
import com.zebpay.demo.manohar.peswani.repository.imp.FeedsDatabaseRepository;
import com.zebpay.demo.manohar.peswani.repository.imp.FeedsNetworkRepository;
import com.zebpay.demo.manohar.peswani.ui.adapter.HomeScreenAdapter;
import com.zebpay.demo.manohar.peswani.util.ZebUtil;
import com.zebpay.demo.manohar.peswani.views.FeedsView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FeedsView {

    private ZebPayPresenter mPresenter;
    private HomeScreenAdapter mAdapter;
    private List<ZebPayFeed> mFeeds;
    private TextView buy;
    private TextView sell;
    private TextView market;
    private TextView volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new ZebPayPresenter(this, new FeedsDatabaseRepository(this),
                new FeedsNetworkRepository(this));
        RecyclerView mRecyclerView = findViewById(R.id.list);
        mFeeds = new ArrayList<>();
        mAdapter = new HomeScreenAdapter(R.layout.list_item, mFeeds);
        mAdapter.bindToRecyclerView(mRecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter.setEmptyView(R.layout.empty_view);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getFeeds(ZebUtil.isOnline(this));
        mPresenter.getMarketInfo();
        setHeaderView();
        setAlarm();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("action"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            MarketInfo marketInfo = intent.getParcelableExtra("marketInfo");
            showMarketValue(marketInfo);
            Toast.makeText(MainActivity.this, "Refreshed.", Toast.LENGTH_SHORT).show();
        }
    };


    private void setAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this, MarketService.class);
        PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(),
                5 * 60 * 1000,
                pendingIntent);
    }

    private void setHeaderView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_view, null, false);
        buy = headerView.findViewById(R.id.buy);
        sell = headerView.findViewById(R.id.sell);
        market = headerView.findViewById(R.id.market);
        volume = headerView.findViewById(R.id.volume);
        mAdapter.addHeaderView(headerView);

    }

    @Override
    public void displayFeeds(List<ZebPayFeed> feeds) {
        mFeeds.clear();
        mFeeds.addAll(feeds);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void handleEmptyList() {

    }

    @Override
    public void wait(boolean wait) {

    }

    @Override
    public void showMarketValue(MarketInfo marketInfo) {
        buy.setText("buy 1B =" + marketInfo.getBuy() + " INR");
        sell.setText("sell 1B =" + marketInfo.getSell() + " INR");
        market.setText(marketInfo.getMarket() + " INR");
        volume.setText(marketInfo.getVolume() + " B");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                mPresenter.getMarketInfo();
                Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.set_divation:
                UserPreference.showChangeLangDialog(this, market.getText().toString());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
