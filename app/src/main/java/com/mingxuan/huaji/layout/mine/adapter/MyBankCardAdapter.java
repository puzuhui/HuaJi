package com.mingxuan.huaji.layout.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.mine.bean.MyBankCardModel;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MyBankCardModel.ResultBean resultBean = list.get(position);
        holder.bank_card_tv_name.setText(resultBean.getBankX()+"("+resultBean.getBankzh()+")");
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

        holder.bank_card_tv_numb.setText("**** **** **** "+resultBean.getBank_number().substring(resultBean.getBank_number().length()-4,resultBean.getBank_number().length()));
        switch (resultBean.getBank_for_codetype()){
            case "ABC":
                setImageView(R.mipmap.abc,holder);
                break;
            case "BOC":
                setImageView(R.mipmap.boc,holder);
                break;
            case "CCB":
                setImageView(R.mipmap.ccb,holder);
                break;
            case "CEB":
                setImageView(R.mipmap.ceb,holder);
                break;
            case "CIB":
                setImageView(R.mipmap.cib,holder);
                break;
            case "CITIC":
                setImageView(R.mipmap.citic,holder);
                break;
            case "CMB":
                setImageView(R.mipmap.cmb,holder);
                break;
            case "CMBC":
                setImageView(R.mipmap.cmbc,holder);
                break;
            case "COMM":
                setImageView(R.mipmap.comm,holder);
                break;
            case "GDB":
                setImageView(R.mipmap.gdb,holder);
                break;
            case "HXBANK":
                setImageView(R.mipmap.hxbank,holder);
                break;
            case "ICBC":
                setImageView(R.mipmap.icbc,holder);
                break;
            case "PSBC":
                setImageView(R.mipmap.psbc,holder);
                break;
            case "SPABANK":
                setImageView(R.mipmap.spabank,holder);
                break;
            case "SPDB":
                setImageView(R.mipmap.spdb,holder);
                break;
        }

        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = holder.getLayoutPosition();
                    onItemClickListener.onClickListener(i,holder.itemView);
                }
            });
        }
    }

    //设置图片
    private void setImageView(int imageViewId,ViewHolder holder){
        Glide.with(context).load(imageViewId).into(holder.bank_card_iv);
        Glide.with(context).load(imageViewId).into(holder.bank_card_iv_two);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener{
        void onClickListener(int i,View view);
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bank_card_iv,bank_card_iv_two;
        TextView bank_card_tv_name,bank_card_tv_type,bank_card_tv_numb;
        public ViewHolder(View itemView) {
            super(itemView);
            bank_card_iv = (ImageView) itemView.findViewById(R.id.bank_card_iv);
            bank_card_iv_two =  (ImageView) itemView.findViewById(R.id.bank_card_iv_two);
            bank_card_tv_name = (TextView) itemView.findViewById(R.id.bank_card_tv_name);
            bank_card_tv_type = (TextView) itemView.findViewById(R.id.bank_card_tv_type);
            bank_card_tv_numb = (TextView) itemView.findViewById(R.id.bank_card_tv_numb);
        }
    }
}
