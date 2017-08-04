package com.zebpay.demo.manohar.peswani.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.zebpay.demo.manohar.peswani.database.dao.ZebpayFeedDao;
import com.zebpay.demo.manohar.peswani.database.entity.ZebPayFeed;

/**
 * Created by Manohar on 04-08-2017.
 */

@Database(entities = {ZebPayFeed.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    /**
     * Name of database
     */
    public static final String DATABASE_NAME = "zebpay";
    /**
     * Database version
     */
    public static final int DATABASE_VERSION = 1;

    public abstract ZebpayFeedDao zebPayFeedDao();


}
