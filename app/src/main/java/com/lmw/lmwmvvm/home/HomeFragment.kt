package com.lmw.lmwmvvm.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import com.lmw.base.customview.BaseCustomViewModel
import com.lmw.base.fragment.MvvmFragment
import com.lmw.lmwmvvm.R
import com.lmw.lmwmvvm.databinding.FragmentHomeBinding
import com.lmw.lmwmvvm.music.MusicListFragment

class HomeFragment : MvvmFragment<FragmentHomeBinding, HomeViewModel, BaseCustomViewModel>() {
    private var mTabs: ArrayList<String>? = null
    private var mTabFragments: ArrayList<Fragment>? = null
    private var mCurrentItem: Int = 0

    private val gifSelectIds = intArrayOf(
        R.drawable.jetpack_hero,
        R.drawable.jetpack_hero,
        R.drawable.jetpack_hero,
        R.drawable.jetpack_hero,
        R.drawable.jetpack_hero
    )

    private val iconSelectIds = intArrayOf(
        R.drawable.jetpack_hero, R.drawable
            .jetpack_hero, R.drawable.jetpack_hero,
        R.drawable.jetpack_hero, R.drawable.jetpack_hero
    )

    private val iconUnSelectIds = intArrayOf(
        R.drawable.jetpack_hero,
        R.drawable.jetpack_hero, R.drawable.jetpack_hero,
        R.drawable.jetpack_hero, R.drawable.jetpack_hero
    )

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
        initFragment()
        initViewPager()
        initTabLayout()
        initCurrentFragment()
    }

    private fun initFragment() {
        mTabFragments = arrayListOf()
        mTabFragments?.add(MusicListFragment.newInstance(""))
        mTabFragments?.add(MusicListFragment.newInstance(""))
        mTabFragments?.add(MusicListFragment.newInstance(""))
        mTabFragments?.add(MusicListFragment.newInstance(""))
        mTabFragments?.add(MusicListFragment.newInstance(""))
    }

    private fun initViewPager() {
        viewDataBinding?.viewPager?.clearOnPageChangeListeners()
        viewDataBinding?.viewPager?.offscreenPageLimit = 4
        viewDataBinding?.viewPager?.adapter =
            object : FragmentPagerAdapter(childFragmentManager) {
                override fun getItem(position: Int): Fragment {
                    return mTabFragments!![position]
                }

                override fun getCount(): Int {
                    return mTabFragments!!.size
                }
            }

    }

    private fun initTabLayout() {
        mTabs = arrayListOf()
        mTabs?.add("首页")
        mTabs?.add("藏品")
        mTabs?.add("守艺")
        mTabs?.add("圈子")
        mTabs?.add("我的")

        viewDataBinding?.tabLayout?.setupWithViewPager(viewDataBinding?.viewPager)
        mTabs?.let {
            for (i in 0 until mTabs!!.size) {
                val tab = viewDataBinding?.tabLayout?.getTabAt(i)
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.tablayout_item_main, viewDataBinding?.tabLayout, false)
                val ivItem = view.findViewById<ImageView>(R.id.ivItem)
                val tvTab = view.findViewById<TextView>(R.id.tvTab)

                if (i == mCurrentItem) {
                    ivItem?.setImageResource(iconSelectIds[i])
                    context?.let { it1 -> ContextCompat.getColor(it1, R.color.c_d9a366) }
                        ?.let { it2 ->
                            tvTab?.setTextColor(
                                it2
                            )
                        }
                } else {
                    ivItem.setImageResource(iconUnSelectIds[i])
                    context?.let { it1 -> ContextCompat.getColor(it1, R.color.c_333333) }
                        ?.let { it2 ->
                            tvTab?.setTextColor(
                                it2
                            )
                        }
                }
                tvTab.text = mTabs?.get(i) ?: ""

                tab?.customView = view
                if (i == 4) {
                    view?.setOnClickListener {
                        setCurrentItem(i)
                    }
                }
            }
        }
    }

    private fun initCurrentFragment() {
        setCurrentItem(mCurrentItem)
    }

    private fun setCurrentItem(position: Int) {
        mCurrentItem = position
        setCurrentItem()
    }

    private fun setCurrentItem() {
        if (mCurrentItem != 0 && mTabFragments != null && mCurrentItem < mTabFragments!!.size) {
            viewDataBinding?.viewPager?.setCurrentItem(mCurrentItem, false)
        }
    }
}