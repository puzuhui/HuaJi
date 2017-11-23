package com.mingxuan.huaji.layout.two.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.model.ShopListModel;

import java.util.List;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {
    private List<ShopListModel> list;
    private Context context;
    private MyOnClickListener myOnClickListener;
    public  ShopListAdapter(List<ShopListModel> list,Context context){
        this.list = list;
        this.context = context;
    }

    public interface MyOnClickListener{
        void onClick(View view,int position);
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener){
        this.myOnClickListener = myOnClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shop_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ShopListModel shopListModel = list.get(position);
        holder.shop_name.setText(shopListModel.getShopname());
        holder.money.setText(""+shopListModel.getMoney());
        holder.sales_volume.setText(shopListModel.getSales_volume());
        holder.evaluate.setText(shopListModel.getEvaluate());

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
        private ImageView shop_image;
        private TextView shop_name,money,sales_volume,evaluate;
        public ViewHolder(View itemView) {
            super(itemView);
            shop_image = (ImageView) itemView.findViewById(R.id.shop_image);
            shop_name = (TextView) itemView.findViewById(R.id.shop_name);
            money = (TextView) itemView.findViewById(R.id.money);
            sales_volume = (TextView) itemView.findViewById(R.id.sales_volume);
            evaluate = (TextView) itemView.findViewById(R.id.evaluate);
        }
    }
}
