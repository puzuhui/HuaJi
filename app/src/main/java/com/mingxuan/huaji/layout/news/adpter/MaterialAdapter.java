package com.mingxuan.huaji.layout.news.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.news.bean.MaterialModer;
import com.mingxuan.huaji.utils.LoadingDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2018/5/17.
 * 公司：铭轩科技
 */

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder> {
    private Context mContext;
    private List<MaterialModer.ResultBean> mList;
    private LayoutInflater layoutInflater;
    OnItemClickListener onItemClickListener;

    public MaterialAdapter(Context context, List<MaterialModer.ResultBean> list) {
        this.mContext = context;
        this.mList = list;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_material, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MaterialModer.ResultBean resultBean = mList.get(position);
        holder.tvTitle.setText(resultBean.getTitle());
        holder.tvMessage.setText(resultBean.getIntro());

        if(onItemClickListener != null){
            holder.tvDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.tvDownload,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_message)
        TextView tvMessage;
        @BindView(R.id.tv_download)
        TextView tvDownload;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
