package com.mingxuan.huaji.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mingxuan.huaji.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class RegisitActivity extends Activity {
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.please_enter_username)
    EditText pleaseEnterUsername;
    @BindView(R.id.please_enter_id_number)
    EditText pleaseEnterIdNumber;
    @BindView(R.id.please_enter_id_name)
    EditText pleaseEnterIdName;
    @BindView(R.id.please_enter_phone_number)
    EditText pleaseEnterPhoneNumber;
    @BindView(R.id.please_enter_verification_code)
    EditText pleaseEnterVerificationCode;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.set_possword)
    EditText setPossword;
    @BindView(R.id.please_enter_possword_two)
    EditText pleaseEnterPosswordTwo;
    @BindView(R.id.submit)
    TextView submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login,R.id.get_code,R.id.submit})
    public void setOnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.login:
                intent = new Intent(RegisitActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
