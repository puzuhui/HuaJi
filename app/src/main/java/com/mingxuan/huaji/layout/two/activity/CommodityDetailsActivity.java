package com.mingxuan.huaji.layout.two.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.LoginActivity;
import com.mingxuan.huaji.layout.four.activity.MyShoppingCartActivity;
import com.mingxuan.huaji.layout.four.model.PictureModel;
import com.mingxuan.huaji.layout.two.adapter.ShopSizeAdapter;
import com.mingxuan.huaji.layout.two.model.ShopListModel;
import com.mingxuan.huaji.layout.two.model.ShopSizeModel;
import com.mingxuan.huaji.layout.two.model.ShoppingCarModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.FullGridLayoutManager;
import com.mingxuan.huaji.utils.GridSpacingItemDecoration;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.NetImageLocadHolder;
import com.mingxuan.huaji.utils.NumberAddSubView;
import com.mingxuan.huaji.utils.UIUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class CommodityDetailsActivity extends Activity {
    @BindView(R.id.convenient)
    ConvenientBanner convenientBanner;
    @BindView(R.id.all_evaluate)
    TextView allEvaluate;
    @BindView(R.id.add_to_shopping_cart)
    TextView addToShoppingCart;
    @BindView(R.id.settle_accounts)
    TextView settleAccounts;
    LoadingDialog loadingDialog;
    List<ShopListModel.ResultBean> list;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.ratinBar)
    RatingBar ratinBar;
    @BindView(R.id.comment_name)
    TextView commentName;
    @BindView(R.id.comment_content)
    TextView commentContent;
    @BindView(R.id.comment_numb)
    TextView commentNumb;
    @BindView(R.id.comment_good)
    TextView commentGood;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.comment_all)
    LinearLayout commentAll;
    @BindView(R.id.shopcar)
    TextView shopcar;
    @BindView(R.id.nenberprice)
    TextView nenberprice;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.numberAddSubView)
    NumberAddSubView numberAddSubView;
    //轮播下面的小点
    private int[] indicator = {R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused};
    //网络图片加载地址的集合
    private List<String> bean;
    private List<ShopSizeModel> sizelist;
    private SimpleDateFormat simpleDateFormat;
    Boolean islogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_details);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("huaji", Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("create_id","");
        create_name = sharedPreferences.getString("create_name","");
        update_id = sharedPreferences.getString("create_id","");
        update_name = sharedPreferences.getString("create_name","");
        islogin = sharedPreferences.getBoolean("islogin",false);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        create_time = simpleDateFormat.format(new Date());
        update_time = simpleDateFormat.format(new Date());
        getBundle();
        initView();
        getlist();
        getshopingevaluate();
        getsearchshoppingcar();
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
    }

    private void initView() {
        shoppingcarlist = new ArrayList<>();
        goodlist = new ArrayList<>();
        list = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);
        sizelist = new ArrayList<>();
        bean = new ArrayList<>();
        piclist = new ArrayList<>();

//        FullGridLayoutManager gridLayoutManager = new FullGridLayoutManager(this, 4);
//        recyclerview.setNestedScrollingEnabled(false);
//        recyclerview.setLayoutManager(gridLayoutManager);
//        int spanCount = 4;//跟布局里面的spanCount属性是一致的
//        int spacing = 5;//每一个矩形的间距
//        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount, spacing, false);
//        recyclerview.addItemDecoration(gridSpacingItemDecoration);

//        String[] s = {"185/64Y", "185/64Y", "185/64Y", "185/64Y", "185/64Y"};
//        for (int i = 0; i < s.length; i++) {
//            ShopSizeModel shopSizeModel = new ShopSizeModel();
//            shopSizeModel.setSize(s[i]);
//            sizelist.add(shopSizeModel);
//        }
//
//        ShopSizeAdapter shopSizeAdapter = new ShopSizeAdapter(CommodityDetailsActivity.this, sizelist);
//        recyclerview.setAdapter(shopSizeAdapter);
//        shopSizeAdapter.setMyOnClickListener(new ShopSizeAdapter.MyOnClickListener() {
//            @Override
//            public void onclick(View view, int position) {
//                Toast.makeText(CommodityDetailsActivity.this, "点击了"+position, Toast.LENGTH_SHORT).show();
//            }
//        });

        addToShoppingCart.setOnClickListener(onClickListener);
        settleAccounts.setOnClickListener(onClickListener);
        backBtn.setOnClickListener(onClickListener);
        allEvaluate.setOnClickListener(onClickListener);
        shopcar.setOnClickListener(onClickListener);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultFontSize(25);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        //处理各种通知请求和事件，如果不使用该句代码，将使用内置浏览器访问网页
        webview.setWebViewClient(new WebViewClient());
    }

    private void showBanner() {
        //bean = Arrays.asList(images);
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
                Toast.makeText(CommodityDetailsActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.all_evaluate://所有评论
                    intent = new Intent(CommodityDetailsActivity.this, CommentActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                    break;
                case R.id.add_to_shopping_cart:
                    if(islogin){//判断是否登录
                        if(isshop){//是否加入购物车
                            products_num = 1;
                            getaddshoppingcar();
                        }else {
                            products_num++;
                            insertshoppingcar();
                        }
                    }else {
                        intent = new Intent(CommodityDetailsActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    break;
                case R.id.settle_accounts:
                    if(islogin){
                        intent = new Intent(CommodityDetailsActivity.this, ConfirmAnOrderActivity.class);
                        intent.putExtra("createname",list.get(0).getCreate_name());
                        intent.putExtra("image",bean.get(0));
                        intent.putExtra("productname",list.get(0).getProduct_name());
                        intent.putExtra("productprice",list.get(0).getProduct_price());
                        startActivity(intent);
                    }else {
                        intent = new Intent(CommodityDetailsActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.shopcar:
                    if(islogin){
                        intent = new Intent(CommodityDetailsActivity.this, MyShoppingCartActivity.class);
                        startActivity(intent);
                    }else {
                        intent = new Intent(CommodityDetailsActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    break;
            }
        }
    };

    /**
     * 商品详情
     */
    String id;
    String product_label;
    String parent_id;
    private void getlist() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        MainApi.getInstance(this).shoppinglistApi(id, parent_id, product_label, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    loadingDialog.dismiss();
                    List<ShopListModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                            result, ShopListModel.ResultBean.class);
                    list.clear();
                    list.addAll(resultBeans);

                    String[] imageurl = list.get(0).getProduct_intr().split(",");
                    for (int i = 0; i < imageurl.length; i++) {
                        String image = Constants.IMAGE_URL + imageurl[i];
                        bean.add(image);
                    }
                    showBanner();

                    numberAddSubView.setMaxValue(Integer.parseInt(list.get(0).getProduct_inventory()));
                    content.setText(list.get(0).getProduct_name());
                    money.setText(list.get(0).getProduct_price());
                    money.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);//中划线
                    nenberprice.setText("会员价:￥ "+list.get(0).getMember_price());
                    number.setText("库存: "+list.get(0).getProduct_inventory()+" 件");
                    webview.loadData(list.get(0).getProduct_desc(), "text/html; charset=UTF-8", null);
                } else BaseApi.showErrMsg(CommodityDetailsActivity.this, result);
            }
        });
    }

    /**
     * 商品评论列表
     */
    List<PictureModel.ResultBean> piclist;
    List<Integer> goodlist ;
    private void getshopingevaluate() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        MainApi.getInstance(this).shoppingevaluateApi(id, "1", new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    loadingDialog.dismiss();
                    List<PictureModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),
                            result, PictureModel.ResultBean.class);
                    piclist.clear();
                    piclist.addAll(resultBeans);

                    for (int i = 0; i < piclist.size(); i++) {
                        int good = piclist.get(i).getComment_level();
                        if (good > 3) {
                            goodlist.add(good);//将3星以上的都视为好评
                        }
                    }
                    float goodnumb = (float) goodlist.size() / piclist.size();
                    DecimalFormat decimalFormat = new DecimalFormat("0.0");
                    Log.e("===", "" + decimalFormat.format(goodnumb));
                    Log.e("===", "" + decimalFormat.format(goodnumb * 100));


                    commentNumb.setText("(" + piclist.size() + ")");
                    commentGood.setText("(" + decimalFormat.format(goodnumb * 100) + "%)");
                    ratinBar.setRating(piclist.get(0).getComment_level());
                    ratinBar.setIsIndicator(true);//设置不可改变
                    commentName.setText(piclist.get(0).getCreate_name());
                    commentContent.setText(piclist.get(0).getComment_content());
                } else {
                    commentNumb.setText("(" + 0 + ")");
                    commentGood.setText("(" + 100 + "%)");
                    commentAll.setVisibility(View.GONE);
                }
            }
        });
    }

    //查询商品是否加入购物车
    boolean isshop;
    List<ShoppingCarModel.ResultBean> shoppingcarlist ;
    private void getsearchshoppingcar() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        MainApi.getInstance(this).searchshoppingcarApi(id, create_id, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    loadingDialog.dismiss();
                    List<ShoppingCarModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,ShoppingCarModel.ResultBean.class);
                    shoppingcarlist.addAll(resultBeans);
                    products_num = shoppingcarlist.get(0).getProducts_num();
                    shoppingcarid = shoppingcarlist.get(0).getId();
                    isshop = false;
                } else{
                    isshop = true;
                }
            }
        });
    }

    /**
     * 添加购物车
     */
    String create_id ;
    String create_name ;
    String create_time;
    private void getaddshoppingcar() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        MainApi.getInstance(this).addshoppingcarApi(id, products_num,create_id,create_name,create_time, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    loadingDialog.dismiss();
                    Toast.makeText(CommodityDetailsActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                   // addToShoppingCart.setText("已加入购物车");

                } else BaseApi.showErrMsg(CommodityDetailsActivity.this,result);
            }
        });
    }

    /**
     * 修改购物车
     */
    int products_num;
    String update_id;
    String update_name;
    String update_time;
    String shoppingcarid;
    int del_flag = 0;
    private void insertshoppingcar() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        MainApi.getInstance(this).insertshoppingcarApi(shoppingcarid,products_num,del_flag, update_id,update_name,update_time, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    loadingDialog.dismiss();
                    Toast.makeText(CommodityDetailsActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                } else BaseApi.showErrMsg(CommodityDetailsActivity.this,result);
            }
        });
    }
}
