package com.mingxuan.huaji.layout.four.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.four.model.MyAdressModel;

import java.util.List;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class MyAdressAdapter extends RecyclerView.Adapter<MyAdressAdapter.ViewHolder> {
    private Context context;
    private List<MyAdressModel.ResultBean> list;
    private AdressOnClickListener adressOnClickListener;

    public MyAdressAdapter(Context context,List<MyAdressModel.ResultBean> list){
        this.context = context;
        this.list = list;
    }

    public interface AdressOnClickListener{
        void onClick(View view,int position);
    }

    public void setAdressOnClickListener(AdressOnClickListener adressOnClickListener){
        this.adressOnClickListener = adressOnClickListener;
    }

    @Override
    public MyAdressAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_myadress,parent,false));
    }

    @Override
    public void onBindViewHolder(final MyAdressAdapter.ViewHolder holder, int position) {
        final MyAdressModel.ResultBean myAdressModel = list.get(position);
        holder.name.setText(myAdressModel.getConsignee());
        holder.phone.setText(myAdressModel.getPhone());
        holder.adress.setText(myAdressModel.getAddress());
        if(myAdressModel.getDefault_flag() == 1){
            holder.checkbox.setText("默认地址");
            holder.checkbox.setTextColor(context.getResources().getColor(R.color.redDark));
            holder.checkbox.setChecked(true);
        }else {
            holder.checkbox.setText("设为默认地址");
            holder.checkbox.setTextColor(context.getResources().getColor(R.color.transparent80));
            holder.checkbox.setChecked(false);
        }

        if(adressOnClickListener != null){
            holder.checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i  = holder.getPosition();
                    adressOnClickListener.onClick(holder.checkbox,i);
                }
            });

            holder.compile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i  = holder.getPosition();
                    adressOnClickListener.onClick(holder.compile,i);
                }
            });

            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i  = holder.getPosition();
                    adressOnClickListener.onClick(holder.del,i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,adress,compile,del;
        CheckBox checkbox;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            adress = (TextView) itemView.findViewById(R.id.adress);
            compile = (TextView) itemView.findViewById(R.id.compile);
            del = (TextView) itemView.findViewById(R.id.del);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
