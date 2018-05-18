package com.mingxuan.huaji.layout.news.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.news.bean.NotificationModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2018/5/16.
 * 公司：铭轩科技
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private final Context mContext;
    private final List<NotificationModel> mList;
    int index;
    private final static int TYPE_NORMAL = 1;//所有的消息
    private final static int TYPE_RECENTLY = 2;//最近的消息
    LayoutInflater layoutInflater;

    public NotificationAdapter(Context context, List<NotificationModel> list,int index) {
        this.mContext = context;
        this.mList = list;
        this.index = index;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(getItemViewType(viewType) == TYPE_NORMAL){
            return new ViewHolder(layoutInflater.inflate(R.layout.item_notification_all, parent, false));
        }
        return new ViewHolder(layoutInflater.inflate(R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolder holder, int position) {
        NotificationModel notificationModel = mList.get(position);
        holder.tvTitle.setText(notificationModel.getTitile());
        holder.tvMessage.setText(notificationModel.getMessage());
        holder.tvTime.setText(notificationModel.getTime());
    }

    @Override
    public int getItemCount() {
        if(index == 2 ){
            return mList.size()>10 ? 10:mList.size();
        }
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(index == 1){
            return TYPE_NORMAL;
        }
        return TYPE_RECENTLY;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_message)
        TextView tvMessage;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
