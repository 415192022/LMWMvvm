package com.lmw.lmwmvvm.router;

import android.content.Intent;
import android.os.Bundle;

import com.lmw.lmwmvvm.main.MainActivity;
import com.lmw.lmwrouter.lib.LMWRouter;
import com.lmw.lmwrouter.lib.util.Utils;


public class LMWRouterUtils {


    public static LMWRouterUtils getInstance() {
        return Holder.mInstance;
    }


    public void startSample() {
        LMWRouter.getInstance().startActivity(MainActivity.class);
    }


    public void startDetail(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
    }

    /**
     * 一次打开多个Activity
     *
     * @param intents
     */
    public void startActivities(Intent[] intents) {
        Bundle bundle = new Bundle();
        LMWRouter.getInstance().startActivities(intents, Utils.getApp(), bundle);
    }


    private static class Holder {
        private static LMWRouterUtils mInstance = new LMWRouterUtils();
    }


}
