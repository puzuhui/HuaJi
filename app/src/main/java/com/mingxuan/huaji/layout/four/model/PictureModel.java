package com.mingxuan.huaji.layout.four.model;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class PictureModel {
    public String imageId; //图片id
    public String thumbnailPath;
    public String imagePath; //图片路径
    private Bitmap bitmap;
    public boolean isSelected = false;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Bitmap getBitmap() {
//        if(bitmap != null){
//            bitmap = Bimp.revitionImageSize(imagePath);
//        }
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
