package com.mingxuan.huaji.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.LoginActivity;
import com.mingxuan.huaji.layout.mine.activity.BindMobileActivity;
import com.mingxuan.huaji.layout.mine.activity.LianXiActivity;
import com.mingxuan.huaji.layout.mine.activity.MyPhoneCardActivity;
import com.mingxuan.huaji.layout.mine.activity.PasswordManageActivity;
import com.mingxuan.huaji.layout.mine.activity.MyAdressActivity;
import com.mingxuan.huaji.layout.mine.activity.MyBankCardActivity;
import com.mingxuan.huaji.layout.mine.activity.MyFriendActivity;
import com.mingxuan.huaji.layout.mine.activity.MyInformationActivity;
import com.mingxuan.huaji.layout.mine.activity.MyIntergralActivity;
import com.mingxuan.huaji.layout.mine.activity.MyOrderActivity;
import com.mingxuan.huaji.layout.mine.activity.MyQrcodeActivity;
import com.mingxuan.huaji.layout.mine.activity.MyShoppingCartActivity;
import com.mingxuan.huaji.layout.mine.bean.InformationModel;
import com.mingxuan.huaji.layout.homepage.activity.ChoosePhoneCardActivity;
import com.mingxuan.huaji.network.api.FourApi;
import com.mingxuan.huaji.utils.CircleImageView;
import com.mingxuan.huaji.base.Constants;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.ToastUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class MineFragment extends Fragment implements TakePhoto.TakeResultListener,InvokeListener {
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
    @BindView(R.id.lianxi)
    LinearLayout lianxi;
    List<InformationModel.ResultBean> list;

    Unbinder unbinder;
    private View view;
    boolean islogin;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private Uri imageUri;  //图片保存路径
    private CropOptions cropOptions;  //裁剪参数
    TakePhoto takePhoto;
    InvokeParam invokeParam;
    String mobile;
    LoadingDialog loadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);

        sharedPreferences = getActivity().getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
        initView();
        return view;
    }

    private void initView() {
        loadingDialog = new LoadingDialog(getActivity());
        list = new ArrayList<>();
        islogin = sharedPreferences.getBoolean("islogin", false);
        mobile =  sharedPreferences.getString("mobile", "");
        if (islogin) {
            phone.setText(sharedPreferences.getString("phone", ""));
            name.setText(sharedPreferences.getString("realName", ""));
            loginBack.setVisibility(View.VISIBLE);

            loginBack.setOnClickListener(onClickListener);
        } else {
            login.setVisibility(View.VISIBLE);
            login.setOnClickListener(onClickListener);
        }

        //轮播
        for (int i = 0; i < 3; i++) {
            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.item_viewflipper, null);
            TextView textView = (TextView) view1.findViewById(R.id.text);
            textView.setText("厉害了我的哥，你又中奖了" + i);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp_14));
            viewFlipper.addView(view1);
        }

        initData();

        circle.setOnClickListener(onClickListener);
        myFriends.setOnClickListener(onClickListener);
        myShoppingCart.setOnClickListener(onClickListener);
        myOrder.setOnClickListener(onClickListener);
        myAddress.setOnClickListener(onClickListener);
        bankCard.setOnClickListener(onClickListener);
        myInformation.setOnClickListener(onClickListener);
        myIntegral.setOnClickListener(onClickListener);
        qrcode.setOnClickListener(onClickListener);
        phoneCard.setOnClickListener(onClickListener);
        insertpassword.setOnClickListener(onClickListener);
        lianxi.setOnClickListener(onClickListener);
        bindingMobile.setOnClickListener(onClickListener);
    }

    SharedPreferences sharedPreferences = null;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.circle:
                    if (islogin) {
                        if (ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                                || ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            // 权限还没有授予，进行申请
                            checkPermisson();
                        } else {
                            showChoosePicDialog();
                        }
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.login:
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_friends:
                    if (islogin) {
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
                case R.id.binding_mobile:
                    if (islogin) {
                        intent = new Intent(getActivity(), BindMobileActivity.class);
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
                case R.id.phone_card:
                    if (islogin) {
                        showPopupWindow();
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
                case R.id.insertpassword:
                    if (islogin) {
                        intent = new Intent(getActivity(), PasswordManageActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtil.makeToast(getContext(), "你还没有登录");
                    }
                    break;
                case R.id.lianxi:
                    intent = new Intent(getActivity(), LianXiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.login_back:
                    SharedPreferences shared = getActivity().getSharedPreferences(Constants.HUAJI, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    editor.clear();
                    editor.apply();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    JPushInterface.deleteAlias(getActivity(), 0);//极光删除别名
                    getActivity().finish();
                    break;
            }
        }
    };

    ///////////////修改头像///////////////
    private void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        imageUri = getImageCropUri();
                        //从相册中选取图片并裁剪
                        takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
                        break;
                    case TAKE_PICTURE:// 拍照
                        //拍照并裁剪
                        imageUri = getImageCropUri();
                        takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private void initData() {
        //获取TakePhoto实例
        takePhoto = getTakePhoto();
        //设置裁剪参数
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
//        //设置压缩参数
//        compressConfig=new CompressConfig.Builder().setMaxSize(50*1024).setMaxPixel(800).create();
//        takePhoto.onEnableCompress(compressConfig,true);  //设置为需要压缩
    }

    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file=new File(Environment.getExternalStorageDirectory(), "/huaji/head/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /** * 获取TakePhoto实例 * @return */
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }

    /**
     * 动态权限的请求
     */
    public void checkPermisson() {
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(getActivity(),//上下文
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},//权限数组
                    1001);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(getActivity(),type,invokeParam,this);
    }


    @Override
    public void takeSuccess(TResult result) {
        String iconPath = result.getImage().getOriginalPath();
        //Toast显示图片路径
        Log.e("成功======", "imagePath:" + iconPath);
        //Google Glide库 用于加载图片资源
        Glide.with(this).load(iconPath).into(circle);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.e("失败======", "msg:" + msg);
    }

    @Override
    public void takeCancel() {}

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }

    ///////////////修改头像///////////////

    TextView title;
    TextView content;
    TextView confirmBtn;
    TextView cancelBtn;
    LinearLayout ll_mima;
    ScrollView sv_content;
    EditText et_mima;
    private void showPopupWindow() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_phone_window, null);
        title = (TextView) view.findViewById(R.id.title);
        content = (TextView) view.findViewById(R.id.content);
        confirmBtn = (TextView) view.findViewById(R.id.confirm_btn);
        cancelBtn = (TextView) view.findViewById(R.id.cancel_btn);
        ll_mima = (LinearLayout) view.findViewById(R.id.ll_mima);
        sv_content = (ScrollView) view.findViewById(R.id.sv_content);
        et_mima = (EditText) view.findViewById(R.id.et_mima);


        title.setText(R.string.hint_title);
        if (!TextUtils.isEmpty(mobile)) {//是否购买电话卡
            ll_mima.setVisibility(View.VISIBLE);
        } else {
            sv_content.setVisibility(View.VISIBLE);
        }
        content.setText(R.string.phone_hint);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels * 4 / 5;
        int height = getResources().getDisplayMetrics().heightPixels * 2 / 7;
        final PopupWindow popupWindow = new PopupWindow(view, weight, height, true);
        popupWindow.setFocusable(true);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (!TextUtils.isEmpty(mobile)) {
                    password = et_mima.getText().toString();
                    login();
                } else {
                    intent = new Intent(getActivity(), ChoosePhoneCardActivity.class);
                    intent.putExtra("index",1);
                    startActivity(intent);
                }

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
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    //二级登录
    String password;
    private void login(){
        loadingDialog.setLoadingContent("正在登录...");
        loadingDialog.show();
        FourApi.getInstance(getActivity()).twologinApi(mobile, password, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                loadingDialog.dismiss();
                if(type == Constants.TYPE_SUCCESS){
                    Intent intent = new Intent(getActivity(), MyPhoneCardActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtil.makeToast(getActivity(),"输入密码错误");
                }
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
