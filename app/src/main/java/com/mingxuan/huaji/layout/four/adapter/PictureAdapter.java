package com.mingxuan.huaji.layout.four.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.four.model.PictureModel;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    private List<PictureModel> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemClickListener mOnItemClickListener;
    public PictureAdapter(List<PictureModel> list,Context context){
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_picture,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PictureModel pictureModel = list.get(position);
        //holder.imageView.setImageBitmap(pictureModel.getBitmap());
        Glide.with(context).load(pictureModel.getImagePath()).centerCrop().placeholder(R.mipmap.ic_launcher).into(holder.imageView);
        if(mOnItemClickListener != null){
            holder.imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = holder.getPosition();
                    mOnItemClickListener.onItemClick(holder.imageView1,i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,imageView1;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            imageView1 = (ImageView) itemView.findViewById(R.id.image_del);
        }
    }
}
