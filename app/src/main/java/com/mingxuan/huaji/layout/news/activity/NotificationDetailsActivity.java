package com.mingxuan.huaji.layout.news.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.TypedValue;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.utils.UIUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/5/23.
 * 公司：铭轩科技
 */

public class NotificationDetailsActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webview;
    private static final String TAG = "NotificationDetailsActi";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification_details;
    }

    public static String injectIsParams(String url) {
        if (url.contains("http://hjhp.cn")) {
            return url;
        } else {
            return "http://hjhp.cn"+url;
        }
    }


    @Override
    protected void initView() {
        setToolbarTitle("通知详情");
        Bundle bundle =getIntent().getExtras();
        String details = bundle.getString("details","");

        webview.loadData(details, "text/html; charset=UTF-8", null);

        webview.setWebViewClient(new WebViewClient(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                if(request.getUrl()){
//
//                }
                return super.shouldOverrideUrlLoading(view, request);
            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                String ss = "<script type=\"text/javascript\">\n" +
//                        "\tdocument.ready(function(){\n" +
//                        "\t\tvar img = document.getElementsByTagName(\\\"img\\\");\n" +
//                        "        var scr = img.src;\n" +
//                        "        document.getElementsByTagName(\\\"img\\\").src='http://hjhp.cn'+scr\n" +
//                        "        )};\n" +
//                        "</script>";
//                webview.loadUrl(ss);
//                return true;
//            }
        });

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setDefaultFontSize(UIUtils.px2dp(16));
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {

    }

}
