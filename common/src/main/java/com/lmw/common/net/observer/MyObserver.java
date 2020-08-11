package com.lmw.common.net.observer;

import com.lmw.common.net.pojo.no.BaseNo;
import com.lmw.lmwnetwork.lib.core.BaseObserver;

import io.reactivex.disposables.Disposable;

public abstract class MyObserver<T extends BaseNo> extends BaseObserver<T> {
    @Override
    public void onError(Throwable e) {
        onFail(null, e);
        if (e != null) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNext(T t) {
        try {
            if (t != null && t.getReturnCode() == 0) {
                onSuccess(t);
            } else {
                onFail(t, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onComplete() {
    }

    abstract public void onSuccess(T t);

    abstract public void onFail(T t, Throwable e);
}
