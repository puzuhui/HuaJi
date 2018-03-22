package com.mingxuan.huaji.layout.two.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.model.CardInfor;

import java.util.List;

/**
 * Created by Admin on 2018/3/16.
 * 公司：铭轩科技
 */

public class CardInformationAdapter extends RecyclerView.Adapter<CardInformationAdapter.ViewHolder> {
    Context context;
    List<CardInfor> list;
    LayoutInflater layoutInflater;
    MyOnClickListener myOnClickListener;
    public CardInformationAdapter(Context context,List<CardInfor> list){
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    public interface MyOnClickListener{
        void onClick(View view,int i);
    }

    public void setOnClickListener(MyOnClickListener myOnClickListener){
        this.myOnClickListener = myOnClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_card_information,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.content.setText(list.get(position).getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                myOnClickListener.onClick(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,content;
        public ViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            content = (TextView) itemView.findViewById(R.id.content);

        }
    }
}
