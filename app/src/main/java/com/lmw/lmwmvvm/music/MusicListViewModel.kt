package com.lmw.lmwmvvm.music

import androidx.databinding.ObservableInt
import com.lmw.base.customview.BaseCustomViewModel
import com.lmw.base.viewmodel.MvvmBaseViewModel

class MusicListViewModel : MvvmBaseViewModel<MusicListModel, BaseCustomViewModel>() {

    fun init(): MusicListViewModel {
        model = MusicListModel()
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