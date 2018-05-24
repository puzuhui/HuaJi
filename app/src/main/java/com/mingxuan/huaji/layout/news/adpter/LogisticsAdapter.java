package com.mingxuan.huaji.layout.news.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.news.bean.LogisticsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2018/5/24.
 * 公司：铭轩科技
 */

public class LogisticsAdapter extends RecyclerView.Adapter<LogisticsAdapter.ViewHolder> {
    private final Context mContext;
    private final List<LogisticsModel.ResultBean> mList;
    LayoutInflater layoutInflater;

    public LogisticsAdapter(Context context, List<LogisticsModel.ResultBean> list) {
        this.mContext = context;
        this.mList = list;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_logistics, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LogisticsModel.ResultBean resultBean = mList.get(position);
        holder.tvName.setText(resultBean.getNumber()+resultBean.getInfo() );
        holder.tvTime.setText(resultBean.getCreate_time());
        holder.tvNumb.setText("运单号:"+resultBean.getLogistics_no());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_numb)
        TextView tvNumb;
        @BindView(R.id.image)
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
