package com.lmw.common.adapters;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;

public class Adapters {
    @BindingAdapter("setVisible")
    public static void setVisible(View view, Boolean str) {
        if (str) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(view.getContext())
                    .load(url)
                    .into(view);
        }
    }

    @BindingAdapter("setText")
    public static void setText(View view, String str) {
        if (view instanceof TextView) {
            ((TextView) view).setText(str);
        } else if (view instanceof Button) {
            ((Button) view).setText(str);
        }
    }
}
