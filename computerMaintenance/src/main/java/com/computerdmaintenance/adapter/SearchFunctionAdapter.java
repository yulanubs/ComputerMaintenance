package com.computerdmaintenance.adapter;import android.content.Context;import com.computerdmaintenance.ComputerMaintenanceApplication;import com.computerdmaintenance.R;import com.computerdmaintenance.util.ViewHolder;import com.mr.cm.common.base.domain.SearchFeatured;/** * @author zhu */public class SearchFunctionAdapter extends CommonAdapter<SearchFeatured> {    public SearchFunctionAdapter(Context context, int layoutId, int radian,                                 ComputerMaintenanceApplication app) {        super(context, layoutId, radian, app);        // TODO Auto-generated constructor stub    }    /**     * 给控件赋值     */    @Override    public void convert(ViewHolder hodler, SearchFeatured ben) {        hodler.setText(R.id.tv_ativity_title, ben.getTitle())                .setText(R.id.tv_ativity_describe, ben.getDescribe())                .setImageURL(R.id.iv_ativity_img, ben.getImgurl(), app.option);    }}