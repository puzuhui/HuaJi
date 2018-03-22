package com.mingxuan.huaji.layout.four.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.model.MyOrderModel;
import com.mingxuan.huaji.utils.Constants;

import java.util.List;
/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    private List<MyOrderModel.ResultBean> list;
    private Context context;
    private MyOnItemClickListener myOnItemClickListener;
    private final int NORMAL_TYPE = 0;
    private final int FOOT_TYPE = 11;
    private String id;
    public interface MyOnItemClickListener{
        void onItemClickListener(View view,int i);
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public MyOrderAdapter(List<MyOrderModel.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FOOT_TYPE){
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_footer,parent,false),FOOT_TYPE);
        }else {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order, parent, false),NORMAL_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(getItemViewType(position) == FOOT_TYPE){

        }else {
            MyOrderModel.ResultBean myOrderModel = list.get(position);
            String[] imageurl = myOrderModel.getProduct_intr().split(",");
            Glide.with(context).load(Constants.IMAGE_URL + imageurl[0]).into(holder.image);
            holder.content.setText(myOrderModel.getProducts_name());
            holder.number.setText("" + myOrderModel.getProducts_num());
            holder.money.setText("" + myOrderModel.getProducts_money() * myOrderModel.getProducts_num());
            if (myOrderModel.getOrders_flag().equals("1")) {
                holder.order_type.setText("待发货");
                //查看物流
                holder.see_btn.setVisibility(View.VISIBLE);

                holder.confirm_receipt.setVisibility(View.GONE);
                holder.delete_order.setVisibility(View.GONE);
                holder.additional_comments.setVisibility(View.GONE);
                holder.evaluate.setVisibility(View.GONE);
                holder.payment.setVisibility(View.GONE);
                holder.cancellation_of_order.setVisibility(View.GONE);
            } else if (myOrderModel.getOrders_flag().equals("2")) {
                holder.order_type.setText("买家已发货");
                //查看物流和确认收货
                holder.see_btn.setVisibility(View.VISIBLE);
                holder.confirm_receipt.setVisibility(View.VISIBLE);

                holder.delete_order.setVisibility(View.GONE);
                holder.additional_comments.setVisibility(View.GONE);
                holder.evaluate.setVisibility(View.GONE);
                holder.payment.setVisibility(View.GONE);
                holder.cancellation_of_order.setVisibility(View.GONE);
            } else if (myOrderModel.getOrders_flag().equals("3")) {
                holder.order_type.setText("待评价");
                //删除订单和评价
                holder.delete_order.setVisibility(View.VISIBLE);
                holder.evaluate.setVisibility(View.VISIBLE);

                holder.confirm_receipt.setVisibility(View.GONE);
                holder.see_btn.setVisibility(View.GONE);
                holder.additional_comments.setVisibility(View.GONE);
                holder.payment.setVisibility(View.GONE);
                holder.cancellation_of_order.setVisibility(View.GONE);
            } else if (myOrderModel.getOrders_flag().equals("4")) {
                holder.order_type.setText("等待买家付款");
                //取消订单和付款
                holder.cancellation_of_order.setVisibility(View.VISIBLE);
                holder.payment.setVisibility(View.VISIBLE);

                holder.confirm_receipt.setVisibility(View.GONE);
                holder.see_btn.setVisibility(View.GONE);
                holder.delete_order.setVisibility(View.GONE);
                holder.additional_comments.setVisibility(View.GONE);
                holder.evaluate.setVisibility(View.GONE);
            } else if (myOrderModel.getOrders_flag().equals("5")) {
                holder.order_type.setText("交易关闭");
                //删除订单
                holder.delete_order.setVisibility(View.VISIBLE);

                holder.confirm_receipt.setVisibility(View.GONE);
                holder.see_btn.setVisibility(View.GONE);
                holder.additional_comments.setVisibility(View.GONE);
                holder.evaluate.setVisibility(View.GONE);
                holder.payment.setVisibility(View.GONE);
                holder.cancellation_of_order.setVisibility(View.GONE);
            } else if (myOrderModel.getOrders_flag().equals("6")) {
                holder.order_type.setText("交易成功");
                id = list.get(position).getId();
                //删除订单和追加评论
                holder.delete_order.setVisibility(View.VISIBLE);
                holder.additional_comments.setVisibility(View.VISIBLE);
                FourApi.getInstance(context).gethjhp_products_commentApi(id , new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if (type == Constants.TYPE_SUCCESS) {
                            holder.additional_comments.setVisibility(View.GONE);
                        } else
                            holder.additional_comments.setVisibility(View.VISIBLE);
                    }
                });

                holder.confirm_receipt.setVisibility(View.GONE);
                holder.see_btn.setVisibility(View.GONE);
                holder.evaluate.setVisibility(View.GONE);
                holder.payment.setVisibility(View.GONE);
                holder.cancellation_of_order.setVisibility(View.GONE);
            }

            if (myOnItemClickListener != null) {
                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        myOnItemClickListener.onItemClickListener(holder.layout, i);
                    }
                });

                holder.see_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        myOnItemClickListener.onItemClickListener(holder.see_btn, i);
                    }
                });

                holder.delete_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        myOnItemClickListener.onItemClickListener(holder.delete_order, i);
                    }
                });

                holder.additional_comments.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        myOnItemClickListener.onItemClickListener(holder.additional_comments, i);
                    }
                });

                holder.evaluate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        myOnItemClickListener.onItemClickListener(holder.evaluate, i);
                    }
                });

                holder.cancellation_of_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        myOnItemClickListener.onItemClickListener(holder.cancellation_of_order, i);
                    }
                });

                holder.payment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        myOnItemClickListener.onItemClickListener(holder.payment, i);
                    }
                });

                holder.confirm_receipt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        myOnItemClickListener.onItemClickListener(holder.confirm_receipt, i);
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()){
            return FOOT_TYPE;
        }else
            return NORMAL_TYPE;
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView shopName;
        private ImageView image;
        private TextView content;
        private TextView number;
        private TextView money;
        private TextView see_btn,delete_order,additional_comments,evaluate,cancellation_of_order,payment,confirm_receipt;
        private TextView order_type;
        public ViewHolder(View itemView,int viewType) {
            super(itemView);
            if(viewType == FOOT_TYPE){

            }else {
                layout = (LinearLayout) itemView.findViewById(R.id.layout);
                shopName = (TextView) itemView.findViewById(R.id.shop_name);
                image = (ImageView) itemView.findViewById(R.id.image);
                content = (TextView) itemView.findViewById(R.id.content);
                number = (TextView) itemView.findViewById(R.id.number);
                money = (TextView) itemView.findViewById(R.id.money);
                see_btn = (TextView) itemView.findViewById(R.id.btn);//查看物流
                delete_order = (TextView) itemView.findViewById(R.id.delete_order);//删除订单
                additional_comments = (TextView) itemView.findViewById(R.id.additional_comments);//追加评论
                evaluate = (TextView) itemView.findViewById(R.id.evaluate);//评价
                cancellation_of_order = (TextView) itemView.findViewById(R.id.cancellation_of_order);//取消订单
                payment = (TextView) itemView.findViewById(R.id.payment);//付款
                confirm_receipt = (TextView) itemView.findViewById(R.id.confirm_receipt);//确认收货
                order_type = (TextView) itemView.findViewById(R.id.order_type);
            }
        }
    }

}
