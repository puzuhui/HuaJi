package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.adapter.AreaAdapter;
import com.mingxuan.huaji.layout.mine.bean.AreaModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.NewEditText;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class AddAddressActivity extends BaseActivity {
    @BindView(R.id.consignee)
    EditText consignee;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.addaddress)
    EditText addaddress;
    @BindView(R.id.save)
    TextView save;
    private int index;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addaddress;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initView() {
        setToolbarTitle(getString(R.string.compile_address));
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("create_id","");
        create_name = sharedPreferences.getString("realName","");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        create_time = simpleDateFormat.format(new Date());

        list = new ArrayList<>();
        Bundle bundle =getIntent().getExtras();
        index = bundle.getInt("index");
        if(index == 102){
            consignee.setText(bundle.getString("consingee"));
            phoneNumber.setText(bundle.getString("phone"));
            address.setText(bundle.getString("seleadd_name"));
            address.setTextColor(getResources().getColor(R.color.black));
            addaddress.setText(bundle.getString("address"));
            default_flag = bundle.getInt("default_flag");
            id = bundle.getInt("id");
            selete_address = bundle.getString("seleadd_address");
        }
        setListener();
    }

    @Override
    protected void initData() {

    }

    private void setListener() {
        consignee.addTextChangedListener(new NewEditText(consignee));
        phoneNumber.addTextChangedListener(new NewEditText(phoneNumber));
        addaddress.addTextChangedListener(new NewEditText(addaddress));

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupeWindow();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == 101 ){
                    if(!TextUtils.isEmpty(consignee.getText())){
                        if(!TextUtils.isEmpty(phoneNumber.getText())){
                            if(!TextUtils.isEmpty(address.getText())){
                                if(!TextUtils.isEmpty(addaddress.getText())){
                                    aconsignee = consignee.getText().toString();
                                    phone = phoneNumber.getText().toString();
                                    seleadd_name = address.getText().toString();
                                    aaddress = addaddress.getText().toString();
                                    default_flag = 0;
                                    selete_address = finishcode;
                                    if(UIUtils.isMobileNO(phone)){
                                        getmyaddress();
                                    }else {
                                        ToastUtil.makeToast(AddAddressActivity.this,"请输入正确的手机号码");
                                    }
                                }else {
                                    ToastUtil.makeToast(AddAddressActivity.this,"请选择所在地区");
                                }
                            }else {
                                ToastUtil.makeToast(AddAddressActivity.this,"请选择所在地区");
                            }
                        }else {
                            ToastUtil.makeToast(AddAddressActivity.this,"请填写手机号码");
                        }
                    }else {
                        ToastUtil.makeToast(AddAddressActivity.this,"请填写收货人");
                    }
                }else {
                    if(!TextUtils.isEmpty(consignee.getText())){
                        if(!TextUtils.isEmpty(phoneNumber.getText())){
                            if(!TextUtils.isEmpty(address.getText())){
                                if(!TextUtils.isEmpty(addaddress.getText())){
                                    aconsignee = consignee.getText().toString();
                                    phone = phoneNumber.getText().toString();
                                    seleadd_name = address.getText().toString();
                                    aaddress = addaddress.getText().toString();
                                    selete_address = finishcode;
                                    if(UIUtils.isMobileNO(phone)){
                                        xgaddress();
                                    }else {
                                        ToastUtil.makeToast(AddAddressActivity.this,"请输入正确的手机号码");
                                    }
                                }else {
                                    ToastUtil.makeToast(AddAddressActivity.this,"请选择所在地区");
                                }
                            }else {
                                ToastUtil.makeToast(AddAddressActivity.this,"请选择所在地区");
                            }
                        }else {
                            ToastUtil.makeToast(AddAddressActivity.this,"请填写手机号码");
                        }
                    }else {
                        ToastUtil.makeToast(AddAddressActivity.this,"请填写收货人");
                    }
                }
            }
        });
    }

    private List<AreaModel.ResultBean> list;
    RecyclerView recyclerView;
    AreaAdapter areaAdapter;
    private void showPopupeWindow(){
        choose_area();
        View view = LayoutInflater.from(AddAddressActivity.this).inflate(R.layout.popupwindow_area,null);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*3/5;

        final PopupWindow popupWindow = new PopupWindow(view,weight,height,true);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        final TextView shen = (TextView) view.findViewById(R.id.shen);
        final TextView city = (TextView) view.findViewById(R.id.city);
        final TextView xian = (TextView) view.findViewById(R.id.xian);
        final TextView zheng = (TextView) view.findViewById(R.id.zheng);
        final TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        final View line1 = view.findViewById(R.id.line1);
        final View line2 = view.findViewById(R.id.line2);
        final View line3 = view.findViewById(R.id.line3);
        final View line4 = view.findViewById(R.id.line4);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        areaAdapter = new AreaAdapter(list,AddAddressActivity.this);
        recyclerView.setAdapter(areaAdapter);
        areaAdapter.setOnClickListener(new AreaAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int i) {
                if(level < 5){
                    level++;
                    code = list.get(i).getCode();
                    newcode += code+",";
                    areaname += list.get(i).getName()+" ";
                    choose_area();
                    if(level == 2){
                        shen.setText(list.get(i).getName());
                        city.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.VISIBLE);
                    }else if(level == 3){
                        city.setText(list.get(i).getName());
                        xian.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                        line3.setVisibility(View.VISIBLE);
                    }else if(level == 4){
                        xian.setText(list.get(i).getName());
                        zheng.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                        line3.setVisibility(View.INVISIBLE);
                        line4.setVisibility(View.VISIBLE);
                    }else {
                        zheng.setText(list.get(i).getName());
                        line4.setVisibility(View.INVISIBLE);
                        address.setText(areaname.substring(4));
                        finishcode = newcode.substring(4,newcode.length()-1);
                        address.setTextColor(getResources().getColor(R.color.black));
                        popupWindow.dismiss();
                        level = 1;
                        code = null;
                        areaname = null;
                        newcode = null;
                    }
                }
            }
        });

        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });

        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
    }

    String areaname;
    String newcode;
    String finishcode;
    int level = 1;
    String code;
    private void choose_area(){
        FourApi.getInstance(this).getprovinceApi(level, code, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type ==  Constants.TYPE_SUCCESS){
                    List<AreaModel.ResultBean> resultBeen = GsonUtil.fromJsonList(new Gson(),result,
                            AreaModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeen);

                    areaAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(AddAddressActivity.this,result);
            }
        });
    }

    String create_id;
    String aconsignee ;
    String phone;
    int default_flag;
    String selete_address;
    String seleadd_name;
    String aaddress;
    String create_name;
    String create_time;
    private void getmyaddress(){
        FourApi.getInstance(this).getaddaddressApi(aconsignee,phone, default_flag,selete_address, seleadd_name,aaddress,
                create_id, create_name,create_time, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type ==  Constants.TYPE_SUCCESS){
                    Intent intent = new Intent(AddAddressActivity.this,MyAdressActivity.class);
                    startActivity(intent);
                }else BaseApi.showErrMsg(AddAddressActivity.this,result);
            }
        });
    }

    /**
     * 编辑
     */
    int id;
    int del_flag;
    private void xgaddress(){
        FourApi.getInstance(this).getxgaddressApi(id,aconsignee,phone, default_flag,selete_address, seleadd_name,aaddress,
                create_id, create_name,create_time,del_flag, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type ==  Constants.TYPE_SUCCESS){
                            Intent intent = new Intent(AddAddressActivity.this,MyAdressActivity.class);
                            startActivity(intent);
                        }else BaseApi.showErrMsg(AddAddressActivity.this,result);
                    }
                });
    }

}
