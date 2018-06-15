package com.mingxuan.huaji.layout.homepage.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.base.BaseActivity;
import com.mingxuan.huaji.network.api.BaseApi;
import com.mingxuan.huaji.network.api.MainApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.mine.activity.MyAdressActivity;
import com.mingxuan.huaji.layout.mine.bean.MyAdressModel;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.PayUtils;
import com.mingxuan.huaji.utils.ToastUtil;
import com.mingxuan.huaji.utils.UIUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class ConfirmAnOrderActivity extends BaseActivity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.no_default_adress)
    TextView noDefaultAdress;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.adress)
    TextView adress;
    @BindView(R.id.default_adress)
    RelativeLayout defaultAdress;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.shop_content)
    TextView shopContent;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.money)
    TextView tvmoney;
    @BindView(R.id.settle_accounts)
    TextView settleAccounts;
    @BindView(R.id.productprice)
    TextView productPrice;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.optional)
    TextView optional;
    @BindView(R.id.phone_confrim)
    RelativeLayout phoneConfrim;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.cb_choose)
    CheckBox cb_choose;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    private List<MyAdressModel.ResultBean> adresslist;
    private int index;
    LoadingDialog loadingDialog;
    private final static int REQUESTCODE = 1004;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confrim_an_order;
    }

    @Override
    protected void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        create_id = sharedPreferences.getString("create_id","");

        getBundle();
        setToolbarTitle(getString(R.string.confrim_an_order));
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initData() {
        searchdefaultaddress();
    }

    Bundle bundle;
    String createname;
    String imageurl;
    String productname;
    double productprice;
    private void getBundle() {
        loadingDialog = new LoadingDialog(this);
        adresslist = new ArrayList<>();
        bundle = getIntent().getExtras();
        if(bundle != null){
            createname = bundle.getString("createname","");
            imageurl = bundle.getString("image","");
            productname = bundle.getString("productname","");
            productprice = bundle.getDouble("productprice");
            index = bundle.getInt("index");
            String createTime = bundle.getString("createTime","");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
            try {
                Date date = sdf.parse(createTime);
                aCalendar.setTime(date);
                aCalendar.add(Calendar.HOUR_OF_DAY,2);
                String cc = sdf.format(aCalendar.getTime());
                tvHint.setText("提示：订单将于【"+ cc +"】失效。");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int day=aCalendar.getActualMaximum(Calendar.DATE) - aCalendar.get(Calendar.DAY_OF_MONTH) +1;//当月剩余多少天
            Double money = productprice / aCalendar.getActualMaximum(Calendar.DATE) * day ;
            if(index == 1 ){
                //购买电信电话卡
                optional.setText("(备注:电信号码首次开通需要预存3个月套餐费和本月剩余"+day+"天共计"+ Math.ceil(money) +"元)");
                phoneConfrim.setVisibility(View.VISIBLE);
                productPrice.setText("￥"+(productprice * 3 + Math.ceil(money)));
                price.setText(""+(productprice * 3 + Math.ceil(money)));
                tvmoney.setText(""+(productprice * 3 + Math.ceil(money)+ 9));
            }else if(index == 2 ){
                //购买联通电话卡
                optional.setText("(备注:联通号码首次开通需要预存3个月套餐费和本月剩余"+day+"天共计"+ Math.ceil(money) +"元)");
                phoneConfrim.setVisibility(View.VISIBLE);
                productPrice.setText("￥"+(productprice * 3 + Math.ceil(money)));
                price.setText(""+(productprice * 3 + Math.ceil(money)));
                tvmoney.setText(""+(productprice * 3 + Math.ceil(money)+ 9));
            }else {
                //其他商品
                productPrice.setText("￥"+productprice );
                price.setText(""+productprice);
                tvmoney.setText(""+(productprice+ 9));
            }

            shopName.setText(createname);
            Glide.with(this).load(imageurl).into(image);
            shopContent.setText(productname);
        }
    }

    @OnClick({R.id.back_btn,R.id.no_default_adress,R.id.default_adress,R.id.settle_accounts,R.id.tv_service,R.id.tv_agreement})
    public void setOnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.no_default_adress:
                intent = new Intent(ConfirmAnOrderActivity.this, AddressManageActivity.class);
                startActivityForResult(intent,REQUESTCODE);
                break;
            case R.id.default_adress:
                intent = new Intent(ConfirmAnOrderActivity.this, AddressManageActivity.class);
                startActivityForResult(intent,REQUESTCODE);
                break;
            case R.id.tv_service:
                intent = new Intent(ConfirmAnOrderActivity.this, AgreementDetailsActivty.class);
                intent.putExtra("index",1);
                startActivity(intent);
                break;
            case R.id.tv_agreement:
                intent = new Intent(ConfirmAnOrderActivity.this, AgreementDetailsActivty.class);
                intent.putExtra("index",2);
                startActivity(intent);
                break;
            case R.id.settle_accounts:
                if(index == 1 ){
                    if(cb_choose.isChecked()){
                        PayUtils.getInstance(this).showPayPopupWindow(productname,"0.01",UIUtils.getIPAddress(this));
                    }else {
                        ToastUtil.makeToast(ConfirmAnOrderActivity.this,"请仔细阅读协议！！！");
                    }
                }else {
                    PayUtils.getInstance(this).showPayPopupWindow(productname,"1",UIUtils.getIPAddress(this));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
           return;
        }else {
            if (requestCode == REQUESTCODE ){
                defaultAdress.setVisibility(View.VISIBLE);
                String nn = data.getStringExtra("name");
                name.setText(nn);
                phone.setText(data.getStringExtra("phone"));
                adress.setText(data.getStringExtra("address"));
            }
        }
    }

    String create_id;
    String default_flag = "1";
    private void searchdefaultaddress() {
        loadingDialog.setLoadingContent("加载中...");
        loadingDialog.show();
        MainApi.getInstance(this).searchdefaultaddressApi(create_id, default_flag, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    List<MyAdressModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,MyAdressModel.ResultBean.class);
                    adresslist.addAll(resultBeans);

                    defaultAdress.setVisibility(View.VISIBLE);
                    name.setText(adresslist.get(0).getConsignee());
                    phone.setText(adresslist.get(0).getPhone());
                    adress.setText(adresslist.get(0).getAddress());
                }else{
                    noDefaultAdress.setVisibility(View.VISIBLE);
                    BaseApi.showErrMsg(ConfirmAnOrderActivity.this,result);
                }

            }
        });
    }

}
