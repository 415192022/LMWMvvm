package com.lmw.common.config;

import androidx.annotation.Nullable;

import com.lmw.base.app.BaseApplication;


public class ModuleInitialize {

    public static final String COMMON = "com.lmw.common.CommonInitialize";


    public static String[] initModuleNames = {COMMON};


    private ModuleInitialize() {

    }

    private static class SingleHolder {
        private static ModuleInitialize instance =
                new ModuleInitialize();
    }

    public static ModuleInitialize getInstance() {
        return SingleHolder.instance;
    }

    public void init1(@Nullable BaseApplication application) {
        for (String moduleName : initModuleNames) {
            try {
                Class<?> clazz = Class.forName(moduleName);
                IInit init = (IInit) clazz.newInstance();
                init.onInit1(application);
            } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    public void init2(@Nullable BaseApplication application) {
        for (String moduleName : initModuleNames) {
            try {
                Class<?> clazz = Class.forName(moduleName);
                IInit init = (IInit) clazz.newInstance();
                // 调用初始化方法
                init.onInit2(application);
            } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
