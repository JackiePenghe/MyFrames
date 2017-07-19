package cn.almsound.www.baselibrary;

import android.widget.ImageView;

/**
 * Created by alm on 17-6-6.
 * 用来封装Url和ImageView，防止图片错位里用
 */

class BitmapHolder {
    String url;

    ImageView imageView;

    BitmapHolder(String u, ImageView i) {
        url = u;
        imageView = i;
    }
}
