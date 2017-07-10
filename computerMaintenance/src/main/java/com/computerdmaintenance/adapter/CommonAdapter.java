package com.computerdmaintenance.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义通用适配器
 *
 * @param <T>
 * @author zhu
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    public ComputerMaintenanceApplication app;
    /**
     * 上下文对象
     */
    protected Context mContext;
    /**
     * 泛型集合
     */
    protected List<T> mDatas;
    /**
     * 布局加载器
     */
    protected LayoutInflater mInflater;
    /**
     * 布局ID
     */
    protected int layoutId;
    /**
     * 图片圆角的弧度
     */
    protected int radian = 5;

    /**
     * 构造方法
     *
     * @param context  上下文
     * @param datas    泛型集合
     * @param layoutId 布局ID
     */
    public CommonAdapter(Context context, List<T> datas, int layoutId,
                         int radian, ComputerMaintenanceApplication app) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.app = app;
        if (mDatas == null) {
            mDatas = new ArrayList<T>();
        }
        this.mDatas = datas;
        this.layoutId = layoutId;
        this.radian = radian;

    }

    public CommonAdapter(Context context, int layoutId, int radian, ComputerMaintenanceApplication app) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.app = app;
        this.radian = radian;
        if (mDatas == null) {
            mDatas = new ArrayList<T>();
        }
    }

    public List<T> getmDatas() {
        return mDatas;
    }

    /**
     * 设置数据
     *
     * @param mDatas
     */
    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        // TODO Auto-generated method stub
        return mDatas.get(position);
    }

    /**
     * 刷新数据
     *
     * @param t
     * @param position
     */
    public void upData(T t, int position) {
        mDatas.set(position, t);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 初始化ViewHodler
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position, radian);
        // 调用convert方法
        convert(holder, getItem(position));
        return holder.getmConvertView();
    }

    /**
     * 获得Hodeler对象和bean的抽象方法
     *
     * @param holder
     * @param ben
     */
    public abstract void convert(ViewHolder holder, T ben);

    /**
     * 清空数据
     */
    public void removedata() {
        if (null != mDatas) {
            try {
                mDatas.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setSelectedItem(int selectedItem) {
        notifyDataSetChanged();
    }

    /**
     * 方法名：<BR>
     * 此方法描述的是：拨打电话
     *
     * @param mobile
     */
    public void tallPhone(String mobile) {
        Intent intent = new Intent("android.intent.action.CALL",
                Uri.parse("tel:" + mobile));
        mContext.startActivity(intent);
    }
}
