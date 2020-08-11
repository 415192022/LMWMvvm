package com.lmw.common.net.base;


import android.app.Application;

import com.lmw.base.app.BaseApplication;
import com.lmw.lmwnetwork.lib.core.INetworkRequiredInfo;

public class NetworkRequestInfo implements INetworkRequiredInfo {
    private BaseApplication mApplication;

    public NetworkRequestInfo(BaseApplication application) {
        this.mApplication = application;
    }


    @Override
    public String getAppVersionName() {
        return null;
    }

    @Override
    public String getAppVersionCode() {
        return null;
    }

    @Override
    public boolean isDebug() {
        return mApplication.isDebug();
    }

    @Override
    public Application getApplicationContext() {
        return mApplication;
    }

}
