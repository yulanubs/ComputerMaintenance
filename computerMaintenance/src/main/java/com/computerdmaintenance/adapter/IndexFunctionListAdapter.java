package com.computerdmaintenance.adapter;

import android.content.Context;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.R;
import com.computerdmaintenance.util.ViewHolder;
import com.mr.cm.common.base.domain.Funcations;

public class IndexFunctionListAdapter extends CommonAdapter<Funcations> {


    public IndexFunctionListAdapter(Context context,
                                    int layoutId, int radian, ComputerMaintenanceApplication app) {
        super(context, layoutId, radian, app);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void convert(ViewHolder hodler, Funcations ben) {
        hodler.setText(R.id.item_title, ben.name);
        hodler.setImageURL(
                R.id.index_app_icon,app.config.IMAGE_SERVER + ben.images, app.option);

    }
}
