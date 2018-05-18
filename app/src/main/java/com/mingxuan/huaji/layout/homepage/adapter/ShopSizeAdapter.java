package com.mingxuan.huaji.layout.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.homepage.bean.ShopSizeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class ShopSizeAdapter extends RecyclerView.Adapter<ShopSizeAdapter.ViewHolder> {
    private Context context;
    private List<ShopSizeModel> list;
    private MyOnClickListener myOnClickListener;
    private List<Boolean> isClicks;//控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色

    public interface MyOnClickListener{
        void onclick(View view,int position);
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener){
        this.myOnClickListener = myOnClickListener;
    }

    public ShopSizeAdapter(Context context,List<ShopSizeModel> list){
        this.context = context;
        this.list = list;
        isClicks = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            if(i==0){
                isClicks.add(true);
            }
            isClicks.add(false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_size,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ShopSizeModel shopSizeModel = list.get(position);
        holder.size.setText(shopSizeModel.getSize());
        if(isClicks.get(position)){
            holder.size.setTextColor(context.getResources().getColor(R.color.redDark));
        }else{
            holder.size.setTextColor(context.getResources().getColor(R.color.transparent80));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                for(int i = 0; i <isClicks.size();i++){
                    isClicks.set(i,false);
                }
                isClicks.set(position,true);
                notifyDataSetChanged();

                myOnClickListener.onclick(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView size;
        public ViewHolder(View itemView) {
            super(itemView);
            size = (TextView) itemView.findViewById(R.id.size);
        }
    }
}
