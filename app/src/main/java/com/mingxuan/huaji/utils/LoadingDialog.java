package com.mingxuan.huaji.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.widget.TextView;

import com.mingxuan.huaji.R;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class LoadingDialog extends Dialog {

    private Activity mActivity;

    public LoadingDialog(Activity mActivity) {
        super(mActivity, R.style.dialog);
        this.mActivity = mActivity;
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_loading);
        getWindow().setDimAmount(0.0f);//去除半透明效果
    }

    //延迟200毫秒结束
    @Override
    public void dismiss() {
        if(mActivity != null && !mActivity.isFinishing() && isShowing())
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    LoadingDialog.super.dismiss();
                }
            },200);

    }

    //可设置加载文字提示,可不传，默认加载中
    public void setLoadingContent(String content) {
        ((TextView)findViewById(R.id.loading_text)).setText(content);
    }

}
