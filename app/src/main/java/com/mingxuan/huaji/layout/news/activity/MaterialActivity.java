package com.mingxuan.huaji.layout.news.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.news.adpter.MaterialAdapter;
import com.mingxuan.huaji.layout.news.bean.MaterialModer;
import com.mingxuan.huaji.network.api.TwoApi;
import com.mingxuan.huaji.utils.DownloadUtil;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialActivity extends BaseActivity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<MaterialModer.ResultBean> list;
    MaterialAdapter materialAdapter;
    boolean isDownLoad = false;
    LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_material;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);

        materialAdapter = new MaterialAdapter(this, list);
        recyclerview.setAdapter(materialAdapter);

        materialAdapter.setOnItemClickListener(new MaterialAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                if(!isDownLoad){
                    DownloadUtil.get().download(list.get(position).getHref(), list.get(position).getTitle(),"huaji", new DownloadUtil.OnDownloadListener() {
                        @Override
                        public void onDownloadSuccess() {
                            isDownLoad = false;
                            getNotificationManager().notify(1,getNotification(list.get(position).getTitle()+"下载成功",-1));
                        }

                        @Override
                        public void onDownloading(int progress) {
                            isDownLoad = true;
                            getNotificationManager().notify(1,getNotification(list.get(position).getTitle()+"下载中...",progress));
                        }

                        @Override
                        public void onDownloadFailed() {
                            isDownLoad = false;
                            getNotificationManager().notify(1,getNotification(list.get(position).getTitle()+"下载失败",-1));
                        }
                    });
                }else {
                    ToastUtil.makeToast(MaterialActivity.this,"请等待上个任务完成");
                }

            }
        });

        getDownLoad();
    }

    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title , int progress){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(title);
        builder.setSmallIcon(R.mipmap.hjhplogo64);
        if(progress > 0 ){
            builder.setContentText(progress+"%");
            builder.setProgress(100,progress,false);
        }
        return  builder.build();
    }

    @OnClick(R.id.back_btn)
    public void onViewClicked() {
        finish();
    }

    private void getDownLoad(){
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        TwoApi.getInstance(this).getdownloadApi(new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<MaterialModer.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(), result, MaterialModer.ResultBean.class);
                    list.addAll(resultBeans);

                    materialAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
