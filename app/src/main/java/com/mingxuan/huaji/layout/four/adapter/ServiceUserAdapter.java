package com.mingxuan.huaji.layout.four.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.four.model.ServiceUserModel;

import java.util.List;

/**
 * Created by Admin on 2018/3/22.
 * 公司：铭轩科技
 */

public class ServiceUserAdapter extends RecyclerView.Adapter<ServiceUserAdapter.ViewHolder> {
    private Context context;
    private List<ServiceUserModel.ResultBean> list;
    LayoutInflater layoutInflater;
    final static int NOMEL = 1135;
    public ServiceUserAdapter(Context context,List<ServiceUserModel.ResultBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ServiceUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_service_user,parent,false));
    }

    @Override
    public void onBindViewHolder(ServiceUserAdapter.ViewHolder holder, int position) {
        ServiceUserModel.ResultBean resultBeans = list.get(position);
        holder.one.setText(resultBeans.getMobile());
        if(!TextUtils.isEmpty(resultBeans.getInfo_name())){
            holder.two.setText(resultBeans.getInfo_name());
        }else {
            holder.two.setText("无");
        }
        holder.three.setText(resultBeans.getReal_name());
        if(!TextUtils.isEmpty(resultBeans.getPhone())){
            holder.four.setText(resultBeans.getPhone());
        }else {
            holder.four.setText("无");
        }
        holder.five.setText(resultBeans.getNet_time());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(list.size() == 0){
            return NOMEL;
        }
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView one,two,three,four,five;
        public ViewHolder(View itemView) {
            super(itemView);
            one = (TextView) itemView.findViewById(R.id.one);
            two = (TextView) itemView.findViewById(R.id.two);
            three = (TextView) itemView.findViewById(R.id.three);
            four = (TextView) itemView.findViewById(R.id.four);
            five = (TextView) itemView.findViewById(R.id.five);
        }
    }
}
