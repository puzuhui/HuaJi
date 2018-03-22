package com.mingxuan.huaji.layout.four.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.four.model.IntergralMolder;

import java.util.List;

/**
 * Created by Admin on 2018/3/12.
 * 公司：铭轩科技
 */

public class IntergralAdapter extends RecyclerView.Adapter<IntergralAdapter.ViewHolder> {
    Context context;
    List<IntergralMolder.ResultBean> list;
    LayoutInflater layoutInflater;
    public IntergralAdapter(Context context,List<IntergralMolder.ResultBean> list){
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public IntergralAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_intergral,parent,false));
    }

    @Override
    public void onBindViewHolder(IntergralAdapter.ViewHolder holder, int position) {
        IntergralMolder.ResultBean resultBean = list.get(position);
        holder.tv_context.setText(resultBean.getPoint_type());
        holder.tv_time.setText(resultBean.getCreate_time());
        holder.tv_number.setText(resultBean.getPoint());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_context,tv_time,tv_number;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_context = (TextView) itemView.findViewById(R.id.tv_context);
        }
    }
}
