package com.lmw.common.net.observer;

import com.lmw.base.model.MvvmBaseModel;
import com.lmw.base.model.MvvmNetworkObserver;
import com.lmw.common.net.base.exception.ExceptionHandle;
import com.lmw.common.net.pojo.no.Test1BaseResponseNo;
import com.lmw.lmwnetwork.lib.core.BaseObserver;

import io.reactivex.disposables.Disposable;

public class TencentObserver<T extends Test1BaseResponseNo> extends BaseObserver<T> {
    MvvmBaseModel baseModel;
    MvvmNetworkObserver<T> mvvmNetworkObserver;

    public TencentObserver(MvvmBaseModel baseModel, MvvmNetworkObserver<T> mvvmNetworkObserver) {
        this.baseModel = baseModel;
        this.mvvmNetworkObserver = mvvmNetworkObserver;
    }

    @Override
    public void onError(Throwable e) {
        mvvmNetworkObserver.onFailure(e);
    }

    @Override
    public void onNext(T t) {
        try {
            mvvmNetworkObserver.onSuccess(t, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (baseModel != null) {
            baseModel.addDisposable(d);
        }
    }

    @Override
    public void onComplete() {
    }
}
