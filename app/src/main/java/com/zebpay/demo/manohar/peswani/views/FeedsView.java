package com.zebpay.demo.manohar.peswani.views;

import com.zebpay.demo.manohar.peswani.database.entity.MarketInfo;
import com.zebpay.demo.manohar.peswani.database.entity.ZebPayFeed;

import java.util.List;

/**
 * Created by Manohar on 04-08-2017.
 */

public interface FeedsView {

    void displayFeeds(List<ZebPayFeed> zebPayFeeds);

    void handleEmptyList();

    void wait(boolean wait);

    void showMarketValue(MarketInfo marketInfo);

    void noMarketValue();
}
