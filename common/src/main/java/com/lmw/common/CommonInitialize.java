package com.lmw.common;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.lmw.base.app.BaseApplication;
import com.lmw.common.config.IInit;
import com.lmw.common.mmkv.MmkvHelper;
import com.lmw.common.net.base.NetworkRequestInfo;
import com.lmw.common.net.base.exception.ExceptionHandle;
import com.lmw.common.net.base.interceptor.CommonRequestInterceptor;
import com.lmw.common.net.base.interceptor.CommonResponseInterceptor;
import com.lmw.common.router.BaseLoggedInterceptor;
import com.lmw.lmwnetwork.lib.core.BaseNetworkApi;
import com.lmw.lmwrouter.lib.LMWRouter;
import com.lmw.lmwrouter.lib.interceptor.BaseRouterInterceptor;
import com.lmw.lmwrouter.lib.interceptor.RealJumpInterceptor;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

public class CommonInitialize implements IInit {
    @Override
    public boolean onInit1(BaseApplication application) {
        init(application);
        return false;
    }

    @Override
    public boolean onInit2(BaseApplication application) {
        return false;
    }


    private void init(BaseApplication application) {
        initNetwork(application);
        initRouter(application);
        initMMKV(application);
    }


    private void initMMKV(Application application) {
        MMKV.initialize(application);
    }

    private void initNetwork(BaseApplication application) {
        List<Interceptor> interceptorList = new ArrayList<>();
        NetworkRequestInfo requestInfo = new NetworkRequestInfo(application);
        CommonRequestInterceptor commonRequestInterceptor = new CommonRequestInterceptor(requestInfo);
        CommonResponseInterceptor commonResponseInterceptor = new CommonResponseInterceptor();
        interceptorList.add(commonRequestInterceptor);
        interceptorList.add(commonResponseInterceptor);

        ExceptionHandle exceptionHandle = new ExceptionHandle();
        BaseNetworkApi.init(interceptorList, requestInfo, exceptionHandle);
    }

    private void initRouter(Application application) {
        ArrayList interceptors = new ArrayList<BaseRouterInterceptor>();
        interceptors.add(new RealJumpInterceptor());
        interceptors.add(new BaseLoggedInterceptor());
        LMWRouter.getInstance().setInterceptors(application, interceptors);
    }
}
