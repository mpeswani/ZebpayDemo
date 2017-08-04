package com.zebpay.demo.manohar.peswani.repository;

import com.zebpay.demo.manohar.peswani.database.entity.MarketInfo;
import com.zebpay.demo.manohar.peswani.database.entity.ZebPayFeed;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Manohar on 04-08-2017.
 */

public interface FeedsRepository {

    Single<List<ZebPayFeed>> getFeeds();
    Single<List<Long>> insertFeeds(ZebPayFeed... zebPayFeeds);
    Single<Integer> deleteFeeds(ZebPayFeed... zebPayFeeds);
    Single<MarketInfo> getMarketInfo();
}
