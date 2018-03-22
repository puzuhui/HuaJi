package com.mingxuan.huaji.layout.two.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.model.ShoppingMallModel;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class ShoppingMallAdapter extends RecyclerView.Adapter<ShoppingMallAdapter.ViewHolder>{
    private List<ShoppingMallModel> list;
    private Context context;
    public ShoppingMallAdapter(List<ShoppingMallModel> list,Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
