package com.lmw.lmwmvvm.home

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModelProviders
import com.lmw.base.customview.BaseCustomViewModel
import com.lmw.base.fragment.MvvmFragment
import com.lmw.lmwmvvm.R
import com.lmw.lmwmvvm.databinding.FragmentHomeBinding

class HomeFragment : MvvmFragment<FragmentHomeBinding, HomeViewModel, BaseCustomViewModel>() {
    companion object {
        fun newInstance(tab: String): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getViewModel(): HomeViewModel {
        if (viewModel == null) {
            viewModel =
                ViewModelProviders.of(this).get(fragmentTag, HomeViewModel::class.java)
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
        viewDataBinding?.rlRoot?.setOnClickListener {
            viewModel?.tryToLoadNextPage()
        }
    }
}