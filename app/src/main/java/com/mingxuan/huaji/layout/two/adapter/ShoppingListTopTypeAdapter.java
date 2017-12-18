package com.mingxuan.huaji.layout.two.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.model.ShoppingListTopTypeModel;

import java.util.List;

import static com.mingxuan.huaji.utils.UIUtils.getColor;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class ShoppingListTopTypeAdapter extends RecyclerView.Adapter<ShoppingListTopTypeAdapter.ViewHolder> {
    private List<ShoppingListTopTypeModel.ResultBean> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;
    private final int NORMAL_TYPE = 0;
    private final int HEAD_TYPE = 11;
    public interface OnItemClickListener{
        void onClick(View v,int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public ShoppingListTopTypeAdapter(List<ShoppingListTopTypeModel.ResultBean> list,Context context){
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
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
        if(getItemViewType(position) == HEAD_TYPE){
            holder.default_footer_title.setTextColor(context.getResources().getColor(R.color.redDark));
            holder.default_footer_title.setText("全部");
            if(onItemClickListener != null){
                holder.default_footer_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        onItemClickListener.onClick(holder.default_footer_title, i);
                    }
                });
            }
        }else {
            holder.textView.setText(list.get(position-1).getName());

            if (onItemClickListener != null) {
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = holder.getPosition();
                        onItemClickListener.onClick(holder.textView, i);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,default_footer_title;
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
