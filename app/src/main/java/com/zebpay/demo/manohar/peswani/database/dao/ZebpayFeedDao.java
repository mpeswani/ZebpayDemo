package com.zebpay.demo.manohar.peswani.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zebpay.demo.manohar.peswani.database.entity.ZebPayFeed;

import java.util.List;

/**
 * Created by Manohar on 04-08-2017.
 */

@Dao
public interface ZebpayFeedDao {

    @Query("SELECT * FROM zebpay_feed")
    List<ZebPayFeed> getZebPayFeeds();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(ZebPayFeed... zebPayFeeds);

    @Delete
    int delete(ZebPayFeed... zebPayFeeds);
}
