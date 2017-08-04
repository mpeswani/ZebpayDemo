package com.zebpay.demo.manohar.peswani.network;

import com.zebpay.demo.manohar.peswani.database.entity.MarketInfo;
import com.zebpay.demo.manohar.peswani.repository.imp.FeedsNetworkRepository;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Manohar on 04-08-2017.
 */

public interface ZebpayNetworkService {

    @GET("feed")
    Single<FeedsNetworkRepository.Feeds> getFeeds();

    @GET
    Single<MarketInfo> getMarketInfo(@Url String url);

}
