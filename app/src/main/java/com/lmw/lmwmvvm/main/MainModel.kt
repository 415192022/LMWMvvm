package com.lmw.lmwmvvm.main

import androidx.lifecycle.MutableLiveData
import com.lmw.base.model.MvvmBaseModel
import com.lmw.common.net.base.retrofit.Test1NetworkApi
import com.lmw.common.net.observer.TencentObserver
import com.lmw.common.net.pojo.no.Test1BaseResponseNo
import com.lmw.lmwmvvm.net.NewsApiInterface
import com.lmw.lmwmvvm.pojo.bo.UserLoginBo
import java.util.*

class MainModel : MvvmBaseModel<Test1BaseResponseNo, ArrayList<UserLoginBo>>(
    Test1BaseResponseNo::class.java, false, ""
) {

    var userLoginBo = UserLoginBo()

    override fun onSuccess(t: Test1BaseResponseNo?, isFromCache: Boolean) {
        var users: ArrayList<UserLoginBo> = arrayListOf()
        userLoginBo?.userId = "" + Random().nextInt(1000)
        userLoginBo?.isLoadSuccess = true
        users.add(userLoginBo)
        loadSuccess(t, users, false)
    }

    override fun onFailure(e: Throwable?) {
    }

    override fun refresh() {
        isRefresh = true
        load()
    }

    override fun load() {
        Test1NetworkApi.getService(NewsApiInterface::class.java)
            .getNewsList("", "", "")
            .compose(
                Test1NetworkApi.getInstance().applySchedulers(object :
                    TencentObserver<Test1BaseResponseNo>(this, this) {})
            )
    }

    fun loadNexPage() {
        isRefresh = false
        load()
    }

    fun init(): MainModel? {
        return this
    }

}