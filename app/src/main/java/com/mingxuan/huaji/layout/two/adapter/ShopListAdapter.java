package com.mingxuan.huaji.layout.two.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.model.ShopListModel;
import com.mingxuan.huaji.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {
    private List<ShopListModel.ResultBean> list;
    private Context context;
    private MyOnClickListener myOnClickListener;
    private final int NORMAL_TYPE = 0;
    private final int FOOT_TYPE = 11;
    public  ShopListAdapter(List<ShopListModel.ResultBean> list,Context context){
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
    public int getItemViewType(int position) {
        if (position == list.size()){
            return FOOT_TYPE;
        }else
            return NORMAL_TYPE;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == NORMAL_TYPE){
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shop_list,parent,false),NORMAL_TYPE);
        }else
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_footer,parent,false),FOOT_TYPE);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(getItemViewType(position) == FOOT_TYPE){
            holder.default_footer_title.setText("没有更多商品了...");
        }else {
            ShopListModel.ResultBean shopListModel = list.get(position);
            if(list.get(position).getProduct_intr()!=null){
                String[] imageurl = list.get(position).getProduct_intr().split(",");
                Glide.with(context).load(Constants.IMAGE_URL + imageurl[0]).placeholder(R.mipmap.ic_launcher)
                        .centerCrop().into(holder.shop_image);
            }

            holder.shop_name.setText(shopListModel.getProduct_name());
            holder.money.setText(shopListModel.getProduct_price());
//        holder.sales_volume.setText(shopListModel.getSales_volume());
//        holder.evaluate.setText(shopListModel.getEvaluate());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    myOnClickListener.onClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView shop_image;
        private TextView shop_name,money,sales_volume,evaluate,default_footer_title;
        public ViewHolder(View itemView,int viewtype) {
            super(itemView);
            if(viewtype == FOOT_TYPE){
                default_footer_title = (TextView) itemView.findViewById(R.id.default_footer_title);
            }else {
                shop_image = (ImageView) itemView.findViewById(R.id.shop_image);
                shop_name = (TextView) itemView.findViewById(R.id.shop_name);
                money = (TextView) itemView.findViewById(R.id.money);
                sales_volume = (TextView) itemView.findViewById(R.id.sales_volume);
                evaluate = (TextView) itemView.findViewById(R.id.evaluate);
            }
        }
    }
}
