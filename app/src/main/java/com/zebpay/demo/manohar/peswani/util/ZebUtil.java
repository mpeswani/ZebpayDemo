package com.zebpay.demo.manohar.peswani.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Manohar on 04-08-2017.
 */

public class ZebUtil {

    private ZebUtil(){

    }

    public static boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null
                && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
