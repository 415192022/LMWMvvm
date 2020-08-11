package com.lmw.lmwmvvm.main

import com.lmw.base.viewmodel.MvvmBaseViewModel
import com.lmw.lmwmvvm.pojo.bo.UserLoginBo

class MainViewModel : MvvmBaseViewModel<MainModel, UserLoginBo>() {

    fun init(): MainViewModel {
        model = MainModel()
        model.register(this)
        dataList?.value?.add(UserLoginBo())
        return this
    }

    fun changeValue() {
        model?.loadNexPage()
    }
}