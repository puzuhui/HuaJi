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
import android.util.Log;
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
import com.mingxuan.huaji.layout.mine.adapter.AreaAdapter;
import com.mingxuan.huaji.layout.mine.bean.AreaModel;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.bean.MyBankCardModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.NewEditText;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class AddBankCardActivity extends BaseActivity {
    @BindView(R.id.bank_account)
    EditText bankAccount;//银行卡账号
    @BindView(R.id.belongs_to_the_bank)
    TextView belongsToTheBank;//所属银行
    @BindView(R.id.card_type)
    TextView cardType;//银行卡类型
    @BindView(R.id.card_person)
    EditText cardPerson;//持有人
    @BindView(R.id.binding_acknowledgement)
    TextView bindingAcknowledgement;//绑定
    @BindView(R.id.tv_add_address)
    TextView tvAddAddress;
    @BindView(R.id.et_name)
    EditText etName;
    List<MyBankCardModel> list;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_bankcard;
    }

    @Override
    protected void initView() {
        setToolbarTitle(getString(R.string.add_bank_card));
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        login_id = sharedPreferences.getString("create_id","");
        create_id = sharedPreferences.getString("create_id","");
        create_name = sharedPreferences.getString("realName","");

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        create_time = simpleDateFormat.format(new Date());

        list = new ArrayList<>();
        addresslist = new ArrayList<>();
        bankAccount.addTextChangedListener(new NewEditText(bankAccount));
        bankAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!TextUtils.isEmpty(bankAccount.getText())){
                    if(!hasFocus){
                        cardNo = bankAccount.getText().toString();
                        bankCard();
                    }
                }
            }
        });
        cardPerson.addTextChangedListener(new NewEditText(cardPerson));
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.binding_acknowledgement,R.id.tv_add_address})
    public void setOnClickListener(View v){
        switch (v.getId()){
            case R.id.tv_add_address:
                showPopupeWindow();
                break;
            case R.id.binding_acknowledgement:
                if(!TextUtils.isEmpty(bankAccount.getText())){
                    if(!TextUtils.isEmpty(cardPerson.getText())){
                        if(!tvAddAddress.getText().toString().equals("请选择开户行地址")){
                            if(!TextUtils.isEmpty(etName.getText())){
                                bank_for_name =cardPerson.getText().toString();
                                bank_number = bankAccount.getText().toString();
                                if(cardType.getText().toString().equals("储蓄卡")){
                                    bank_type = "1";
                                }else if(cardType.getText().toString().equals("信用卡")){
                                    bank_type = "2";
                                }

                                bank = belongsToTheBank.getText().toString();
                                addresss = tvAddAddress.getText().toString();//地址
                                bankzh = etName.getText().toString();//开户支行
                                bank_code = "1003";
                                addBankCard();
                            }else {
                                ToastUtil.makeToast(AddBankCardActivity.this,"请填写支行名称");
                            }
                        }else {
                            ToastUtil.makeToast(AddBankCardActivity.this,"请选择开户行地址");
                        }
                    }else {
                        ToastUtil.makeToast(AddBankCardActivity.this,"持有人不能为空");
                    }
                }else {
                    ToastUtil.makeToast(AddBankCardActivity.this,"银行卡账号不能为空");
                }
                break;
        }
    }

    /**
     * 查找银行卡类型
     */
    String cardNo ;
    String cardtype;  //DC:储蓄卡，CC:信用卡 ,SCC: "准贷记卡",PC: "预付费卡"
    private void bankCard(){
        FourApi.getInstance(this).bankcardApi("utf-8", cardNo, true, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                MyBankCardModel myBankCardList = GsonUtil.fromJSONData(new Gson(),result,MyBankCardModel.class);
                if (myBankCardList.isValidated()) {
                    bank_for_codetype = myBankCardList.getBank();
                    cardtype = myBankCardList.getCardType();
                    try {
                        InputStream is = AddBankCardActivity.this.getAssets().open("bankjson.txt");
                        InputStreamReader inputStreamReader = new InputStreamReader(is);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String line;
                        StringBuilder stringBuilder = new StringBuilder();
                        while ((line = bufferedReader.readLine()) != null) {
                            // stringBuilder.append(line);
                            stringBuilder.append(line);
                        }
                        inputStreamReader.close();
                        bufferedReader.close();
                        is.close();

                        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                        belongsToTheBank.setText(jsonObject.get(bank_for_codetype).toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if(cardtype.equals("CC")){
                        cardType.setText("信用卡");
                    }else cardType.setText("储蓄卡");

                }else {
                    ToastUtil.makeToast(AddBankCardActivity.this,"输入的银行卡号有误");
                }
            }
        });
    }

    /**
     * 添加银行卡
     */
    String login_id, bank_for_codetype, bank_for_name, bank_number, bank_type;
    String addresss, bankzh, bank_code, bank;
    String create_id, create_name, create_time;
    private void addBankCard() {
        FourApi.getInstance(this).addbankcardApi(login_id, bank_for_codetype, bank_for_name, bank_number,
                bank_type, addresss, bankzh, bank_code, bank, create_id, create_name, create_time, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type == Constants.TYPE_SUCCESS){
                            Intent intent = new Intent(AddBankCardActivity.this,MyBankCardActivity.class);
                            startActivity(intent);
                            finish();
                        }else BaseApi.showErrMsg(AddBankCardActivity.this,result);
                    }
                });
    }

    private List<AreaModel.ResultBean> addresslist;
    RecyclerView recyclerView;
    AreaAdapter areaAdapter;
    private void showPopupeWindow(){
        choose_area();
        View view = LayoutInflater.from(AddBankCardActivity.this).inflate(R.layout.popupwindow_area,null);
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
        final TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        final View line1 = view.findViewById(R.id.line1);
        final View line2 = view.findViewById(R.id.line2);
        final View line3 = view.findViewById(R.id.line3);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        areaAdapter = new AreaAdapter(addresslist,AddBankCardActivity.this);
        recyclerView.setAdapter(areaAdapter);
        areaAdapter.setOnClickListener(new AreaAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int i) {
                if(level < 4){
                    level++;
                    code = addresslist.get(i).getCode();
                    newcode += code+",";
                    areaname += addresslist.get(i).getName()+" ";
                    choose_area();
                    if(level == 2){
                        shen.setText(addresslist.get(i).getName());
                        city.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.VISIBLE);
                    }else if(level == 3){
                        city.setText(addresslist.get(i).getName());
                        xian.setVisibility(View.VISIBLE);
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                        line3.setVisibility(View.VISIBLE);
                    }else if(level == 4){
                        xian.setText(addresslist.get(i).getName());
                        tvAddAddress.setText(areaname.substring(4));
                        finishcode = newcode.substring(4,newcode.length()-1);
                        tvAddAddress.setTextColor(getResources().getColor(R.color.black));
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
                    addresslist.clear();
                    addresslist.addAll(resultBeen);

                    areaAdapter.notifyDataSetChanged();
                }else BaseApi.showErrMsg(AddBankCardActivity.this,result);
            }
        });
    }
}
