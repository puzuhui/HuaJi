package com.mingxuan.huaji.layout.four.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingxuan.huaji.R;
import com.mingxuan.huaji.api.BaseApi;
import com.mingxuan.huaji.api.FourApi;
import com.mingxuan.huaji.interfaces.GetResultCallBack;
import com.mingxuan.huaji.layout.four.adapter.PictureAdapter;
import com.mingxuan.huaji.layout.four.model.PictureModel;
import com.mingxuan.huaji.utils.Constants;
import com.mingxuan.huaji.utils.GsonUtil;
import com.mingxuan.huaji.utils.LoadingDialog;
import com.mingxuan.huaji.utils.NewEditText;
import com.mingxuan.huaji.utils.ToastUtil;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    @BindView(R.id.evaluate)
    TextView evaluate;
    @BindView(R.id.ratinBar)
    RatingBar ratingBar;
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    //调用系统相机
    private static final int CREMA = 1;
    private List<PictureModel.ResultBean> list;
    private List<PictureModel.ResultBean> idlist;
    private List<PictureModel.ResultBean> reviewslist;
    private PictureAdapter pictureAdapter;
    private static final int REQUEST_CODE_GALLERY = 100;//打开相册
    private int index;
    SimpleDateFormat simpleDateFormat;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_reviews);
        ButterKnife.bind(this);


        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        create_time = simpleDateFormat.format(new Date());
        update_time = simpleDateFormat.format(new Date());

        initView();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index");
        contingency_id = bundle.getString("id");
        products_id = bundle.getString("pid");
        String imageurl = bundle.getString("imageurl");
        String content = bundle.getString("content");

        list = new ArrayList<>();
        idlist = new ArrayList<>();
        reviewslist = new ArrayList<>();

        Glide.with(this).load(imageurl).into(shopImage);
        if(index == 1){
            shopName.setText(content);
        }else {
            searchreviews();

            ratingBar.setIsIndicator(true);//设置ratingbar不可修改
            shopName.setText("已评价");
            evaluate.setText(getResources().getString(R.string.additional_comments));
            editReviews.setHint(getResources().getString(R.string.reviews_edit_two));
        }

        editReviews.addTextChangedListener(new NewEditText(editReviews));
        pictureAdapter = new PictureAdapter(list, ProductsReviewsActivity.this);
        recyclerview.setAdapter(pictureAdapter);
        pictureAdapter.setOnItemClickListener(new PictureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                list.remove(position);
                pictureAdapter.notifyDataSetChanged();
            }
        });

        GridLayoutManager gridLayoutManger = new GridLayoutManager(ProductsReviewsActivity.this, 4);
        recyclerview.setLayoutManager(gridLayoutManger);
    }

    @OnClick({R.id.back_btn, R.id.submit, R.id.popup_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.submit:
                if(!TextUtils.isEmpty(editReviews.getText())){
                    comment_content = editReviews.getText().toString();
                    if (index == 1) {
                        comment_type = "1";
                        comment_level = ""+ratingBar.getRating();
                    } else {
                        comment_type = "2";
                        comment_level = ""+ratingBar.getRating();
                    }
                    //有照片上传时
                    if(list.size() > 0){
                        for (int i=0;i<list.size();i++){
                            path = list.get(i).getImagePath();//获取路径
                            String randNum = String.valueOf((int)(Math.random()*100));
                            String hzm =  path.substring(path.lastIndexOf("."),path.length());//后缀名
                            simpleDateFormat = new SimpleDateFormat("yyyyMMddHHssmmSSSS");
                            new_fname = simpleDateFormat.format(new Date())+randNum+hzm;
                            time += new_fname+",";
                            pic_dz += create_id+"/"+new_fname+",";
                        }
                        pic_dz = pic_dz.substring(4,pic_dz.length()-1);
                    }
                    addProductsReviews();
                }else {
                    ToastUtil.makeToast(ProductsReviewsActivity.this,"你还没有填写评论哟，亲！");
                }
                break;
            case R.id.popup_image:
                if(list.size()<4){
                    Album.image(ProductsReviewsActivity.this) // 选择图片。
                            .multipleChoice()
                            .requestCode(REQUEST_CODE_GALLERY)
                            .camera(true)
                            .columnCount(4)
                            .selectCount(4)
                            .onResult(new Action<ArrayList<AlbumFile>>() {
                                @Override
                                public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                                    for (int i = 0; i < result.size(); i++) {
                                        PictureModel.ResultBean pictureModel = new PictureModel.ResultBean();
                                        pictureModel.setImagePath(result.get(i).getPath());
                                        list.add(pictureModel);
                                    }
                                    pictureAdapter.notifyDataSetChanged();
                                }
                            })
                            .start();
                }else {
                    ToastUtil.makeToast(ProductsReviewsActivity.this,"最多只能选择四张哟，亲！");
                }
                break;
        }
    }

    /**
     * 添加评论
     */
    String contingency_id;
    String products_id;
    String pic_dz;
    String comment_userid = "d1e964159cd04e0d909677bd72ab89e6";
    String comment_content;
    String comment_level ;
    String comment_type;
    String create_id = "d1e964159cd04e0d909677bd72ab89e6";
    String create_name = "th";
    String create_time;
    private void addProductsReviews() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).addProductsReviewsApi(contingency_id, products_id, 1,pic_dz, comment_userid,
                comment_content, comment_level, comment_type, create_id, create_name, create_time, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        if (type == Constants.TYPE_SUCCESS) {
                            loadingDialog.dismiss();
                            //添加评论的同时修改hjhp_products_orders_contingency表中的orders_flag值为6
                            if (index == 1) {
                                orders_flag = "6";
                                updateOrderType();
                            }
                            //在上传评论时查询评论的id
                            if(list.size()>0){
                                searchId();
                            }else {
                                Intent intent = new Intent(ProductsReviewsActivity.this, MyOrderActivity.class);
                                startActivity(intent);
                            }
                        } else BaseApi.showErrMsg(ProductsReviewsActivity.this, result);
                    }
                });
    }



    /**
     * 更新状态（hjhp_products_orders_contingency表）
     */
    String orders_flag;
    String del_flag;
    String update_id = "d1e964159cd04e0d909677bd72ab89e6";
    String update_name = "th";
    String update_time;
    private void updateOrderType() {
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).updateOrderTypeApi(contingency_id, orders_flag, update_id, update_name,
                update_time,del_flag, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    loadingDialog.dismiss();
                    Log.e("", "更新成功");

                } else BaseApi.showErrMsg(ProductsReviewsActivity.this, result);
            }
        });
    }

    /**
     * 查询第一次评论的内容
     */
    int starnum;
    private void searchreviews(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).searchreviewsApi(create_id, products_id, contingency_id,  new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    List<PictureModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,PictureModel.ResultBean.class);
                    reviewslist.addAll(resultBeans);

                    starnum = reviewslist.get(0).getComment_level();
                    ratingBar.setRating(starnum);
                    Log.e("xingxing=",""+reviewslist.get(0).getComment_level());
                }else BaseApi.showErrMsg(ProductsReviewsActivity.this,result);
            }
        });
    }

    /**
     * 查询评论id添加到图片表中
     */
    String time;
    private void searchId(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).searchidApi(create_id, products_id, contingency_id, comment_type, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if(type == Constants.TYPE_SUCCESS){
                    loadingDialog.dismiss();
                    List<PictureModel.ResultBean> resultBeans = GsonUtil.fromJsonList(new Gson(),result,PictureModel.ResultBean.class);
                    idlist.addAll(resultBeans);

                    comment_id = idlist.get(0).getId();
                    for(int i = 0;i<list.size();i++){
                        String randNum = String.valueOf((int)(Math.random()*100));
                        path = list.get(i).getImagePath();//获取路径
                        file_name = path.substring(path.lastIndexOf("/") + 1, path.length());//获取文件名
                        //simpleDateFormat = new SimpleDateFormat("yyyyMMddHHssmmSSSS");
                        //String hzm =  path.substring(path.lastIndexOf("."),path.length());//后缀名
                        String[] newtime =  time.substring(4,time.length()-1).split(",");
                        //new_fname = newtime[i]+randNum+hzm;
                        new_fname = newtime[i];
                        Log.e("新时间====",""+newtime[i]);
                        xzdz =create_id+"/"+newtime[i];
                        setSubmit();
                    }
                }else BaseApi.showErrMsg(ProductsReviewsActivity.this,result);
            }
        });
    }
    /**
     * 上传图片
     */
    String comment_id;
    String xzdz ;
    String new_fname;
    String file_name;
    String path;
    private void setSubmit(){
        loadingDialog.setLoadingContent("正在加载...");
        loadingDialog.show();
        FourApi.getInstance(this).getuploadingfileApi(products_id,"1", comment_id, xzdz, new_fname,
                file_name, create_id, create_name, create_time, "0", path, new GetResultCallBack() {
                    @Override
                    public void getResult(String result, int type) {
                        loadingDialog.dismiss();
                        if (type == Constants.TYPE_SUCCESS) {
                            Intent intent = new Intent(ProductsReviewsActivity.this, MyOrderActivity.class);
                            startActivity(intent);
                        } else BaseApi.showErrMsg(ProductsReviewsActivity.this, result);
                    }
                });
    }

}
