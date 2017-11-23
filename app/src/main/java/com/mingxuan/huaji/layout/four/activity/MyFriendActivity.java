package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.adapter.MyFriendAdapter;
import com.mingxuan.huaji.layout.four.model.MyFriendsModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class MyFriendActivity extends Activity {
    @BindView(R.id.head_shop)
    TextView headShop;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.back_btn)
    ImageView backbtn;
    private List<MyFriendsModel.ResultBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfriend);
        ButterKnife.bind(this);

        initView();
        getFriend();
    }

    private void initView() {
        list = new ArrayList<>();

        myFriendAdapter = new MyFriendAdapter(MyFriendActivity.this, list);
        listview.setAdapter(myFriendAdapter);
    }

    @OnClick(R.id.back_btn)
    public void setClick(){
        finish();
    }

    MyFriendAdapter myFriendAdapter;
    String p_id = "1";
    private void getFriend() {
        FourApi.getInstance(this).getfriendApi(p_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    List<MyFriendsModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(), result, MyFriendsModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    myFriendAdapter.notifyDataSetChanged();
                } else BaseApi.showErrMsg(MyFriendActivity.this, result);
            }
        });
    }
}
