package com.mingxuan.huaji.layout.homepage.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.bean.PictureModel;
import com.mingxuan.huaji.layout.homepage.adapter.CommentAdapter;
import com.mingxuan.huaji.layout.homepage.adapter.CommentExpandableAdapter;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class CommentActivity extends BaseActivity {
    @BindView(R.id.expandable)
    ExpandableListView expandable;
    private List<PictureModel.ResultBean> list;
    private CommentAdapter commentAdapter;
    CommentExpandableAdapter commentExpandableAdapter;
    LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initView() {
        setToolbarTitle(getString(R.string.comments));

        Bundle bundle = getIntent().getExtras();
        products_id = bundle.getString("id");

        loadingDialog = new LoadingDialog(this);
        list = new ArrayList<>();

        commentExpandableAdapter = new CommentExpandableAdapter(groublist,parentlist, this);
        expandable.setAdapter(commentExpandableAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_footer,null);
        //expandable.addFooterView(view);

        //不可折叠
        expandable.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });

        //默认展开
        int groupCount = expandable.getCount();
        for (int i =0;i<groupCount;i++){
            expandable.expandGroup(i);
        }
    }

    @Override
    protected void initData() {
        getEvaluate();
    }

    String products_id;
    Map<PictureModel.ResultBean,List<PictureModel.ResultBean>> groublist = new HashMap<>();
    List<PictureModel.ResultBean> parentlist = new ArrayList<>();
    List<PictureModel.ResultBean> childlist = new ArrayList<>();
    List<PictureModel.ResultBean> list1 = new ArrayList<>();
    List<PictureModel.ResultBean> list2 = new ArrayList<>();
    private void getEvaluate() {
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        MainApi.getInstance(this).evaluateApi(products_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if (type == Constants.TYPE_SUCCESS) {
                    List<PictureModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(), result, PictureModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getComment_type().equals("1")) {
                            list1.add(list.get(i));
                        } else  {
                            list2.add(list.get(i));
                        }
                    }

                    for(int i = 0; i < list1.size(); i++){
                        parentlist.add(list1.get(i));
                        childlist = new ArrayList<>();
                        for(int j =0;j <list2.size();j++){
                           if(list2.get(j).getContingency_id().equals(parentlist.get(i).getContingency_id())){
                               childlist.add(list2.get(j));

                            }else {}
                        }

                        groublist.put(parentlist.get(i),childlist);
                    }

                    for (int i =0;i<parentlist.size();i++){
                        commentExpandableAdapter.refresh(expandable,i);
                    }
                } else BaseApi.showErrMsg(CommentActivity.this, result);
            }
        });
    }
}
