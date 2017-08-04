package com.zebpay.demo.manohar.peswani.presenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Manohar on 04-08-2017.
 */

public abstract class BasePresenterImpl implements BasePresenter {
    protected CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    public void unsubscribe() {
        mDisposable.clear();
    }
}
