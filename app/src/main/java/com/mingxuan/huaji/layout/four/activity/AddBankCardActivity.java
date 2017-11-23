package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.utils.NewEditText;

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
    EditText bankAccount;
    @BindView(R.id.belongs_to_the_bank)
    TextView belongsToTheBank;
    @BindView(R.id.card_type)
    TextView cardType;
    @BindView(R.id.card_person)
    EditText cardPerson;
    @BindView(R.id.reserved_phone_number)
    EditText reservedPhoneNumber;
    @BindView(R.id.binding_acknowledgement)
    TextView bindingAcknowledgement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bankcard);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        bankAccount.addTextChangedListener(new NewEditText(bankAccount));
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
                Log.e("====","点击");
                break;
        }
    }
}
