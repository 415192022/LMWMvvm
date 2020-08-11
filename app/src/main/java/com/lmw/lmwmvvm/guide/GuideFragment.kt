package com.lmw.lmwmvvm.guide

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.core.view.KeyEventDispatcher
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.blankj.utilcode.util.LogUtils
import com.lmw.base.customview.BaseCustomViewModel
import com.lmw.base.fragment.MvvmFragment
import com.lmw.lmwmvvm.R
import com.lmw.lmwmvvm.databinding.FragmentGuideBinding
import com.lmw.lmwmvvm.databinding.FragmentHomeBinding
import com.lmw.lmwmvvm.home.HomeViewModel
import com.lmw.lmwmvvm.pojo.bo.UserLoginBo
import com.zhpan.bannerview.adapter.OnPageChangeListenerAdapter
import com.zhpan.bannerview.utils.BannerUtils
import com.zhpan.indicator.enums.IndicatorSlideMode
import kotlinx.android.synthetic.main.fragment_guide.*
import java.util.*
import kotlin.collections.ArrayList

class GuideFragment : MvvmFragment<FragmentGuideBinding, GuideViewModel, BaseCustomViewModel>() {
    companion object {
        fun newInstance(tab: String): GuideFragment {
            val args = Bundle()
            val fragment = GuideFragment()
            fragment.arguments = args
            return fragment
        }

        private const val ANIMATION_DURATION = 1300

    }

    override fun getBindingVariable(): Int {
        return 1
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_guide
    }

    override fun getViewModel(): GuideViewModel {
        var drawables: ArrayList<Drawable> = arrayListOf()
        context?.let {
            ContextCompat.getDrawable(it, R.drawable.guide0)?.let { it1 -> drawables?.add(it1) }
            ContextCompat.getDrawable(it, R.drawable.guide1)?.let { it1 -> drawables?.add(it1) }
            ContextCompat.getDrawable(it, R.drawable.guide2)?.let { it1 -> drawables?.add(it1) }

        }
        return ViewModelProviders.of(this).get(GuideViewModel::class.java).init()
            .initDrawable(drawables)
    }

    override fun onListItemInserted(sender: ObservableList<BaseCustomViewModel>?) {
    }

    override fun onRetryBtnClick() {
    }

    override fun getFragmentTag(): String {
        return tag ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    private fun updateUI(position: Int) {
        tv_describe?.text = viewModel?.mStrList?.value?.get(position)
        val translationAnim = ObjectAnimator.ofFloat(tv_describe, "translationX", -120f, 0f)
        translationAnim.duration = ANIMATION_DURATION.toLong()
        translationAnim.interpolator = DecelerateInterpolator()
        val alphaAnimator1 = ObjectAnimator.ofFloat(tv_describe, "alpha", 0f, 1f)
        alphaAnimator1.duration = ANIMATION_DURATION.toLong()
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(translationAnim, alphaAnimator1)
        animatorSet.start()

        if (position == viewpager.list.size - 1 && btn_start?.visibility == View.GONE) {
            btn_start?.visibility = View.VISIBLE
            ObjectAnimator
                .ofFloat(btn_start, "alpha", 0f, 1f)
                .setDuration(ANIMATION_DURATION.toLong()).start()
        } else {
            btn_start?.visibility = View.GONE
        }
    }
}