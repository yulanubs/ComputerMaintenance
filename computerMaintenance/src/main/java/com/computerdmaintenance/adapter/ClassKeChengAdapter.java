package com.computerdmaintenance.adapter;

import android.content.Context;
import android.widget.TextView;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.R;
import com.loudmaintenance.util.ViewHolder;
import com.mr.cm.common.base.domain.ClassCurriculum;

/**
 * @ClassName:ClassKeChengAdapter <BR>
 * @Describe：课程适配器<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-23 下午10:02:05
 */
public class ClassKeChengAdapter extends CommonAdapter<ClassCurriculum> {

    public ClassKeChengAdapter(Context context, int layoutId, int radian,
                               ComputerMaintenanceApplication app) {
        super(context, layoutId, radian, app);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void convert(ViewHolder holder, ClassCurriculum ben) {
        TextView view = holder.getView(R.id.tv_class_num);
        view.setText("第" + ben.getNum() + "节");
        if (ben.isSelected) {
            view.setBackgroundResource(R.drawable.button_02);
            view.setTextColor(0xffffffff);
        } else {
            view.setBackgroundResource(R.drawable.button_01);
            view.setTextColor(0xff12b7f5);
        }

    }

}
