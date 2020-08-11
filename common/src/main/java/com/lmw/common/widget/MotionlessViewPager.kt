package com.lmw.common.widget

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class MotionlessViewPager : ViewPager {

    // 初始设置滑动静止
    private var scrolled = true

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    fun setScroll(noScroll: Boolean) {
        this.scrolled = noScroll
    }

    override fun onTouchEvent(arg0: MotionEvent): Boolean {
        if (scrolled)
            return false
        else
            return super.onTouchEvent(arg0)
    }

    override fun onInterceptTouchEvent(arg0: MotionEvent): Boolean {
        if (scrolled)
            return false
        else
            return super.onInterceptTouchEvent(arg0)
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }

}