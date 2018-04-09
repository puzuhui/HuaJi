package com.mingxuan.huaji.layout.four.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.four.model.IncomeModel;
import com.mingxuan.huaji.layout.four.model.ServiceUserModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Admin on 2018/3/26.
 * 公司：铭轩科技
 */

public class ServiceChannelAdapter extends RecyclerView.Adapter<ServiceChannelAdapter.ViewHolder> {

    private List<IncomeModel.TwoBean> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public ServiceChannelAdapter(List<IncomeModel.TwoBean> list, Context context) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_service_channel, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IncomeModel.TwoBean twoBean = list.get(position);
        holder.tvNumb.setText(twoBean.getId());
        holder.tvName.setText(twoBean.getName());
        holder.tvServiceNumb.setText(twoBean.getUser_phone());
        holder.one.setText(twoBean.getNewxj());
        holder.two.setText(twoBean.getNewxjje());
        holder.three.setText(twoBean.getXjsl());
        holder.four.setText(twoBean.getSljl_ljyxze());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_numb)
        TextView tvNumb;
        @BindView(R.id.tv_service_numb)
        TextView tvServiceNumb;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.one)
        TextView one;
        @BindView(R.id.two)
        TextView two;
        @BindView(R.id.three)
        TextView three;
        @BindView(R.id.four)
        TextView four;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
