package com.computerdmaintenance.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * 自定义ViewHodlerz
 *
 * @author zhu
 */
public class ViewHolder {
    String cachePath = "Photo_LMG";
    private SparseArray<View> mViews;
    private View mConvertView;
    private int mPosition;
    /**
     * 图片圆角发弧度
     */
    private int radian;

    /**
     * 构造方法
     *
     * @param context  上下文对象
     * @param parent   ViewGroup
     * @param layoutId 布局ID
     * @param position 条目编号
     */
    public ViewHolder(Context context, ViewGroup parent, int layoutId,
                      int position, int radian) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        this.radian = radian;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        mConvertView.setTag(this);


    }

    /**
     * 创建视图
     *
     * @param context     上下文对象
     * @param convertView View回收池
     * @param parent      ViewGroup
     * @param layoutId    布局ID
     * @param position    条目编号
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position, int radian) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position, radian);

        } else {
            // 从Tag中获得hodler实例
            ViewHolder hodler = (ViewHolder) convertView.getTag();
            hodler.mPosition = position;
            return hodler;
        }

    }

    /***/

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    /**
     * 通过viewId获得控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获得onvertView
     *
     * @return
     */
    public View getmConvertView() {
        return mConvertView;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            TextView tv = getView(viewId);
            tv.setText(text);
        }
        return this;

    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public TextView setTexts(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return tv;

    }

    /**
     * 设置Text的中划线
     *
     * @param viewId
     * @return
     */
    public ViewHolder getPaint(int viewId) {
        TextView tv = getView(viewId);
        tv.getPaint().setFlags(
                Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        return this;

    }

    /**
     * 设置Text的HTML样式的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setHtml(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(Html.fromHtml("<b>" + text + "</b>"));
        return this;

    }

    /**
     * 设置ImageView的值(资源)
     *
     * @param viewId 控件ID
     * @param resId  资源ID
     * @return 当前类对象
     */
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;

    }

    /**
     * 绑定控件，获得ImageView
     *
     * @param viewId
     * @return
     */
    public ImageView setImageResource(int viewId) {
        ImageView view = getView(viewId);
        return view;

    }

    /**
     * 设置ImageView的值（Bitmap）
     *
     * @param viewId 控件ID
     * @param bitmap Bitmap
     * @return 当前类对象
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;

    }

    /**
     * 设置ImageView的值（Drawable）
     *
     * @param viewId   控件ID
     * @param drawable Drawable
     * @return 当前类对象
     */
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;

    }

    /**
     * 设置ImageView的值(Matrix)
     *
     * @param viewId 控件ID
     * @param matrix Matrix
     * @return 当前类对象
     */
    public ViewHolder setImageMatrix(int viewId, Matrix matrix) {
        ImageView view = getView(viewId);
        view.setImageMatrix(matrix);
        return this;

    }

    /**
     * 设置IImageView的值（Uri）
     *
     * @param viewId 布局ID
     * @param uri    图片的路径
     * @return 当前类对象
     */
    public ViewHolder setImageURI(int viewId, Uri uri) {
        ImageView view = getView(viewId);
        view.setImageURI(uri);
        // imageloader.getInstance().loading(view ,url);
        return this;

    }

    /**
     * 设置IImageView的值（Url）
     *
     * @param viewId 布局ID
     * @param url    图片的路径
     * @return 当前类对象
     */
    public ViewHolder setImageURL(int viewId, String url, DisplayImageOptions options) {
        ImageView view = getView(viewId);
        ImageLoader.getInstance().displayImage(url, view, options);
        return this;

    }

    public ImageView setImageURLs(int viewId, String url, DisplayImageOptions options) {
        ImageView view = getView(viewId);
        ImageLoader.getInstance().displayImage(url, view, options);
        return view;

    }

    public GridView setAdapter(int viewId, BaseAdapter baseAdapter) {
        GridView view = getView(viewId);
        view.setAdapter(baseAdapter);
        return view;

    }
}
