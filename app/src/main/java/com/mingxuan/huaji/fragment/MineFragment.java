package com.mingxuan.huaji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mingxuan.huaji.R;
import com.mingxuan.huaji.layout.four.activity.BalanceActivity;
import com.mingxuan.huaji.layout.four.activity.MyAdressActivity;
import com.mingxuan.huaji.layout.four.activity.MyBankCardActivity;
import com.mingxuan.huaji.layout.four.activity.MyFriendActivity;
import com.mingxuan.huaji.layout.four.activity.MyInformationActivity;
import com.mingxuan.huaji.layout.four.activity.MyIntergralActivity;
import com.mingxuan.huaji.layout.four.activity.MyOrderActivity;
import com.mingxuan.huaji.layout.four.activity.MyShoppingCartActivity;
import com.mingxuan.huaji.layout.four.adapter.MyShoppingCarAdapter;
import com.mingxuan.huaji.utils.CircleImageView;

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
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView() {
        myFriends.setOnClickListener(onClickListener);
        myShoppingCart.setOnClickListener(onClickListener);
        myOrder.setOnClickListener(onClickListener);
        myAddress.setOnClickListener(onClickListener);
        bankCard.setOnClickListener(onClickListener);
        myInformation.setOnClickListener(onClickListener);
        balance.setOnClickListener(onClickListener);
        myIntegral.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.my_integral:
                    intent = new Intent(getActivity(), MyIntergralActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_friends:
                    intent = new Intent(getActivity(), MyFriendActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_shopping_cart:
                    intent = new Intent(getActivity(), MyShoppingCartActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_order:
                    intent = new Intent(getActivity(), MyOrderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_address:
                    intent = new Intent(getActivity(), MyAdressActivity.class);
                    startActivity(intent);
                    break;
                case R.id.balance:
                    intent = new Intent(getActivity(), BalanceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.bank_card:
                    intent = new Intent(getActivity(), MyBankCardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_information:
                    intent = new Intent(getActivity(), MyInformationActivity.class);
                    startActivity(intent);
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
