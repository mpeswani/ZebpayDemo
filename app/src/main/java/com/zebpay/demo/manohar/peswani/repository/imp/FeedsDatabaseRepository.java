package com.zebpay.demo.manohar.peswani.repository.imp;

import android.content.Context;

import com.zebpay.demo.manohar.peswani.MainApplication;
import com.zebpay.demo.manohar.peswani.database.dao.ZebpayFeedDao;
import com.zebpay.demo.manohar.peswani.database.entity.MarketInfo;
import com.zebpay.demo.manohar.peswani.database.entity.ZebPayFeed;
import com.zebpay.demo.manohar.peswani.repository.FeedsRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Manohar on 04-08-2017.
 */

public class FeedsDatabaseRepository implements FeedsRepository{

    private ZebpayFeedDao mZebpayFeedDao;

    public FeedsDatabaseRepository(Context context) {
        mZebpayFeedDao = MainApplication.getDatabase(context).zebPayFeedDao();
    }

    @Override
    public Single<List<ZebPayFeed>> getFeeds() {
        return Single.fromCallable(() -> mZebpayFeedDao.getZebPayFeeds());
    }

    @Override
    public Single<Integer> deleteFeeds(ZebPayFeed... zebPayFeeds) {
        return Single.fromCallable(() -> mZebpayFeedDao.delete(zebPayFeeds));
    }

    @Override
    public Single<MarketInfo> getMarketInfo() {
        return null;
    }

    @Override
    public Single<List<Long>> insertFeeds(ZebPayFeed... zebPayFeeds) {
        return Single.fromCallable(() -> mZebpayFeedDao.insertAll(zebPayFeeds));
    }
}
