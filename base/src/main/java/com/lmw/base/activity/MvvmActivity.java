package com.lmw.base.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.BarUtils;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.lmw.base.R;
import com.lmw.base.loadsir.EmptyCallback;
import com.lmw.base.loadsir.ErrorCallback;
import com.lmw.base.loadsir.LoadingCallback;
import com.lmw.base.utils.ToastUtil;
import com.lmw.base.viewmodel.MvvmBaseViewModel;
import com.lmw.base.viewmodel.ViewStatus;

public abstract class MvvmActivity<V extends ViewDataBinding, VM extends MvvmBaseViewModel> extends AppCompatActivity implements Observer {
    protected VM viewModel;
    private LoadService mLoadService;
    protected V viewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O && !setTranslucentTheme())
            //android8.0如果同时设置透明主题+屏幕方向会导致崩溃
            //设置屏幕竖屏
            setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O)
            initViewModel();
        performDataBinding();
        if (viewModel != null)
            getLifecycle().addObserver(viewModel);
    }

    private void initViewModel() {
        viewModel = getViewModel();
    }

    public void setLoadSir(View view) {
        // You can change the callback on sub thread directly.
        mLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onRetryBtnClick();
            }
        });
    }

    protected abstract void onRetryBtnClick();

    protected abstract VM getViewModel();

    public abstract int getBindingVariable();

    public abstract
    @LayoutRes
    int getLayoutId();

    private void performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
        if (getBindingVariable() > 0) {
            viewDataBinding.setVariable(getBindingVariable(), viewModel);
        }
        viewDataBinding.executePendingBindings();
    }

    @Override
    public void onChanged(Object o) {
        if (o instanceof ViewStatus && mLoadService != null) {
            switch ((ViewStatus) o) {
                case LOADING:
                    mLoadService.showCallback(LoadingCallback.class);
                    break;
                case EMPTY:
                    mLoadService.showCallback(EmptyCallback.class);
                    break;
                case SHOW_CONTENT:
                    mLoadService.showSuccess();
                    break;
                case NO_MORE_DATA:
                    ToastUtil.show(getString(R.string.no_more_data));
                    break;
                case REFRESH_ERROR:
                    if (((ObservableArrayList) viewModel.dataList.getValue()).size() == 0) {
                        mLoadService.showCallback(ErrorCallback.class);
                    } else {
                        ToastUtil.show(viewModel.errorMessage.getValue().toString());
                    }
                    break;
                case LOAD_MORE_FAILED:
                    ToastUtil.show(viewModel.errorMessage.getValue().toString());
                    break;
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(getActivityTag(), "Activity:" + this + ": " + "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(getActivityTag(), "Activity:" + this + ": " + "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(getActivityTag(), "Activity:" + this + ": " + "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(getActivityTag(), "Activity:" + this + ": " + "onDestroy");
    }

    protected String getActivityTag() {
        return this.getClass().getSimpleName();
    }

    /**
     * set screen orientation
     *
     * @param screenOrientationPortrait
     */
    private void setScreenOrientation(int screenOrientationPortrait) {
        setRequestedOrientation(screenOrientationPortrait);
    }

    public Boolean setTranslucentTheme() {
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setTranslucentStatus(isApplyStatusBarTranslucency());
    }

    /**
     * 设置沉浸式状态栏
     *
     * @param on
     */
    private void setTranslucentStatus(boolean on) {
        if (on) {
            BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
            setBarTextColor();
        }
    }

    private void setBarTextColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * 是否使用沉浸式
     */
    protected abstract boolean isApplyStatusBarTranslucency();
}
