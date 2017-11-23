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

public class LoginActivity extends Activity {
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.please_enter_proof_of_identity)
    EditText pleaseEnterProofOfIdentity;
    @BindView(R.id.please_enter_possword)
    EditText pleaseEnterPossword;
    @BindView(R.id.submit)
    TextView submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.register,R.id.submit})
    public void setOnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.register:
                intent = new Intent(LoginActivity.this,RegisitActivity.class);
                startActivity(intent);
                break;
            case R.id.submit:
                intent = new Intent(LoginActivity.this,HomePageViewPagerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
