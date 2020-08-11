package com.lmw.lmwmvvm.app;

import com.lmw.base.app.BaseApplication;
import com.lmw.common.config.ModuleInitialize;
import com.lmw.lmwmvvm.BuildConfig;

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setsDebug(BuildConfig.DEBUG);
        ModuleInitialize.getInstance().init1(this);
    }
}
