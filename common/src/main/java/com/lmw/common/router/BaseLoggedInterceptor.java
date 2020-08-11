package com.lmw.common.router;

import com.lmw.lmwrouter.lib.Docker;
import com.lmw.lmwrouter.lib.interceptor.BaseRouterInterceptor;

import java.util.ArrayList;

/**
 * <p>
 * 用户未登入拦截
 */

public class BaseLoggedInterceptor implements BaseRouterInterceptor {
    //黑名单
    private static ArrayList<String> mBlackList = new ArrayList<>();

    static {
    }

    @Override
    public boolean intercept(Chain chain) throws IndexOutOfBoundsException {

        Docker docker = chain.getDocker();
        //过滤黑名单

        return chain.proceed(docker);
    }
}
