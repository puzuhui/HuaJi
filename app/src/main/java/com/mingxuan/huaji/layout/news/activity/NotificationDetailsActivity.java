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
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.webview)
    WebView webview;
    private static final String TAG = "NotificationDetailsActi";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification_details;
    }

//    <p style="text-align:center;line-height:37px">
//    <span style="font-family: arial, helvetica, sans-serif; font-size: 10px;">
//            （重庆）华记黄埔 〔2018〕018号</span></p><p style="line-height:150%">
//    <span style="line-height: 150%; font-family: arial, helvetica, sans-serif; font-size: 16px;">
//    </span>
//    </p>
//    <p style="line-height:150%">
//    <span style="line-height: 150%; font-family: arial, helvetica, sans-serif; font-size: 16px;">尊敬的各位服务商：</span>
//    </p>
//    <p style="text-indent:43px;line-height:150%">
//   <span style="line-height: 150%; font-family: arial, helvetica, sans-serif; font-size: 16px;">服务商直接办理5户及以上，奖励300元政策的活动名额已经全部抢完。截止2018年05月31日24：00时,将正式取消华记黄埔 〔2018〕005号公告文件政策。请各位服务商抓住这最后的时间。</span>
// </p>
// <p style="text-indent:43px;line-height:150%">
// <span style="line-height: 150%; font-family: arial, helvetica, sans-serif; font-size: 16px;">
// 再此感谢各位服务商的积极参与支持，后期我们将会有更加丰厚的活动奖励敬献给大家。敬请期待！同时也希望大家更加斗志昂扬冲向梦想的征途。
// </span>
// </p>
// <p style="text-align:center">
// <span style="line-height: 150%; color: rgb(62, 62, 62); font-family: arial, helvetica, sans-serif; font-size: 16px; background: white;">
// <img src="/files/inform/productImg/20180521/1526882468208050689.jpg" title="1526882468208050689.jpg" alt="5.21-2.jpg" width="732" height="781" style="width: 732px; height: 781px;"/>
// </span>
// </p>
// <p style="text-indent:43px;line-height:150%">
// <span style="font-size:21px;line-height:150%;font-family:宋体">&nbsp;</span>
// </p>
// <p style="line-height: 150%; text-align: right;">
// <span style="line-height: 150%; font-family: arial, helvetica, sans-serif; font-size: 16px;">&nbsp;</span>
// </p>
// <p style="margin-right:37px;text-align:right;text-indent:48px;line-height:29px">
// <span style="font-family: arial, helvetica, sans-serif; font-size: 16px;">重庆华记黄埔信息技术有限公司</span>
// </p><p style="margin-right:37px;text-align:right;text-indent:48px;line-height:29px">
// <span style="font-family: arial, helvetica, sans-serif; font-size: 16px;">2018年05月21日</span></p>
// <p style="margin-right: 80px; text-indent: 378px; line-height: 29px; text-align: right;"><br/></p><p><br/></p>

    public static String injectIsParams(String url) {
        if (url.contains("http://hjhp.cn")) {
            return url;
        } else {
            return "http://hjhp.cn"+url;
        }
    }


    @Override
    protected void initView() {
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

    @OnClick(R.id.back_btn)
    public void onViewClicked() {
        finish();
    }
}
