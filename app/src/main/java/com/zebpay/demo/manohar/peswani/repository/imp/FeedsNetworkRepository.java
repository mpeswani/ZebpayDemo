package com.zebpay.demo.manohar.peswani.repository.imp;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.zebpay.demo.manohar.peswani.database.entity.MarketInfo;
import com.zebpay.demo.manohar.peswani.database.entity.ZebPayFeed;
import com.zebpay.demo.manohar.peswani.network.RetrofitService;
import com.zebpay.demo.manohar.peswani.network.ZebpayNetworkService;
import com.zebpay.demo.manohar.peswani.repository.FeedsRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Manohar on 04-08-2017.
 */

public class FeedsNetworkRepository implements FeedsRepository {

    private ZebpayNetworkService zebpayNetworkService;

    public FeedsNetworkRepository(Context context) {
        zebpayNetworkService = RetrofitService.getInstance().create(ZebpayNetworkService.class);
    }

    @Override
    public Single<List<ZebPayFeed>> getFeeds() {
        return zebpayNetworkService.getFeeds().
                flatMap(feeds -> Single.fromCallable(feeds::getZebPayFeeds));
    }

    public Single<MarketInfo> getMarketInfo() {
        return zebpayNetworkService.getMarketInfo(RetrofitService.URL_MARKET);
    }

    @Override
    public Single<List<Long>> insertFeeds(ZebPayFeed... zebPayFeeds) {
        return null;
    }

    @Override
    public Single<Integer> deleteFeeds(ZebPayFeed... zebPayFeeds) {
        return null;
    }

    public class Feeds {
        @SerializedName("activityFeed")
        List<ZebPayFeed> zebPayFeeds;

        public List<ZebPayFeed> getZebPayFeeds() {
            return zebPayFeeds;
        }

        public void setZebPayFeeds(List<ZebPayFeed> zebPayFeeds) {
            this.zebPayFeeds = zebPayFeeds;
        }
    }

}
