package com.mingxuan.huaji.layout.four.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.four.model.DateMolder;

import java.util.List;

/**
 * Created by Admin on 2018/3/27.
 * 公司：铭轩科技
 */

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {
    Context context;
    List<DateMolder> list;
    LayoutInflater layoutInflater;
    public DateAdapter(Context context,List<DateMolder> list){
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_date,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DateMolder dateMolder = list.get(position);
        holder.textView.setText(dateMolder.getDate());
        holder.checkbox.setChecked(dateMolder.isIsselected());
        for (int i = 0; i < list.size() ; i++) {
            dateMolder.setIsselected(false);
        }

        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int position = holder.getPosition();
                    dateMolder.setIsselected(true);
                    notifyDataSetChanged();
                    onItemClickListener.onItemClickListener(holder.checkbox, position);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int position = holder.getPosition();
                    dateMolder.setIsselected(true);
                    notifyDataSetChanged();
                    onItemClickListener.onItemClickListener(holder.itemView, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //回调点击事件的监听
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkbox;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.date);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);


        }
    }
}
