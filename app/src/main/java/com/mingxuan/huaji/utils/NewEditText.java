package com.mingxuan.huaji.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class NewEditText implements TextWatcher {
    private EditText edit;
    public NewEditText(EditText edit){
        this.edit = edit ;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String msg4 = edit.getText().toString();
    }
}
