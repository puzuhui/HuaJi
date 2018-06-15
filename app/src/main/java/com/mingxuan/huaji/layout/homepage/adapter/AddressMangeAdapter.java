package com.mingxuan.huaji.layout.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.mine.bean.MyAdressModel;

import java.util.List;

/**
 * Created by Admin on 2018/5/29.
 * 公司：铭轩科技
 */

public class AddressMangeAdapter extends RecyclerView.Adapter<AddressMangeAdapter.ViewHolder> {
    private final Context mContext;
    private final List<MyAdressModel.ResultBean> mList;
    private LayoutInflater layoutInflater;

    public AddressMangeAdapter(Context context, List<MyAdressModel.ResultBean> list){
        this.mContext = context;
        this.mList = list;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public AddressMangeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_address,parent,false));
    }

    @Override
    public void onBindViewHolder(final AddressMangeAdapter.ViewHolder holder, int position) {
        MyAdressModel.ResultBean resultBean = mList.get(position);
        holder.name.setText(resultBean.getConsignee());
        holder.phone.setText(resultBean.getPhone());
        if(resultBean.getDefault_flag() == 1){
            holder.tv_default.setVisibility(View.VISIBLE);
        }
        holder.adress.setText(resultBean.getAddress());

        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i  = holder.getPosition();
                    onItemClickListener.onItemClick(holder.itemView,i);
                }
            });
        }
    }

    OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,adress,tv_default;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            adress = (TextView) itemView.findViewById(R.id.adress);
            tv_default =  (TextView) itemView.findViewById(R.id.tv_default);
        }
    }
}
