package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.adapter.PictureAdapter;
import com.mingxuan.huaji.layout.four.model.PictureModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.NewEditText;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class ProductsReviewsActivity extends Activity {
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.shop_image)
    ImageView shopImage;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.edit_reviews)
    EditText editReviews;
    @BindView(R.id.popup_image)
    ImageView popupImage;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    //调用系统相机
    private static final int CREMA = 1;
    private List<PictureModel> list;
    private PictureAdapter pictureAdapter;
    private static final int REQUEST_CODE_GALLERY = 100;//打开相册
    private int index ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_reviews);
        ButterKnife.bind(this);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        create_time = simpleDateFormat.format(new Date());
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index");
        contingency_id = bundle.getString("id");
        products_id = bundle.getString("pid");
        String imageurl = bundle.getString("imageurl");
        String content = bundle.getString("content");

        list = new ArrayList<>();

        Glide.with(this).load(imageurl).into(shopImage);
        shopName.setText(content);

        editReviews.addTextChangedListener(new NewEditText(editReviews));
        pictureAdapter = new PictureAdapter(list,ProductsReviewsActivity.this);
        recyclerview.setAdapter(pictureAdapter);
        pictureAdapter.setOnItemClickListener(new PictureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                list.remove(position);
                pictureAdapter.notifyDataSetChanged();
            }
        });

        GridLayoutManager gridLayoutManger = new GridLayoutManager(ProductsReviewsActivity.this,4);
        recyclerview.setLayoutManager(gridLayoutManger);
    }

    @OnClick({R.id.back_btn,R.id.submit,R.id.popup_image})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.submit:
                comment_content = editReviews.getText().toString();
                if(index == 1){
                    comment_type = "1";
                }else {
                    comment_type = "2";
                }
                addProductsReviews();
                break;
            case R.id.popup_image:
                Album.image(ProductsReviewsActivity.this) // 选择图片。
                        .multipleChoice()
                        .requestCode(REQUEST_CODE_GALLERY)
                        .camera(true)
                        .columnCount(3)
                        .selectCount(9)
                        .onResult(new Action<ArrayList<AlbumFile>>() {
                            @Override
                            public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                                for(int i =0;i<result.size();i++){
                                    PictureModel pictureModel = new PictureModel();
                                    pictureModel.setImagePath(result.get(i).getPath());
                                    list.add(pictureModel);
                                }
                                pictureAdapter.notifyDataSetChanged();
                            }
                        })
                        .start();
                break;
        }
    }

    /**
     * 添加评论
     */
    String contingency_id;
    String products_id;
    String comment_userid = "56c9f9556b2e46428bb53f85bbc1b234";
    String comment_content;
    String comment_level = "1";
    String comment_type ;
    String create_id ="56c9f9556b2e46428bb53f85bbc1b234";
    String create_name = "th";
    String create_time;
    private void addProductsReviews(){
        FourApi.getInstance(this).addProductsReviewsApi(contingency_id, products_id, 1,comment_userid,
                comment_content, comment_level, comment_type, create_id, create_name, create_time, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if(type ==  Constants.TYPE_SUCCESS){
                            Intent intent = new Intent(ProductsReviewsActivity.this,MyOrderActivity.class);
                            startActivity(intent);
                        }else BaseApi.showErrMsg(ProductsReviewsActivity.this,result);
                    }
                });
    }



}
