package com.mingxuan.huaji.layout.news.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.news.bean.IntroduceModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2018/5/24.
 * 公司：铭轩科技
 */

public class IntroduceAdapter extends RecyclerView.Adapter<IntroduceAdapter.ViewHolder> {
    private List<IntroduceModel.ResultBean> mList;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public IntroduceAdapter(Context context, List<IntroduceModel.ResultBean> list) {
        this.mContext = context;
        this.mList = list;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_introduce, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IntroduceModel.ResultBean resultBean = mList.get(position);
        holder.tvTitle.setText(resultBean.getName());
        holder.wbContext.loadData(resultBean.getIntro(), "text/html; charset=UTF-8", null);

        WebSettings webSettings = holder.wbContext.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.wb_context)
        WebView wbContext;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
