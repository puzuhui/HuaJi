package com.mingxuan.huaji.layout.four.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.four.activity.MyShoppingCartActivity;
import com.mingxuan.huaji.layout.four.model.MyShoppingCarModel;
import com.mingxuan.huaji.utils.NumberAddSubView;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class MyShoppingCarAdapter extends RecyclerView.Adapter<MyShoppingCarAdapter.ViewHolder> {
    private final Context context;
    private final List<MyShoppingCarModel.ResultBean> list;
    private CheckBox checkAll;
    private final TextView money;
    private int TYPE_NORMOL = 1001;
    private int TYPE_EMPTY = 1002;

    public MyShoppingCarAdapter(Context context, final List<MyShoppingCarModel.ResultBean> list, CheckBox checkAll, TextView money) {
        this.context = context;
        this.list = list;
        this.checkAll = checkAll;
        this.money = money;
        //计算总价
        showTotalPrice();
        //设置item点击事件
        setListener();
        checkAll();
    }

    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                MyShoppingCarModel.ResultBean myShoppingCarModel = list.get(position);
                myShoppingCarModel.setSelected(!myShoppingCarModel.isSelected());
                notifyItemChanged(position);
                checkAll();
                showTotalPrice();
            }
        });

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = checkAll.isChecked();
                checked_null(checked);
                showTotalPrice();
            }
        });

    }

    /**
     * 全选和不选
     *
     * @param checked
     */
    private void checked_null(boolean checked) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                MyShoppingCarModel.ResultBean myShoppingCarModel = list.get(i);
                myShoppingCarModel.setSelected(checked);
                notifyItemChanged(i);
            }
        }
    }


    private void checkAll() {
        if (list != null && list.size() > 0) {
            int number = 0;
            for (int i = 0; i < list.size(); i++) {
                MyShoppingCarModel.ResultBean myShoppingCarModel = list.get(i);
                if (!myShoppingCarModel.isSelected()) {
                    //非全选
                    checkAll.setChecked(false);
                } else {
                    //全选
                    number++;
//                    checkAll.setChecked(true);
                }
            }

            if (number == list.size()) {
                checkAll.setChecked(true);
            }else {
                checkAll.setChecked(false);
            }
        }
    }

    private void showTotalPrice() {
        money.setText("" + getTotalPrice());
    }

    /**
     * 计算总价
     *
     * @return
     */
    public double getTotalPrice() {
        double totalPrice = 0.0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                MyShoppingCarModel.ResultBean myShoppingCarModel = list.get(i);
                if (myShoppingCarModel.isSelected()) {
                    totalPrice = totalPrice + Double.valueOf(myShoppingCarModel.getNumber()) * Double.valueOf(myShoppingCarModel.getProduct_price());
                }
            }
        }
        return totalPrice;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_EMPTY){
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.shopping_car_empty, parent, false),TYPE_EMPTY);
        }
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shopping_car, parent, false),TYPE_NORMOL);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_EMPTY){

        }else {
            final MyShoppingCarModel.ResultBean myShoppingCarModel = list.get(position);
            holder.shop_name.setText(myShoppingCarModel.getCreate_name());
            holder.checkbox.setChecked(myShoppingCarModel.isSelected());
            holder.shop_content.setText(myShoppingCarModel.getProduct_name());
            holder.shop_money.setText("" + myShoppingCarModel.getProduct_price());
            holder.numberAddSubView.setValue(myShoppingCarModel.getNumber());
            holder.numberAddSubView.setMaxValue(myShoppingCarModel.getProduct_inventory());//库存
            String[] imageurl = myShoppingCarModel.getProduct_intr().split(",");
            Glide.with(context).load("http://125.65.82.219:8080"+imageurl[0]).into(holder.image);
            holder.checkbox.setTag(position);
            if(onItemClickListener != null){
                holder.checkbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
                        myShoppingCarModel.setSelected(!myShoppingCarModel.isSelected());
                        notifyItemChanged(position);
                        checkAll();
                        showTotalPrice();
                    }
                });


                //设置数字加减回调
                holder.numberAddSubView.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
                    @Override
                    public void addNumber(View view, int value) {
//                    if(value > myShoppingCarModel.getProduct_inventory()){
//                        ToastUtil.makeToast(context,"亲，已经没有更多的货了");
//                    }
                        myShoppingCarModel.setNumber(value);
                        showTotalPrice();
                    }

                    @Override
                    public void subNumner(View view, int value) {
                        myShoppingCarModel.setNumber(value);
                        showTotalPrice();
                    }
                });

                holder.del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getPosition();
                        onItemClickListener.onItemClickListener(holder.del_btn,position);
                    }
                });
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(list.size() != 0){
            return TYPE_NORMOL;
        }
        return TYPE_EMPTY;
    }

    @Override
    public int getItemCount() {
        return list.size()==0 ? 1 : list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView shop_name, shop_content, shop_money;
        private NumberAddSubView numberAddSubView;
        private ImageView del_btn, image;
        private CheckBox checkbox;
        public ViewHolder(final View itemView,int viewtype) {
            super(itemView);
            if(viewtype == TYPE_EMPTY){

            }else {
                shop_name = (TextView) itemView.findViewById(R.id.shop_name);
                shop_content = (TextView) itemView.findViewById(R.id.shop_content);
                shop_money = (TextView) itemView.findViewById(R.id.shop_money);
                numberAddSubView = (NumberAddSubView) itemView.findViewById(R.id.numberAddSubView);
                del_btn = (ImageView) itemView.findViewById(R.id.del_btn);
                image = (ImageView) itemView.findViewById(R.id.image);
                checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClickListener(v, getLayoutPosition());
                        }
                    }
                });
            }

        }
    }

    //回调点击事件的监听
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CheckBox getCheckAll() {
        return checkAll;
    }

    public void setCheckAll(CheckBox checkAll) {
        this.checkAll = checkAll;
    }

}
