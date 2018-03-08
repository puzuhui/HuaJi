package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/3/2.
 * 公司：铭轩科技
 */

public class PasswordManageActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.insertpassword)
    TextView insertpassword;
    @BindView(R.id.insertpassword_two)
    TextView insertpasswordTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordmanage);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_btn,R.id.insertpassword,R.id.insertpassword_two})
    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.insertpassword:
                intent = new Intent(this,InsertPasswordActivity.class);
                intent.putExtra("index",1);
                startActivity(intent);
                break;
            case R.id.insertpassword_two:
                intent = new Intent(this,InsertPasswordActivity.class);
                intent.putExtra("index",2);
                startActivity(intent);
                break;
        }
    }
}
