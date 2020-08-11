package com.lmw.lmwmvvm.music

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModelProviders
import com.lmw.base.customview.BaseCustomViewModel
import com.lmw.base.fragment.MvvmFragment
import com.lmw.lmwmvvm.R
import com.lmw.lmwmvvm.databinding.FragmentHomeBinding

class MusicListFragment :
    MvvmFragment<FragmentHomeBinding, MusicListViewModel, BaseCustomViewModel>() {
    companion object {
        fun newInstance(tab: String): MusicListFragment {
            val args = Bundle()
            val fragment = MusicListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getBindingVariable(): Int {
        return 1
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_music_list
    }

    override fun getViewModel(): MusicListViewModel {
        if (viewModel == null) {
            viewModel =
                ViewModelProviders.of(this).get(fragmentTag, MusicListViewModel::class.java)
                    .init()
            return viewModel
        }
        return viewModel
    }

    override fun onListItemInserted(sender: ObservableList<BaseCustomViewModel>?) {
    }

    override fun onRetryBtnClick() {
    }

    override fun getFragmentTag(): String {
        return ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}