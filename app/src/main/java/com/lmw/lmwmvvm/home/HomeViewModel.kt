package com.lmw.lmwmvvm.home

import androidx.databinding.ObservableInt
import com.lmw.base.customview.BaseCustomViewModel
import com.lmw.base.viewmodel.MvvmBaseViewModel

class HomeViewModel : MvvmBaseViewModel<HomeModel, BaseCustomViewModel>() {

    fun init(): HomeViewModel {
        model = HomeModel()
        model.register(this)
        return this
    }

    fun tryToLoadNextPage() {
        model.loadNexPage()
    }


    enum class Popularity {
        NORMAL,
        POPULAR,
        STAR
    }

    private fun ObservableInt.increment() {
        set(get() + 1)
    }

}