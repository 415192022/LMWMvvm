package com.lmw.lmwmvvm.guide

import android.graphics.drawable.Drawable
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.Utils
import com.lmw.base.customview.BaseCustomViewModel
import com.lmw.base.viewmodel.MvvmBaseViewModel
import com.lmw.lmwmvvm.pojo.bo.BannerBo
import com.lmw.lmwmvvm.pojo.bo.UserLoginBo
import java.util.ArrayList

class GuideViewModel : MvvmBaseViewModel<GuideModel, UserLoginBo>() {
    var textData: MutableLiveData<UserLoginBo> = MutableLiveData()

    var mDrawableList: MutableLiveData<ArrayList<Drawable>> = MutableLiveData()
    var mStrList: MutableLiveData<Array<String>> = MutableLiveData()
    var mBannerBoList: MutableLiveData<ArrayList<BannerBo>> = MutableLiveData()


    fun init(): GuideViewModel {
        model = GuideModel()
        model.register(this)
        mStrList?.value = arrayOf("在这里\n你可以听到周围人的心声", "在这里\nTA会在下一秒遇见你", "在这里\n不再错过可以改变你一生的人")
        return this
    }

    fun initDrawable(drawables: ArrayList<Drawable>): GuideViewModel {
        drawables?.map {
            mDrawableList?.value?.add(it)
        }
        return this
    }
}