package com.mingxuan.huaji.layout.two.adapter;

import android.content.Context;
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

public class ShoppingMallAdapter extends BaseAdapter implements View.OnClickListener{
    private List<ShoppingMallModel> list;
    private Context context;
    final int VIEW_TYPE = 3;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    private Callback callback;

    public ShoppingMallAdapter(List<ShoppingMallModel> list,Context context,Callback callback){
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    public interface Callback{
        public void click(View v);
    }

    @Override
    public void onClick(View v) {
        callback.click(v);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_1;
        }else if(position == 4){
            return TYPE_3;
        }
        return TYPE_2;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = null;
        ViewHoder1 viewHoder1 = null;
        ViewHoder2 viewHoder2 = null;
        int type = getItemViewType(position);
        if(convertView == null){
            switch (type){
                case TYPE_1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping_mall_type_one,null);
                    viewHoder = new ViewHoder();
                    viewHoder.type_one_title = (TextView) convertView.findViewById(R.id.type_one_title);
                    viewHoder.type_one_more = (TextView) convertView.findViewById(R.id.type_one_more);
                    viewHoder.type_one_one = (LinearLayout) convertView.findViewById(R.id.type_one_one);
                    viewHoder.type_one_two = (LinearLayout) convertView.findViewById(R.id.type_one_two);
                    viewHoder.type_one_three = (LinearLayout) convertView.findViewById(R.id.type_one_three);
                    viewHoder.type_one_four = (LinearLayout) convertView.findViewById(R.id.type_one_four);
                    viewHoder.type_one_five = (LinearLayout) convertView.findViewById(R.id.type_one_five);
                    viewHoder.type_one_six = (LinearLayout) convertView.findViewById(R.id.type_one_six);
                    convertView.setTag(viewHoder);
                    break;
                case TYPE_2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping_mall_type_two,null);
                    viewHoder1 = new ViewHoder1();
                    viewHoder1.type_two_title = (TextView) convertView.findViewById(R.id.type_two_title);
                    viewHoder1.type_two_more = (TextView) convertView.findViewById(R.id.type_two_more);
                    viewHoder1.type_two_one = (LinearLayout) convertView.findViewById(R.id.type_two_one);
                    viewHoder1.type_two_two = (LinearLayout) convertView.findViewById(R.id.type_two_two);
                    viewHoder1.type_two_three = (LinearLayout) convertView.findViewById(R.id.type_two_three);
                    convertView.setTag(viewHoder1);
                    break;
                case TYPE_3:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping_mall_type_three,null);
                    viewHoder2 = new ViewHoder2();
                    viewHoder2.type_three_title = (TextView) convertView.findViewById(R.id.type_three_title);
                    viewHoder2.type_three_more = (TextView) convertView.findViewById(R.id.type_three_more);
                    viewHoder2.type_three_one = (LinearLayout) convertView.findViewById(R.id.type_three_one);
                    viewHoder2.type_three_two = (LinearLayout) convertView.findViewById(R.id.type_three_two);
                    viewHoder2.type_three_three = (LinearLayout) convertView.findViewById(R.id.type_three_three);
                    convertView.setTag(viewHoder2);
                    break;
                default:
            }
        }else {
            switch (type){
                case TYPE_1:
                    viewHoder = (ViewHoder) convertView.getTag();
                    break;
                case TYPE_2:
                    viewHoder1 = (ViewHoder1) convertView.getTag();
                    break;
                case TYPE_3:
                    viewHoder2 = (ViewHoder2) convertView.getTag();
                    break;
                default:
            }
        }

        switch (type){
            case TYPE_1:
                viewHoder.type_one_title.setText(list.get(position).getLinner_text());
                viewHoder.type_one_more.setTag(position);
                viewHoder.type_one_one.setTag(position);
                viewHoder.type_one_two.setTag(position);
                viewHoder.type_one_three.setTag(position);
                viewHoder.type_one_four.setTag(position);
                viewHoder.type_one_five.setTag(position);
                viewHoder.type_one_six.setTag(position);
                viewHoder.type_one_more.setOnClickListener(this);
                viewHoder.type_one_one.setOnClickListener(this);
                viewHoder.type_one_two.setOnClickListener(this);
                viewHoder.type_one_three.setOnClickListener(this);
                viewHoder.type_one_four.setOnClickListener(this);
                viewHoder.type_one_five.setOnClickListener(this);
                viewHoder.type_one_six.setOnClickListener(this);
                break;
            case TYPE_2:
                viewHoder1.type_two_title.setText(list.get(position).getLinner_text());
                viewHoder1.type_two_more.setTag(position);
                viewHoder1.type_two_three.setTag(position);
                viewHoder1.type_two_two.setTag(position);
                viewHoder1.type_two_one.setTag(position);
                viewHoder1.type_two_more.setOnClickListener(this);
                viewHoder1.type_two_three.setOnClickListener(this);
                viewHoder1.type_two_two.setOnClickListener(this);
                viewHoder1.type_two_one.setOnClickListener(this);
                break;
            case TYPE_3:
                viewHoder2.type_three_title.setText(list.get(position).getLinner_text());
                viewHoder2.type_three_more.setTag(position);
                viewHoder2.type_three_one.setTag(position);
                viewHoder2.type_three_two.setTag(position);
                viewHoder2.type_three_three.setTag(position);
                viewHoder2.type_three_more.setOnClickListener(this);
                viewHoder2.type_three_one.setOnClickListener(this);
                viewHoder2.type_three_two.setOnClickListener(this);
                viewHoder2.type_three_three.setOnClickListener(this);
                break;
            default:
        }
        return convertView;
    }


    public class ViewHoder{
        TextView type_one_title;
        TextView type_one_more;
        LinearLayout type_one_one,type_one_two,type_one_three,type_one_four,type_one_five,type_one_six;
    }

    public class ViewHoder1{
        TextView type_two_title,type_two_more;
        LinearLayout type_two_two,type_two_one,type_two_three;
    }

    public class ViewHoder2{
        TextView type_three_title;
        TextView type_three_more;
        LinearLayout type_three_one,type_three_two,type_three_three;
    }

//
//    private ArrayList<HashMap<String, Object>> listItem;
//    private LayoutHelper layoutHelper;
//    private RecyclerView.LayoutParams layoutParams;
//    private int count = 0;
//    private MyOnItemClickListener mOnItemClickListener;
//
//    public interface MyOnItemClickListener {
//        void onItemClick(View view, int position);
//    }
//
//
//
//    public void setOnItemClickLitener(MyOnItemClickListener mOnItemClickListener) {
//        this.mOnItemClickListener = mOnItemClickListener;
//    }
//
//    public ShoppingMallAdapter(Context context, LayoutHelper layoutHelper, int count,ArrayList<HashMap<String, Object>> listItem){
//        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), listItem);
//    }
//
//    public ShoppingMallAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams, ArrayList<HashMap<String, Object>> listItem) {
//        this.context = context;
//        this.layoutHelper = layoutHelper;
//        this.count = count;
//        this.layoutParams = layoutParams;
//        this.listItem = listItem;
//    }
//
//    @Override
//    public LayoutHelper onCreateLayoutHelper() {
//        return layoutHelper;
//    }
//
//    @Override
//    public ShoppingMallAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.shopping_mall_head,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(final ShoppingMallAdapter.ViewHolder holder, int position) {
////        holder.Text.setText((String) listItem.get(position).get("ItemTitle"));
////        holder.image.setImageResource((Integer) listItem.get(position).get("ItemImage"));
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return count;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView Text,login;
//        private ConvenientBanner convenientBanner;
//        private EditText head_edit;
//        public ViewHolder(final View itemView) {
//            super(itemView);
//            Text = (TextView) itemView.findViewById(R.id.head_shop);
//            login = (TextView) itemView.findViewById(R.id.login);
//            convenientBanner = (ConvenientBanner) itemView.findViewById(R.id.converientBanner);
//            head_edit = (EditText) itemView.findViewById(R.id.head_edit);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(mOnItemClickListener != null){
//                        mOnItemClickListener.onItemClick(v, getPosition());
//                    }
//                }
//            });
//        }
//
//    }
}
