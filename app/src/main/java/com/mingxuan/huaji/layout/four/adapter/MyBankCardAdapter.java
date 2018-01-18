package com.mingxuan.huaji.layout.four.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.four.model.MyBankCardModel;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class MyBankCardAdapter extends RecyclerView.Adapter<MyBankCardAdapter.ViewHolder> {
    Context context;
    List<MyBankCardModel.ResultBean> list;
    public MyBankCardAdapter(Context context,List<MyBankCardModel.ResultBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bank_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyBankCardModel.ResultBean resultBean = list.get(position);
        holder.bank_card_tv_name.setText(resultBean.getBank());
        //  1储蓄卡  2、信用卡  3.准贷记卡  4.预付费卡
        if(resultBean.getBank_type().equals("1")){
            holder.bank_card_tv_type.setText("储蓄卡");
        }else if(resultBean.getBank_type().equals("2")){
            holder.bank_card_tv_type.setText("信用卡");
        }else if(resultBean.getBank_type().equals("3")){
            holder.bank_card_tv_type.setText("准贷记卡");
        }else if(resultBean.getBank_type().equals("4")){
            holder.bank_card_tv_type.setText("预付费卡");
        }
        holder.bank_card_tv_numb.setText(resultBean.getBank_number());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bank_card_iv;
        TextView bank_card_tv_name,bank_card_tv_type,bank_card_tv_numb;
        public ViewHolder(View itemView) {
            super(itemView);
            bank_card_iv = (ImageView) itemView.findViewById(R.id.bank_card_iv);
            bank_card_tv_name = (TextView) itemView.findViewById(R.id.bank_card_tv_name);
            bank_card_tv_type = (TextView) itemView.findViewById(R.id.bank_card_tv_type);
            bank_card_tv_numb = (TextView) itemView.findViewById(R.id.bank_card_tv_numb);
        }
    }
}
