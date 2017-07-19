package cn.almsound.www.baselibrary;

import android.graphics.Bitmap;

import java.lang.ref.WeakReference;

/**
 * Created by alm on 17-6-6.
 * 图片显示器
 */

public class BitmapDisplayer implements Runnable{

    private Bitmap mBitmap;
    private WeakReference<ImageLoader> imageLoaderWeakReference;
    private ImageLoader mImageLoader;
    private BitmapHolder mBitmapHolder;

    public BitmapDisplayer(Bitmap bitmap, BitmapHolder bitmapHolder, ImageLoader imageLoader) {
        this.mBitmap = bitmap;
        mBitmapHolder = bitmapHolder;
        mImageLoader = imageLoader;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        ImageLoader imageLoader = imageLoaderWeakReference.get();
        if (imageLoader == null){
            return;
        }
        if (imageLoader.imageViewReused(mBitmapHolder)) {
            return;
        }

        if (mBitmap != null) {
            mBitmapHolder.imageView.setImageBitmap(mBitmap);
        } else {
            mBitmapHolder.imageView.setImageResource(imageLoader.defaultDrawable);
        }
    }
}