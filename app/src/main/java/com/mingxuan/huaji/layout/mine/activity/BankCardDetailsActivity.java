package com.mingxuan.huaji.layout.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.bean.MyBankCardModel;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/6/6.
 * 公司：铭轩科技
 */

public class BankCardDetailsActivity extends BaseActivity {
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_numb)
    TextView tvBankNumb;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_bankzh)
    TextView tvBankzh;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.submit)
    TextView submit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank_details;
    }

    @Override
    protected void initView() {
        setToolbarTitle("银行卡详情");
        MyBankCardModel.ResultBean resultBean = (MyBankCardModel.ResultBean) getIntent().getSerializableExtra("bank");
        id = resultBean.getId();
        tvBankName.setText(resultBean.getBankX());
        tvBankNumb.setText("尾号"+resultBean.getBank_number().substring(resultBean.getBank_number().length()-4,resultBean.getBank_number().length()));
        if(resultBean.getBank_type().equals("1")){//1储蓄卡  2、信用卡  3.准贷记卡  4.预付费卡
            tvType.setText("储蓄卡");
        }else if (resultBean.getBank_type().equals("2")){
            tvType.setText("信用卡");
        }else if (resultBean.getBank_type().equals("3")){
            tvType.setText("准贷记卡");
        }else {
            tvType.setText("预付费卡");
        }
        tvBankzh.setText(resultBean.getBankzh());
        tvName.setText(resultBean.getBank_for_name());
        switch (resultBean.getBank_for_codetype()){
            case "ABC":
                setImageView(R.mipmap.abc);
                break;
            case "BOC":
                setImageView(R.mipmap.boc);
                break;
            case "CCB":
                setImageView(R.mipmap.ccb);
                break;
            case "CEB":
                setImageView(R.mipmap.ceb);
                break;
            case "CIB":
                setImageView(R.mipmap.cib);
                break;
            case "CITIC":
                setImageView(R.mipmap.citic);
                break;
            case "CMB":
                setImageView(R.mipmap.cmb);
                break;
            case "CMBC":
                setImageView(R.mipmap.cmbc);
                break;
            case "COMM":
                setImageView(R.mipmap.comm);
                break;
            case "GDB":
                setImageView(R.mipmap.gdb);
                break;
            case "HXBANK":
                setImageView(R.mipmap.hxbank);
                break;
            case "ICBC":
                setImageView(R.mipmap.icbc);
                break;
            case "PSBC":
                setImageView(R.mipmap.psbc);
                break;
            case "SPABANK":
                setImageView(R.mipmap.spabank);
                break;
            case "SPDB":
                setImageView(R.mipmap.spdb);
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    private void setImageView(int imageViewId){
        Glide.with(this).load(imageViewId).into(imageView);
    }

    String id;
    String del_flag = "1";
    private void delbankcard() {
        FourApi.getInstance(this).delbankcardApi(id,del_flag, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    Intent intent = new Intent(BankCardDetailsActivity.this,MyBankCardActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @OnClick({ R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                delbankcard();
                break;
        }
    }
}
