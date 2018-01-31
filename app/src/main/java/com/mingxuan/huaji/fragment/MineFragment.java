package com.mingxuan.huaji.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.LoginActivity;
import com.mingxuan.huaji.layout.four.activity.BalanceActivity;
import com.mingxuan.huaji.layout.four.activity.MyAdressActivity;
import com.mingxuan.huaji.layout.four.activity.MyBankCardActivity;
import com.mingxuan.huaji.layout.four.activity.MyFriendActivity;
import com.mingxuan.huaji.layout.four.activity.MyInformationActivity;
import com.mingxuan.huaji.layout.four.activity.MyIntergralActivity;
import com.mingxuan.huaji.layout.four.activity.MyOrderActivity;
import com.mingxuan.huaji.layout.four.activity.MyShoppingCartActivity;
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
    @BindView(R.id.balance)
    LinearLayout balance;
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

    Unbinder unbinder;
    private View view;
    boolean islogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);

        initView();
        sharedPreferences = getActivity().getSharedPreferences("huaji",Context.MODE_PRIVATE);

        return view;
    }

    private void initView() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("huaji", Context.MODE_PRIVATE);
        islogin = sharedPreferences.getBoolean("islogin", false);
        if (islogin) {
            Log.e("====", "你登录了" + islogin);
            phone.setText(sharedPreferences.getString("phone", ""));
            name.setText(sharedPreferences.getString("realname", ""));
            loginBack.setVisibility(View.VISIBLE);

            loginBack.setOnClickListener(onClickListener);
        } else {
            Log.e("====", "你没有登录" + islogin);
            login.setVisibility(View.VISIBLE);
            login.setOnClickListener(onClickListener);
        }

        myFriends.setOnClickListener(onClickListener);
        myShoppingCart.setOnClickListener(onClickListener);
        myOrder.setOnClickListener(onClickListener);
        myAddress.setOnClickListener(onClickListener);
        bankCard.setOnClickListener(onClickListener);
        myInformation.setOnClickListener(onClickListener);
        balance.setOnClickListener(onClickListener);
        myIntegral.setOnClickListener(onClickListener);
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
                case R.id.my_address:
                    if (islogin) {
                        intent = new Intent(getActivity(), MyAdressActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.balance:
                    if (islogin) {
                        intent = new Intent(getActivity(), BalanceActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.bank_card:
                    if (islogin) {
                        intent = new Intent(getActivity(), MyBankCardActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.my_information:
                    if (islogin) {
                        intent = new Intent(getActivity(), MyInformationActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;

                case R.id.login_back:
                    editor = sharedPreferences.edit();
                    editor.putBoolean("islogin",false);
                    editor.commit();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
