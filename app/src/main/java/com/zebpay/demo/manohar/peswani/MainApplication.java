package com.zebpay.demo.manohar.peswani;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.zebpay.demo.manohar.peswani.database.AppDatabase;

/**
 * Created by Manohar on 04-08-2017.
 */

public class MainApplication extends Application {

    private static AppDatabase db;

    public static AppDatabase getDatabase(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        }
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getDatabase(this);
    }
}
