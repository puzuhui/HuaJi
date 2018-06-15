package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/4/23.
 * 公司：铭轩科技
 */

public class QrcodeActivity extends BaseActivity {
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qrcode_two;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initView() {
        setToolbarTitle("推广");
        Bitmap bitmap = generateBitmap("http://www.baidu.com", 500, 500);
        iv.setImageBitmap(bitmap);
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.iv)
    public void onViewClicked() {

    }

    //生成二维码
    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
