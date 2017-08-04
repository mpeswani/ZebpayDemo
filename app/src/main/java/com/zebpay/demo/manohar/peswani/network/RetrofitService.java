package com.zebpay.demo.manohar.peswani.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Manohar on 04-08-2017.
 */

public class RetrofitService {

    private static Retrofit retrofit;

    public static final String URL_MARKET = "https://api.zebpay.com/api/v1/ticker?currencyCode=INR";
    private static final String BASE_URL_FEEDS = "https://www.zebapi.com/api/v1/";

    private RetrofitService() {

    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_FEEDS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();
            return retrofit;
        }
        return retrofit;
    }
}
