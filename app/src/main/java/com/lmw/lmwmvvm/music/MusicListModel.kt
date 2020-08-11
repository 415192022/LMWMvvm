package com.lmw.lmwmvvm.music

import com.lmw.base.customview.BaseCustomViewModel
import com.lmw.base.model.MvvmBaseModel
import com.lmw.common.net.base.retrofit.Test1NetworkApi
import com.lmw.common.net.observer.TencentObserver
import com.lmw.common.net.pojo.no.Test1BaseResponseNo
import com.lmw.lmwmvvm.net.NewsApiInterface
import com.lmw.lmwmvvm.pojo.bo.GuideBo
import java.util.*

class MusicListModel : MvvmBaseModel<Test1BaseResponseNo, ArrayList<BaseCustomViewModel>>(
    Test1BaseResponseNo::class.java, false, ""
) {


    override fun onSuccess(t: Test1BaseResponseNo?, isFromCache: Boolean) {
        var guideBo = GuideBo()
        var baseViewModels: ArrayList<BaseCustomViewModel> =
            ArrayList<BaseCustomViewModel>()


        if (isRefresh) {
            guideBo = GuideBo()
            guideBo?.jumpUri = "11111111"
            baseViewModels.add(guideBo)
            baseViewModels.add(guideBo)
            baseViewModels.add(guideBo)
        } else {
            guideBo?.jumpUri = "22222222"
            baseViewModels.add(guideBo)
        }
        loadSuccess(t, baseViewModels, false)
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


}