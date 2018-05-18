package com.mingxuan.huaji.layout.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.mine.bean.PictureModel;
import com.mingxuan.huaji.base.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<PictureModel.ResultBean> list;
    private List<PictureModel.ResultBean> list2;
    private Context context;
    private LayoutInflater layoutInflater;

    public CommentAdapter(List<PictureModel.ResultBean> list,List<PictureModel.ResultBean> list2, Context context){
        this.context = context;
        this.list = list;
        this.list2 = list2;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(layoutInflater.inflate(R.layout.item_comment,parent,false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PictureModel.ResultBean resultBean = list.get(position);
            holder.name.setText(resultBean.getCreate_name());
            holder.ratingBar.setRating(resultBean.getComment_level());
            holder.ratingBar.setIsIndicator(true);//不能滑动
            holder.content.setText(resultBean.getComment_content());
            holder.time.setText(resultBean.getCreate_time());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 5);
            holder.recyclerView.setLayoutManager(gridLayoutManager);

            String[] imageurl = resultBean.getPic_dz().split(",");
            holder.recyclerView.setAdapter(new ChildAdapter(imageurl));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,content,time;
        RatingBar ratingBar;
        RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            content = (TextView) itemView.findViewById(R.id.content);
            time = (TextView) itemView.findViewById(R.id.time);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);
        }
    }

    /**
     * 子类适配器
     */
    public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
        private String[] image;
        private LayoutInflater layoutInflater;
        private final static int TYPE_NORMOL = 1001;
        private final static int TYPE_MORE = 1002;
        public ChildAdapter(String[] image){
            this.image = image;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public ChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(layoutInflater.inflate(R.layout.item_image,parent,false));
        }

        @Override
        public void onBindViewHolder(ChildAdapter.ViewHolder holder, int position) {
            Glide.with(context).load(Constants.IMAGE_URL+image[position]).placeholder(R.mipmap.ic_launcher).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return image.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.image);
            }
        }
    }

}
