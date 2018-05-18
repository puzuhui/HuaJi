package com.mingxuan.huaji.layout.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.homepage.bean.CardInfor;

import java.util.List;

/**
 * Created by Admin on 2018/4/16.
 * 公司：铭轩科技
 */

public class ChoosePhoneAdapter extends RecyclerView.Adapter<ChoosePhoneAdapter.ViewHolder> {
    private List<CardInfor.ResultBean> list;
    private Context context;
    LayoutInflater layoutInflater;
    //    private List<Boolean> isClicks;//控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public ChoosePhoneAdapter(List<CardInfor.ResultBean> list,Context context){
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ChoosePhoneAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_phone,parent,false));
    }

    @Override
    public void onBindViewHolder(final ChoosePhoneAdapter.ViewHolder holder, int position) {
        if(list.size() > 0){
            final CardInfor.ResultBean resultBean= list.get(position);
            holder.textView.setText(resultBean.getNumber());

            if( resultBean.isIschecked() ){
                holder.textView.setTextColor(context.getResources().getColor(R.color.redDark));
                holder.textView.setBackgroundResource(R.drawable.size_red);
            }else{
                holder.textView.setTextColor(context.getResources().getColor(R.color.transparent80));
                holder.textView.setBackgroundResource(R.drawable.size_black);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    for (int i = 0; i < list.size() ; i++) {
                        list.get(i).setIschecked(false);
                    }//状态恢复
                    resultBean.setIschecked(!resultBean.isIschecked());
                    notifyDataSetChanged();

                    onItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
