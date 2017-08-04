package com.zebpay.demo.manohar.peswani.presenter;

import android.util.Log;

import com.zebpay.demo.manohar.peswani.database.entity.MarketInfo;
import com.zebpay.demo.manohar.peswani.database.entity.ZebPayFeed;
import com.zebpay.demo.manohar.peswani.repository.imp.FeedsDatabaseRepository;
import com.zebpay.demo.manohar.peswani.repository.imp.FeedsNetworkRepository;
import com.zebpay.demo.manohar.peswani.views.FeedsView;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Manohar on 04-08-2017.
 */

public class ZebPayPresenter extends BasePresenterImpl implements BasePresenter {

    private final FeedsView view;
    private final FeedsDatabaseRepository mDatabaseRepository;
    private final FeedsNetworkRepository mNetworkRepository;

    public ZebPayPresenter(FeedsView view, FeedsDatabaseRepository repository,
                           FeedsNetworkRepository mNetworkRepository) {
        this.view = view;
        this.mDatabaseRepository = repository;
        this.mNetworkRepository = mNetworkRepository;
    }

    @Override
    public void subscribe() {

    }

    public void saveFeeds(ZebPayFeed... zebPayFeeds) {
        mDisposable.add(mDatabaseRepository.insertFeeds(zebPayFeeds).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DisposableSingleObserver<List<Long>>() {
                    @Override
                    public void onSuccess(@NonNull List<Long> longs) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }

    public void getMarketInfo() {
        mDisposable.add(mNetworkRepository.getMarketInfo().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DisposableSingleObserver<MarketInfo>() {
                    @Override
                    public void onSuccess(@NonNull MarketInfo marketInfo) {
                        view.showMarketValue(marketInfo);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.v("error", e.getMessage());
                    }
                }));
    }

    public void getFeeds(boolean isOnline) {
        Single<List<ZebPayFeed>> feeds;
        if (isOnline) {
            feeds = mNetworkRepository.getFeeds();
        } else {
            feeds = mDatabaseRepository.getFeeds();
        }
        mDisposable.add(feeds.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DisposableSingleObserver<List<ZebPayFeed>>() {
                    @Override
                    public void onSuccess(@NonNull List<ZebPayFeed> zebPayFeeds) {
                        if (zebPayFeeds.isEmpty()) {
                            view.handleEmptyList();
                        } else {
                            ZebPayFeed[] payFeeds = new ZebPayFeed[zebPayFeeds.size()];
                            saveFeeds(zebPayFeeds.toArray(payFeeds));
                            view.displayFeeds(zebPayFeeds);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.v("error", e.getMessage());
                    }
                }));
    }

    public void deleteFeeds(ZebPayFeed... zebPayFeeds) {
        mDatabaseRepository.deleteFeeds(zebPayFeeds).subscribeOn(Schedulers.io());
    }
}
