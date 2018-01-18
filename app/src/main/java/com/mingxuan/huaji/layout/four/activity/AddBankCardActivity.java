package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.model.MyBankCardModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.NewEditText;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import org.json.JSONArray;
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

public class AddBankCardActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.bank_account)
    EditText bankAccount;//银行卡账号
    @BindView(R.id.belongs_to_the_bank)
    TextView belongsToTheBank;//所属银行
    @BindView(R.id.card_type)
    TextView cardType;//银行卡类型
    @BindView(R.id.card_person)
    EditText cardPerson;//持有人
    @BindView(R.id.reserved_phone_number)
    EditText reservedPhoneNumber;//预留手机号
    @BindView(R.id.binding_acknowledgement)
    TextView bindingAcknowledgement;//绑定
    List<MyBankCardModel> list;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bankcard);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("huaji", Context.MODE_PRIVATE);
        login_id = sharedPreferences.getString("create_id","");
        create_id = sharedPreferences.getString("create_id","");
        create_name = sharedPreferences.getString("create_name","");

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        create_time = simpleDateFormat.format(new Date());

        initView();

    }

    private void initView() {
        list = new ArrayList<>();
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
        reservedPhoneNumber.addTextChangedListener(new NewEditText(reservedPhoneNumber));
    }

    @OnClick({R.id.back_btn,R.id.binding_acknowledgement})
    public void setOnClickListener(View v){
        switch (v.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.binding_acknowledgement:
                if(!TextUtils.isEmpty(bankAccount.getText())){
                    if(!TextUtils.isEmpty(cardPerson.getText())){
                        if(!TextUtils.isEmpty(reservedPhoneNumber.getText())){
                            if(UIUtils.isMobileNO(reservedPhoneNumber.getText().toString())){
                                bank_for_name =cardPerson.getText().toString();
                                bank_number = bankAccount.getText().toString();
                                if(cardType.getText().toString().equals("储蓄卡")){
                                    bank_type = "1";
                                }else if(cardType.getText().toString().equals("信用卡")){
                                    bank_type = "2";
                                }

                                bank = belongsToTheBank.getText().toString();
                                phone = reservedPhoneNumber.getText().toString();
                                addBankCard();
                            }else {
                                ToastUtil.makeToast(AddBankCardActivity.this,"预留手机号格式错误");
                            }
                        }else {
                            ToastUtil.makeToast(AddBankCardActivity.this,"预留手机号不能为空");
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
    String cardname;
    String cardtype;  //DC:储蓄卡，CC:信用卡 ,SCC: "准贷记卡",PC: "预付费卡"
    private void bankCard(){
        FourApi.getInstance(this).bankcardApi("utf-8", cardNo, true, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                MyBankCardModel myBankCardList = GsonUtil.fromJSONData(new Gson(),result,MyBankCardModel.class);
                if (myBankCardList.isValidated()) {
                    cardname = myBankCardList.getBank();
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
                        Log.e("====",""+stringBuilder);

                        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                        Log.e("====",""+jsonObject);

                        belongsToTheBank.setText(jsonObject.get(cardname).toString());
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
    String login_id ,bank_for_name;
    String bank_number,bank_type;
    String bank, phone, create_id, create_name, create_time;
    private void addBankCard(){
        FourApi.getInstance(this).addbankcardApi(login_id, bank_for_name, bank_number, bank_type, bank,
                phone, create_id, create_name, create_time, new GetResultCallBack() {
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


}
