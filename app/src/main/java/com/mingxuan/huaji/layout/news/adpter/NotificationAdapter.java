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
    private final List<NotificationModel.ResultBean> mList;
    int index;//   =1 公告通知  =2 最近通知（显示最近10条）
    private final static int TYPE_NORMAL = 1;//所有的消息
    private final static int TYPE_RECENTLY = 2;//最近的消息
    LayoutInflater layoutInflater;
    private OnItemClickListener mOnItemClickListener;

    public NotificationAdapter(Context context, List<NotificationModel.ResultBean> list,int index) {
        this.mContext = context;
        this.mList = list;
        this.index = index;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_NORMAL){
            return new ViewHolder(layoutInflater.inflate(R.layout.item_notification_all, parent, false),TYPE_NORMAL);
        }
        return new ViewHolder(layoutInflater.inflate(R.layout.item_notification, parent, false),TYPE_RECENTLY);
    }

    @Override
    public void onBindViewHolder(final NotificationAdapter.ViewHolder holder, int position) {
        NotificationModel.ResultBean notificationModel = mList.get(position);
        holder.tvTitle.setText(notificationModel.getTitle());
        holder.tvMessage.setText(notificationModel.getIntro());
        holder.tvTime.setText(notificationModel.getCreate_time().substring(0,10));
        if(mList.get(position).getState().equals("2") && index==2){
            holder.ivNotification.setVisibility(View.GONE);
        }

        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,i);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int i);
    }

    public void setOnClickListenter(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
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
        @BindView(R.id.iv_notification)
        ImageView ivNotification;
        public ViewHolder(View itemView,int viewtype) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
