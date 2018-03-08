package com.mingxuan.huaji.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.mingxuan.huaji.layout.LoginActivity;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.two.activity.ListOfGoodsActivity;
import com.mingxuan.huaji.layout.two.activity.PhoneCardActivity;
import com.mingxuan.huaji.layout.two.adapter.ShoppingMallAdapter;
import com.mingxuan.huaji.layout.two.model.ShoppingMallModel;
import com.mingxuan.huaji.utils.NetImageLocadHolder;
import com.mingxuan.huaji.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class ShoppingMallFragment extends Fragment implements ShoppingMallAdapter.Callback{
    private ConvenientBanner convenientBanner;
    private String[] images = {"http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"};
    //轮播下面的小点
    private int[] indicator={R.mipmap.ic_page_indicator,R.mipmap.ic_page_indicator_focused};
    //网络图片加载地址的集合
    private List<String> bean;
    private ShoppingMallAdapter Adapter_linearLayout,Adapter_GridLayout,Adapter_linearLayout1,Adapter_ScrollFixLayout
            ,Adapter_FloatLayout,Adapter_ColumnLayout,Adapter_SingleLayout,Adapter_onePlusNLayout,
            Adapter_StickyLayout,Adapter_StaggeredGridLayout;
    private ArrayList<HashMap<String, Object>> listItem;
    private View view;
    private RecyclerView recyclerView;
    private List<ShoppingMallModel> list;
    private ListView listView;
    private TextView login,one,two,three,fore,five;
    private EditText head_edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping_mall,null);

        initView();
        return view;
    }

    private void initView() {
        bean = new ArrayList<>();
        list = new ArrayList<>();
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        listView = (ListView) view.findViewById(R.id.listview);

        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.shopping_mall_head,null);
        listView.addHeaderView(view1);
        login = (TextView) view1.findViewById(R.id.login);
        one = (TextView) view1.findViewById(R.id.one);
        two = (TextView) view1.findViewById(R.id.two);
        three = (TextView) view1.findViewById(R.id.three);
        fore = (TextView) view1.findViewById(R.id.fore);
        five = (TextView) view1.findViewById(R.id.five);
        head_edit = (EditText) view1.findViewById(R.id.head_edit);

        one.setOnClickListener(onClickListener);
        two.setOnClickListener(onClickListener);
        three.setOnClickListener(onClickListener);
        fore.setOnClickListener(onClickListener);
        five.setOnClickListener(onClickListener);
        head_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Intent intent = new Intent(getActivity(), ListOfGoodsActivity.class);
                intent.putExtra("type",31);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("huaji", Context.MODE_PRIVATE);
        boolean islogin = sharedPreferences.getBoolean("islogin",false);
        if(islogin){
            Log.e("====","你登录了"+islogin);
            login.setVisibility(View.INVISIBLE);
            login.setOnClickListener(onClickListener);
        }else {
            Log.e("====","你没有登录"+islogin);
            login.setOnClickListener(onClickListener);
        }
        convenientBanner = (ConvenientBanner) view1.findViewById(R.id.converientBanner);
        bean = Arrays.asList(images);
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
                Toast.makeText(getActivity(), "点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });

        String[] s = {"爱生活","限时折扣","热门推荐","家居生活","值得买"};
        for (int i = 0; i < s.length; i++) {
            ShoppingMallModel shoppingMallModel = new ShoppingMallModel();
            shoppingMallModel.setLinner_text(s[i]);
            list.add(shoppingMallModel);
        }

        ShoppingMallAdapter shoppingMallAdapter = new ShoppingMallAdapter(list,getActivity(),this);
        listView.setAdapter(shoppingMallAdapter);
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
                case R.id.one:
                    intent = new Intent(getActivity(), ListOfGoodsActivity.class);
                    intent.putExtra("type",31);
                    startActivity(intent);
                    break;
                case R.id.two:
                    intent = new Intent(getActivity(), PhoneCardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.three:
                    ToastUtil.makeToast(getActivity(),"此功能还未开通！！！");
                    break;
                case R.id.fore:
                    ToastUtil.makeToast(getActivity(),"此功能还未开通！！！");
                    break;
                case R.id.five:
                    ToastUtil.makeToast(getActivity(),"此功能还未开通！！！");
                    break;
            }
        }
    };

    @Override
    public void click(View v) {
        int position = (int) v.getTag();
        switch (v.getId()){
            case R.id.type_one_more:
                Toast.makeText(getActivity(), "点击了"+"更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.type_one_one:
                Toast.makeText(getActivity(), "点击了"+"第一个", Toast.LENGTH_SHORT).show();
                break;
            case R.id.type_one_two:
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.type_one_three:
                break;
            case R.id.type_one_four:
                break;
            case R.id.type_one_five:
                break;
            case R.id.type_one_six:
                break;
            case R.id.type_two_more:
                if(position == 1){
                    Toast.makeText(getActivity(), "点击了"+2, Toast.LENGTH_SHORT).show();
                }else if(position == 2){
                    Toast.makeText(getActivity(), "点击了"+3, Toast.LENGTH_SHORT).show();
                }else if(position == 3){
                    Toast.makeText(getActivity(), "点击了"+4, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.type_two_one:
                if(position == 1){
                    Toast.makeText(getActivity(), "点击了"+2, Toast.LENGTH_SHORT).show();
                }else if(position == 2){
                    Toast.makeText(getActivity(), "点击了"+3, Toast.LENGTH_SHORT).show();
                }else if(position == 3){
                    Toast.makeText(getActivity(), "点击了"+4, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.type_two_two:
                if(position == 1){
                    Toast.makeText(getActivity(), "点击了"+2, Toast.LENGTH_SHORT).show();
                }else if(position == 2){
                    Toast.makeText(getActivity(), "点击了"+3, Toast.LENGTH_SHORT).show();
                }else if(position == 3){
                    Toast.makeText(getActivity(), "点击了"+4, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.type_two_three:
                if(position == 1){
                    Toast.makeText(getActivity(), "点击了"+2, Toast.LENGTH_SHORT).show();
                }else if(position == 2){
                    Toast.makeText(getActivity(), "点击了"+3, Toast.LENGTH_SHORT).show();
                }else if(position == 3){
                    Toast.makeText(getActivity(), "点击了"+4, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.type_three_more:
                break;
            case R.id.type_three_one:
                break;
            case R.id.type_three_two:
                break;
            case R.id.type_three_three:
                break;
        }
    }

//    private void initView() {
//        listItem = new ArrayList<>();
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//        convenientBanner = (ConvenientBanner) view.findViewById(R.id.converientBanner);
//
////        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.shopping_mall_head,null);
//
//        // 创建VirtualLayoutManager对象
//        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找
//        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//
//        /**
//         * 步骤2：设置组件复用回收池
//         * */
//        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
//        recyclerView.setRecycledViewPool(viewPool);
//        // recyclerView.addItemDecoration(itemDecoration);
//        viewPool.setMaxRecycledViews(0, 20);
//
//        listItem = new ArrayList<HashMap<String, Object>>();
//
//        String[] s = {"家居生活","母婴玩具","服饰箱包","食品饮料","数码科技"};
//        for (int i = 0; i < s.length; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemTitle", s[i]);
//            map.put("ItemImage", R.mipmap.ic_launcher);
//            listItem.add(map);
//        }
//
//        /**
//         设置线性布局
//         */
//        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
//        linearLayoutHelper.setItemCount(1);// 设置布局里Item个数
//        //linearLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        //linearLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
//        // linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
//        //linearLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
//        Adapter_linearLayout  = new ShoppingMallAdapter(getActivity(), linearLayoutHelper, 1, listItem) {
//            // 参数2:绑定绑定对应的LayoutHelper
//            // 参数3:传入该布局需要显示的数据个数
//            // 参数4:传入需要绑定的数据
//
////            当前item被回收时调用，可用来释放绑定在view上的大数据
//            @Override
//            public void onViewRecycled(ViewHolder holder) {
//                super.onViewRecycled(holder);
//                if(holder.itemView.findViewById(R.id.converientBanner) instanceof  ConvenientBanner){
////                    ((ConvenientBanner)holder.itemView.findViewById(R.id.converientBanner))
//                }
//            }
//
////            @Override
////            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
////                return new ViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.shopping_mall_head,parent,false));
////            }
//
//            @Override
//            public void onBindViewHolder(final ViewHolder holder, int position) {
//                if(holder.itemView.findViewById(R.id.converientBanner) instanceof  ConvenientBanner){
//                    ConvenientBanner convenientBanner = (ConvenientBanner) holder.itemView.findViewById(R.id.converientBanner);
//
//                    bean = Arrays.asList(images);
//                    //设置指示器是否可见
//                    convenientBanner.setPointViewVisible(true);
//                    //设置小点
//                    convenientBanner.setPageIndicator(indicator);
//                    //允许手动轮播
//                    convenientBanner.setManualPageable(true);
//                    //设置自动轮播的时间
//                    convenientBanner.startTurning(3000);
//                    //设置点击事件    泛型为具体实现类ImageLoaderHolder
//                    convenientBanner.setPages(new CBViewHolderCreator<NetImageLocadHolder>() {
//                        @Override
//                        public NetImageLocadHolder createHolder() {
//                            return new NetImageLocadHolder();
//                        }
//                    },bean);
//
//                    //设置指示器的方向
//                    convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
//
//                    convenientBanner.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(getActivity(), "点击了",Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                }
//            }
//        };
//
//        Adapter_linearLayout.setOnItemClickLitener(new ShoppingMallAdapter.MyOnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Log.e("=========","点击"+position);
//            }
//        });
//
//        /**
//         * 表格布局
//         */
//        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
//        gridLayoutHelper.setItemCount(5);
//        gridLayoutHelper.setPadding(0, 20, 0, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        gridLayoutHelper.setWeights(new float[]{20,20,20,20,20});
//
//        Adapter_GridLayout = new ShoppingMallAdapter(getActivity(),gridLayoutHelper,5,listItem){
//            @Override
//            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return new ViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_shopping_mall_type_two,parent,false));
//            }
//
//            @Override
//            public void onBindViewHolder(ViewHolder holder, int position) {
//                ((TextView)holder.itemView.findViewById(R.id.type_two_text)).setText((String)listItem.get(position).get("ItemTitle"));
//            }
//        };
//
//        Adapter_linearLayout1 = new ShoppingMallAdapter(getActivity(), linearLayoutHelper, 1, listItem) {
//            @Override
//            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return new ViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_shopping_mall_type_one,parent,false));
//            }
//
//            @Override
//            public void onBindViewHolder(ViewHolder holder, int position) {
////                holder.itemView.findViewById(R.id.converientBanner)
//            }
//        };
//
//        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
//        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
//
//        // 2. 将上述创建的Adapter对象放入到DelegateAdapter.Adapter列表里
//        adapters.add(Adapter_linearLayout) ;
//        adapters.add(Adapter_GridLayout) ;
//        adapters.add(Adapter_linearLayout1) ;
////        adapters.add(Adapter_ScrollFixLayout) ;
////        adapters.add(Adapter_FixLayout) ;
////        adapters.add(Adapter_FloatLayout) ;
////        adapters.add(Adapter_ColumnLayout) ;
////        adapters.add(Adapter_SingleLayout) ;
////        adapters.add(Adapter_onePlusNLayout) ;
////        adapters.add(Adapter_StaggeredGridLayout) ;
//
//        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
//        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
//
//        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
//        delegateAdapter.setAdapters(adapters);
//
//        // 5. 将delegateAdapter绑定到recyclerView
//        recyclerView.setAdapter(delegateAdapter);
//    }
}
