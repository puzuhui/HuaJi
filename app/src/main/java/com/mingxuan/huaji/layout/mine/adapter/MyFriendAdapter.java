package com.mingxuan.huaji.layout.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.mine.bean.MyFriendsModel;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class MyFriendAdapter extends BaseAdapter {
    private Context context;
    private List<MyFriendsModel.ResultBean> list;
    private int TYPE_NULL = 10000;
    public MyFriendAdapter(Context context,List<MyFriendsModel.ResultBean> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
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
    public int getItemViewType(int position) {
        if(list.size() == 0){
            return TYPE_NULL;
        }else
            return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_myfriends,null);
            viewHolder.circleimage = (ImageView) convertView.findViewById(R.id.circleimage);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.city = (TextView) convertView.findViewById(R.id.city);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.phone);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
       // Glide.with(context).load(list.get(position).getImageurl()).into(viewHolder.circleimage);
        viewHolder.name.setText(list.get(position).getReal_name());
        //viewHolder.city.setText(list.get(position).g());
        viewHolder.phone.setText(list.get(position).getPhone());
        return convertView;
    }

    private class ViewHolder{
        ImageView circleimage;
        TextView name;
        TextView city;
        TextView phone;
    }
}
