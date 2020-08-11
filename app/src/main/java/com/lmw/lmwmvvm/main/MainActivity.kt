package com.lmw.lmwmvvm.main;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.lmw.base.activity.MvvmActivity
import com.lmw.common.mmkv.MmkvHelper
import com.lmw.lmwmvvm.R
import com.lmw.lmwmvvm.databinding.ActivityMainBinding
import com.lmw.lmwmvvm.guide.GuideFragment
import com.lmw.lmwmvvm.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvvmActivity<ActivityMainBinding, MainViewModel>() {

    private var mTabs: ArrayList<String>? = arrayListOf()

    private var mTabFragments: ArrayList<Fragment>? = arrayListOf()

    private var mCurrentItem: Int = 0

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
        initViewPager()
        if (MmkvHelper.getInstance().decodeBool("first", true)) {
            ToastUtils.showShort("第一次进入应用")
            MmkvHelper.getInstance().mmkv.encode("first", false)
            setCurrentItem(0)
        } else {
            setCurrentItem(1)
        }

        setListener()
    }

    private fun setListener() {
        tvChange?.setOnClickListener {
            viewModel?.changeValue()
            tvChange?.visibility = View.GONE
            setCurrentItem(1)
        }
    }


    private fun initViewPager() {
        viewDataBinding?.viewPage?.clearOnPageChangeListeners()
        viewDataBinding?.viewPage?.offscreenPageLimit = 4
        viewDataBinding?.viewPage?.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return mTabFragments!![position]
            }

            override fun getCount(): Int {
                return mTabFragments!!.size
            }
        }
        viewDataBinding?.viewPage?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                mCurrentItem = position
            }
        })

    }

    private fun initFragment() {
        mTabFragments = arrayListOf()
        mTabFragments?.add(GuideFragment.newInstance(""))
        mTabFragments?.add(HomeFragment.newInstance(""))
    }

    override fun onRetryBtnClick() {

    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java).init()
    }

    override fun getBindingVariable(): Int {
        return 1
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private fun setCurrentItem(position: Int) {
        mCurrentItem = position
        viewPage?.setCurrentItem(mCurrentItem, false)
        LogUtils.d("setCurrentItem: $position")
    }
}
