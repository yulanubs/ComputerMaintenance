package com.computerdmaintenance.adapter;

import android.content.Context;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.R;
import com.loudmaintenance.util.ViewHolder;
import com.mr.cm.common.base.domain.Funcations;

public class HotSearchListAdapter extends CommonAdapter<Funcations> {


    public HotSearchListAdapter(Context context,
                                int layoutId, int radian, ComputerMaintenanceApplication app) {
        super(context, layoutId, radian, app);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void convert(ViewHolder hodler, Funcations ben) {
        hodler.setText(R.id.item_title, ben.name);
        hodler.setImageURL(
                R.id.index_app_icon, app.config.SERVER_MR_VPS + ben.images, app.option);

    }
}
