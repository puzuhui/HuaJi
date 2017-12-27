package com.mingxuan.huaji.layout.two.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.model.ShopListModel;
import com.mingxuan.huaji.layout.two.model.ShoppingListTopTypeModel;

import java.util.ArrayList;
import java.util.List;

import static com.mingxuan.huaji.utils.UIUtils.getColor;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class ShoppingListTopTypeAdapter extends RecyclerView.Adapter<ShoppingListTopTypeAdapter.ViewHolder> {
    private List<ShoppingListTopTypeModel.ResultBean> mlist;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;
    private final int NORMAL_TYPE = 0;
    private final int HEAD_TYPE = 11;
    private List<Boolean> ischeck ;//记录选中的状态
    public interface OnItemClickListener{
        void onClick(View v,int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public ShoppingListTopTypeAdapter(List<ShoppingListTopTypeModel.ResultBean> list,Context context){
        this.mlist = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        ischeck = new ArrayList<>();
        for (int i = 0; i <mlist.size()+1 ; i++) {
            if(i == 0){
                ischeck.add(true);
            }else
                ischeck.add(false);
        }
    }

    public void setDate(List<ShoppingListTopTypeModel.ResultBean> list) {
        this.mlist = list;

    }


    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return HEAD_TYPE;
        }else
            return NORMAL_TYPE;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == HEAD_TYPE){
            return new ViewHolder(layoutInflater.inflate(R.layout.layout_footer,parent,false),HEAD_TYPE);
        }else
            return new ViewHolder(layoutInflater.inflate(R.layout.item_shoppinglist_top_type,parent,false),NORMAL_TYPE);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ischeck.add(false);
        if(getItemViewType(position) == HEAD_TYPE){
            if(!ischeck.get(position)){
                holder.default_footer_title.setTextColor(context.getResources().getColor(R.color.transparent80));
            }else
                holder.default_footer_title.setTextColor(context.getResources().getColor(R.color.redDark));
            holder.default_footer_title.setText("全部");
            if(onItemClickListener != null){
                holder.default_footer_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        onItemClickListener.onClick(holder.default_footer_title, i);
                        for (int j = 0; j < ischeck.size(); j++) {
                            ischeck.set(j,false);
                        }
                        ischeck.set(i,true);
                        notifyDataSetChanged();
                    }
                });
            }
        }else {
            holder.textView.setText(mlist.get(position-1).getName());
            if(!ischeck.get(position)){
                holder.textView.setTextColor(context.getResources().getColor(R.color.transparent80));
            }else
                holder.textView.setTextColor(context.getResources().getColor(R.color.redDark));
            if (onItemClickListener != null) {
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        onItemClickListener.onClick(holder.textView, i);
                        for (int j = 0; j < ischeck.size(); j++) {
                            ischeck.set(j,false);
                        }
                        ischeck.set(i,true);
                        notifyDataSetChanged();
                    }
                });
            }
        }
    }


    @Override
    public int getItemCount() {
        return mlist.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView default_footer_title;
        public ViewHolder(View itemView,int viewtype) {
            super(itemView);
            if(viewtype == HEAD_TYPE){
                default_footer_title = (TextView) itemView.findViewById(R.id.default_footer_title);
            }else {
                textView = (TextView) itemView.findViewById(R.id.textView2);
            }
        }
    }
}
