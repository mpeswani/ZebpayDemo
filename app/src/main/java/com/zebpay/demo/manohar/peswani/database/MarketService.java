package com.zebpay.demo.manohar.peswani.database;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

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
    }
}
