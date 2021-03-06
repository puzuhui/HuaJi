package com.mingxuan.huaji.layout.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.adapter.MyFriendAdapter;
import com.mingxuan.huaji.layout.mine.bean.MyFriendsModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class MyFriendActivity extends BaseActivity {
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.emptyview)
    View emptyview;
    private List<MyFriendsModel.ResultBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myfriend;
    }

    @Override
    protected void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        p_id = sharedPreferences.getString("create_id","");

        list = new ArrayList<>();

        myFriendAdapter = new MyFriendAdapter(MyFriendActivity.this, list);
        listview.setAdapter(myFriendAdapter);
        listview.setEmptyView(emptyview);

        setToolbarTitle("我的伙伴");
    }

    @Override
    protected void initData() {
        getFriend();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    MyFriendAdapter myFriendAdapter;
    String p_id;
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
