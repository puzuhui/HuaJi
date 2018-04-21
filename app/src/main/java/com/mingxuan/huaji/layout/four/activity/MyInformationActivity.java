package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.model.InformationModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class MyInformationActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.real_name)
    TextView realName;
    @BindView(R.id.phonenumber)
    TextView phonenumber;
    @BindView(R.id.national_identification_number)
    TextView nationalIdentificationNumber;
    List<InformationModel.ResultBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        id = sharedPreferences.getString("create_id","");

        initView();
        getInformation();
    }

    private void initView() {
        list = new ArrayList<>();
    }


    @OnClick(R.id.back_btn)
    public void setOnClick(View view){
        finish();
    }

    String id;
    private void getInformation(){
        FourApi.getInstance(this).myInformationApi(id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    List<InformationModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,InformationModel.ResultBean.class);
                    list.addAll(resultBeans);

                    realName.setText(list.get(0).getName().replace(list.get(0).getName().
                            substring(0,list.get(0).getName().length()-1),"**"));
                    phonenumber.setText(list.get(0).getPhone().replace(list.get(0).getPhone().
                            substring(3,list.get(0).getPhone().length()-3),"****"));
                    nationalIdentificationNumber.setText(list.get(0).getId_card().replace(list.get(0).getId_card().
                            substring(1,list.get(0).getId_card().length()-1),"**************"));
                }else BaseApi.showErrMsg(MyInformationActivity.this,result);
            }
        });
    }
}
