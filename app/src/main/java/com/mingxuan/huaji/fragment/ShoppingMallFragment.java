package com.mingxuan.huaji.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingxuan.huaji.layout.homepage.activity.RechargeActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.LoginActivity;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.homepage.activity.CardInformationActivity;
import com.mingxuan.huaji.layout.homepage.activity.CommodityDetailsActivity;
import com.mingxuan.huaji.layout.homepage.activity.ListOfGoodsActivity;
import com.mingxuan.huaji.layout.homepage.activity.PhoneCardActivity;
import com.mingxuan.huaji.layout.homepage.bean.BannerModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.NetImageLocadHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class ShoppingMallFragment extends Fragment {
    private ConvenientBanner convenientBanner;
    //轮播下面的小点
    private int[] indicator={R.mipmap.ic_page_indicator,R.mipmap.ic_page_indicator_focused};
    //网络图片加载地址的集合
    private List<String> bean;
    private View view;
    private TextView login,one,two,three;
    private TextView head_edit;
    private ImageView iv_one,iv_two,iv_three,iv_four;
    private List<BannerModel.ResultBean> list;
    boolean islogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_mall_head,null);

        initView();
        getlist("1");
        getlist("3");
        return view;
    }

    private void initView() {
        bean = new ArrayList<>();
        list = new ArrayList<>();
        login = (TextView) view.findViewById(R.id.login);
        one = (TextView) view.findViewById(R.id.one);
        two = (TextView) view.findViewById(R.id.two);
        three = (TextView) view.findViewById(R.id.three);
        head_edit = (TextView) view.findViewById(R.id.head_edit);
        iv_one = (ImageView) view.findViewById(R.id.iv_one);
        iv_two = (ImageView) view.findViewById(R.id.iv_two);
        iv_three = (ImageView) view.findViewById(R.id.iv_three);
        iv_four = (ImageView) view.findViewById(R.id.iv_four);

        one.setOnClickListener(onClickListener);
        two.setOnClickListener(onClickListener);
        three.setOnClickListener(onClickListener);
        head_edit.setOnClickListener(onClickListener);
        iv_one.setOnClickListener(onClickListener);
        iv_two.setOnClickListener(onClickListener);
        iv_three.setOnClickListener(onClickListener);
        iv_four.setOnClickListener(onClickListener);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        islogin = sharedPreferences.getBoolean("islogin",false);
        if(islogin){
            login.setVisibility(View.INVISIBLE);
            login.setOnClickListener(onClickListener);
        }else {
            login.setOnClickListener(onClickListener);
        }

    }

    private void showBanner() {
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.converientBanner);
        //设置指示器是否可见
        convenientBanner.setPointViewVisible(true);
        //设置小点
        convenientBanner.setPageIndicator(indicator);
        //允许手动轮播
        convenientBanner.setManualPageable(true);
        //设置自动轮播的时间
        convenientBanner.startTurning(3000);
        //设置点击事件    泛型为具体实现类ImageLoaderHolder
        convenientBanner.setPages(new CBViewHolderCreator<NetImageLocadHolder>() {
            @Override
            public NetImageLocadHolder createHolder() {
                return new NetImageLocadHolder();
            }
        }, bean);

        //设置指示器的方向
        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(),PhoneCardActivity.class);
                startActivity(intent);
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.login:
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.head_edit:
                    intent = new Intent(getActivity(), ListOfGoodsActivity.class);
                    intent.putExtra("type",31);
                    startActivity(intent);
                    break;
                case R.id.one:
                    intent = new Intent(getActivity(), ListOfGoodsActivity.class);
                    intent.putExtra("type",31);
                    startActivity(intent);
                    break;
                case R.id.two:
                    if(islogin){
                        intent = new Intent(getActivity(), PhoneCardActivity.class);
                        startActivity(intent);
                    }else {
                        intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.three:
                    if(islogin){
                        intent = new Intent(getActivity(), RechargeActivity.class);
                        startActivity(intent);
                    }else {
                        intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.iv_one:
                    intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                    intent.putExtra("id",""+85);
                    startActivity(intent);
                    break;
                case R.id.iv_two:
                    intent = new Intent(getActivity(), CardInformationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.iv_three:
                    intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                    intent.putExtra("id",""+81);
                    startActivity(intent);
                    break;
                case R.id.iv_four:
                    intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                    intent.putExtra("id",""+84);
                    startActivity(intent);
                    break;
            }
        }
    };

    /**
     * 要改的太多，催的又急，只能先这样了，这不能怪我
     *
     */
    private void getlist(final String type){
        MainApi.getInstance(getActivity()).hotproductApi(type, new GetResultCallBack() {
            @Override
            public void getResult(String result, int types) {
                if(types == Constants.TYPE_SUCCESS){
                    List<BannerModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                            result,BannerModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    if(type.equals("1")){
                        for (int i=0;i<resultBeans.size();i++){
                            bean.add(Constants.IMAGE_URL+list.get(i).getPic());
                            showBanner();
                        }
                    }else {
                        Glide.with(getActivity()).load(Constants.IMAGE_URL+list.get(0).getPic()).into(iv_one);
                        Glide.with(getActivity()).load(Constants.IMAGE_URL+list.get(1).getPic()).into(iv_two);
                        Glide.with(getActivity()).load(Constants.IMAGE_URL+list.get(2).getPic()).into(iv_three);
                        Glide.with(getActivity()).load(Constants.IMAGE_URL+list.get(3).getPic()).into(iv_four);
                    }
                }else BaseApi.showErrMsg(getActivity(),result);
            }
        });
    }

}
