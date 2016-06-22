
package com.computerdmaintenance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.R;
import com.mr.cm.common.base.domain.Funcations;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/*
 * 数据装载类
 */
public class ViewFlowAdapter extends BaseAdapter {
    private static int[] ids = {R.drawable.jumi_flash_1, R.drawable.jumi_flash_2,
            R.drawable.jumi_flash_3};
    ComputerMaintenanceApplication app;
    private ArrayList<Funcations> list;
    private LayoutInflater mInflater;

    public ViewFlowAdapter(Context context, ComputerMaintenanceApplication app) {
        this.app = app;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(ArrayList<Funcations> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;   //返回很大的值使得getView中的position不断增大来实现循环。
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.circle_viewflow_item_layout, null);
        }
        ImageView mimg = ((ImageView) convertView.findViewById(R.id.imgView));
        if (null != list && list.size() > 0) {
            String ImgUrl = list.get(position % list.size()).images;
            if (null != ImgUrl && !ImgUrl.equals("")) {
                ImageLoader.getInstance().displayImage(app.config.SERVER_MR_VPS + ImgUrl, mimg, app.option);

            }

        } else {
            mimg.setImageResource(ids[position % ids.length]);
        }
        return convertView;
    }

}
