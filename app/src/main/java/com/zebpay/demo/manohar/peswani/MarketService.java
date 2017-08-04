package com.zebpay.demo.manohar.peswani;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.zebpay.demo.manohar.peswani.database.entity.MarketInfo;
import com.zebpay.demo.manohar.peswani.database.entity.ZebPayFeed;
import com.zebpay.demo.manohar.peswani.presenter.ZebPayPresenter;
import com.zebpay.demo.manohar.peswani.repository.imp.FeedsDatabaseRepository;
import com.zebpay.demo.manohar.peswani.repository.imp.FeedsNetworkRepository;
import com.zebpay.demo.manohar.peswani.views.FeedsView;

import java.util.List;

/**
 * Created by Manohar on 04-08-2017.
 */

public class MarketService extends IntentService implements FeedsView {

    private ZebPayPresenter mPresenter;

    public MarketService() {
        super("hello");
        mPresenter = new ZebPayPresenter(this, new FeedsDatabaseRepository(this),
                new FeedsNetworkRepository(this));
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mPresenter.getMarketInfo();
    }

    @Override
    public void displayFeeds(List<ZebPayFeed> studentList) {

    }

    @Override
    public void handleEmptyList() {

    }

    @Override
    public void wait(boolean wait) {

    }

    @Override
    public void showMarketValue(MarketInfo marketInfo) {
        LocalBroadcastManager.getInstance(this).sendBroadcast
                (new Intent("action").putExtra("marketInfo", marketInfo));
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String value = preferences.getString(UserPreference.VARIABLE_VALUE, "");
        if (!TextUtils.isEmpty(value)) {
            String valueSaved = preferences.getString(UserPreference.SAVED_VALUE, "");
            boolean isPercent = preferences.getBoolean(UserPreference.VALUE_TYPE, false);
            double variable = Double.parseDouble(value);
            double savedValue = Double.parseDouble(valueSaved.split(" ")[0]);
            double currentValue = Double.parseDouble(marketInfo.getMarket());
            if (isPercent) {
                double percentChange = ((savedValue - currentValue) / savedValue) * 100;
                if (Math.abs(percentChange) >= Math.abs(variable)) {
                    showNotification(percentChange + "");
                    preferences.edit().putString(UserPreference.SAVED_VALUE,
                            marketInfo.getMarket()).apply();
                }
            } else {
                double change = savedValue - currentValue;
                if (Math.abs(change) >= Math.abs(variable)) {
                    showNotification(change + " INR");
                    preferences.edit().putString(UserPreference.SAVED_VALUE,
                            marketInfo.getMarket()).apply();
                }
            }
        }
    }

    private void showNotification(String change) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_info_outline_white_24dp)
                        .setContentTitle("ZebPay")
                        .setContentText("Bitcoin value has been changed " + change)
                        .setSound(uri);
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        int mNotificationId = 100;
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

    }
}
