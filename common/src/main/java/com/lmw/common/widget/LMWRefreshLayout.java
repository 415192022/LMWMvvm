package com.lmw.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.lmw.widget.lmwrefreshlayout.lib.VerticalRefreshLayout;
import com.lmw.widget.lmwrefreshlayout.lib.widget.DefaultHeaderView;


public class LMWRefreshLayout extends VerticalRefreshLayout {

    public LMWRefreshLayout(Context context) {
        this(context, null);
    }

    public LMWRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LMWRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setReleaseDistance(50);
        setLoadMoreDistance(3);
        setHeightRadio(1);
        setSupportNestedScroll(false);
        setDuration(500);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected View getHeaderView() {
        return new DefaultHeaderView(getContext());
    }

    @Override
    protected View getFooterView() {
        return new DefaultHeaderView(getContext());
    }
}
