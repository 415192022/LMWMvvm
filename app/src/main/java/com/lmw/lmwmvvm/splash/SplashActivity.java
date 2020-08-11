package com.lmw.lmwmvvm.splash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.lmw.common.mmkv.MmkvHelper;
import com.lmw.lmwmvvm.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startToMain();
    }

    private void startToMain() {
        MainActivity.Companion.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
