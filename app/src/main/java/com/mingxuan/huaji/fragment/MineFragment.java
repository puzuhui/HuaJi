package com.mingxuan.huaji.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.LoginActivity;
import com.mingxuan.huaji.layout.four.activity.PasswordManageActivity;
import com.mingxuan.huaji.layout.four.activity.MyAdressActivity;
import com.mingxuan.huaji.layout.four.activity.MyBankCardActivity;
import com.mingxuan.huaji.layout.four.activity.MyFriendActivity;
import com.mingxuan.huaji.layout.four.activity.MyInformationActivity;
import com.mingxuan.huaji.layout.four.activity.MyIntergralActivity;
import com.mingxuan.huaji.layout.four.activity.MyOrderActivity;
import com.mingxuan.huaji.layout.four.activity.MyQrcodeActivity;
import com.mingxuan.huaji.layout.four.activity.MyShoppingCartActivity;
import com.mingxuan.huaji.layout.two.activity.ChoosePhoneCardActivity;
import com.mingxuan.huaji.utils.CircleImageView;
import com.mingxuan.huaji.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class MineFragment extends Fragment {
    @BindView(R.id.circle)
    CircleImageView circle;
    @BindView(R.id.my_friends)
    LinearLayout myFriends;
    @BindView(R.id.my_integral)
    LinearLayout myIntegral;
    @BindView(R.id.my_shopping_cart)
    LinearLayout myShoppingCart;
    @BindView(R.id.my_order)
    LinearLayout myOrder;
    @BindView(R.id.my_address)
    LinearLayout myAddress;
//    @BindView(R.id.balance)
//    LinearLayout balance;
    @BindView(R.id.bank_card)
    LinearLayout bankCard;
    @BindView(R.id.my_information)
    LinearLayout myInformation;
    @BindView(R.id.login_back)
    Button loginBack;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.qrcode)
    LinearLayout qrcode;
    @BindView(R.id.phone_card)
    LinearLayout phoneCard;
    @BindView(R.id.binding_mobile)
    LinearLayout bindingMobile;
    @BindView(R.id.insertpassword)
    LinearLayout insertpassword;
    @BindView(R.id.view_filpper)
    ViewFlipper viewFlipper;

    Unbinder unbinder;
    private View view;
    boolean islogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);

        sharedPreferences = getActivity().getSharedPreferences("huaji",Context.MODE_PRIVATE);
        initView();

        return view;
    }

    private void initView() {
        //SharedPreferences sharedPreferences = getActivity().getSharedPreferences("huaji", Context.MODE_PRIVATE);
        islogin = sharedPreferences.getBoolean("islogin", false);
        if (islogin) {
            phone.setText(sharedPreferences.getString("phone", ""));
            name.setText(sharedPreferences.getString("realname", ""));
            loginBack.setVisibility(View.VISIBLE);

            loginBack.setOnClickListener(onClickListener);
        } else {
            login.setVisibility(View.VISIBLE);
            login.setOnClickListener(onClickListener);
        }

        //轮播
        for(int i = 0;i<3;i++){
            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.item_viewflipper,null);
            TextView textView = (TextView) view1.findViewById(R.id.text);
            textView.setText("厉害了我的哥，你又中奖了"+i);
            viewFlipper.addView(view1);
        }

        myFriends.setOnClickListener(onClickListener);
        myShoppingCart.setOnClickListener(onClickListener);
        myOrder.setOnClickListener(onClickListener);
        myAddress.setOnClickListener(onClickListener);
        bankCard.setOnClickListener(onClickListener);
        myInformation.setOnClickListener(onClickListener);
        //balance.setOnClickListener(onClickListener);
        myIntegral.setOnClickListener(onClickListener);
        qrcode.setOnClickListener(onClickListener);
        phoneCard.setOnClickListener(onClickListener);
        insertpassword.setOnClickListener(onClickListener);
    }


    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences = null;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.login:
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_friends:
                    if (islogin) {
                        Log.e("====", "登录" + islogin);
                        intent = new Intent(getActivity(), MyFriendActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.my_integral:
                    if (islogin) {
                        intent = new Intent(getActivity(), MyIntergralActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.my_shopping_cart:
                    if (islogin) {
                        intent = new Intent(getActivity(), MyShoppingCartActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.my_order:
                    if (islogin) {
                        intent = new Intent(getActivity(), MyOrderActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.qrcode:
                    if (islogin) {
                        intent = new Intent(getActivity(), MyQrcodeActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.my_address:
                    if (islogin) {
                        intent = new Intent(getActivity(), MyAdressActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
//                case R.id.balance:
//                    if (islogin) {
//                        intent = new Intent(getActivity(), BalanceActivity.class);
//                        startActivity(intent);
//                    } else {
//                        ToastUtil.makeToast(getContext(), "你还没有登录");
//                    }
//                    break;
                case R.id.bank_card:
                    if (islogin) {
                        intent = new Intent(getActivity(), MyBankCardActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.phone_card:
                    showPopupWindow();
//                    if (islogin) {
//                        intent = new Intent(getActivity(), MyBankCardActivity.class);
//                        startActivity(intent);
//                    } else {
//                        ToastUtil.makeToast(getContext(), "你还没有登录");
//                    }
                    break;
                case R.id.my_information:
                    if (islogin) {
                        intent = new Intent(getActivity(), MyInformationActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.insertpassword:
                    if (islogin) {
                        intent = new Intent(getActivity(), PasswordManageActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.login_back:
                    editor = sharedPreferences.edit();
                    editor.putBoolean("islogin",false);
                    editor.apply();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    break;
            }
        }
    };

    TextView title;
    TextView content;
    TextView confirmBtn;
    TextView cancelBtn;
    private void showPopupWindow(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_phone_window,null);
        title = (TextView) view.findViewById(R.id.title);
        content = (TextView) view.findViewById(R.id.content);
        confirmBtn = (TextView) view.findViewById(R.id.confirm_btn);
        cancelBtn = (TextView) view.findViewById(R.id.cancel_btn);

        title.setText(R.string.hint_title);
        content.setText(R.string.phone_hint);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels*4/5;
        int height = getResources().getDisplayMetrics().heightPixels*2/7;
        final PopupWindow popupWindow = new PopupWindow(view,weight,height,true);
        popupWindow.setFocusable(true);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ChoosePhoneCardActivity.class);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1.0f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
