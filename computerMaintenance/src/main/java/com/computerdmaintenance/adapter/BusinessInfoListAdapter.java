package com.computerdmaintenance.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.benframework.utils.UtilsToast;
import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.R;
import com.computerdmaintenance.util.ViewHolder;
import com.mr.cm.common.base.domain.Business;

/**
 * @ClassName:BusinessInfoListAdapter <BR>
 * @Describe：商家列表适配器<BR>
 * @Author: 朱勋康
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-15 下午10:29:57
 */
public class BusinessInfoListAdapter extends CommonAdapter<Business> {

    public BusinessInfoListAdapter(Context context, int layoutId, int radian,
                                   ComputerMaintenanceApplication app) {
        super(context, layoutId, radian, app);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void convert(final ViewHolder holder, Business ben) {
        // TODO Auto-generated method stub
        holder.setImageURL(R.id.iv_shop_logo,
                app.config.IMAGE_SERVER + ben.Merchant_Logo, app.option)
                .setText(R.id.tv_shop_name, ben.Merchant_Name)
                .setText(R.id.tv_shop_follow_num, ben.Merchant_Follow_Nnum)
                .setText(R.id.tv_shop_distance, ben.Merchant_Distance)
                .setText(R.id.tv_shop_describe, ben.Merchant_Describe);
        TextView view = holder.getView(R.id.tv_shop_btn);
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                UtilsToast.myToast(mContext, holder.getmPosition() + "被点击");
            }
        });

    }
}
