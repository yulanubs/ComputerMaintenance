package com.computerdmaintenance.ui.wgt;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.computerdmaintenance.R;
import com.computerdmaintenance.adapter.HistoricalRecordsListAdapter;
import com.computerdmaintenance.adapter.HotSearchListAdapter;
import com.computerdmaintenance.nohttp.AllModuleReqeust;
import com.computerdmaintenance.nohttp.CallServer;
import com.computerdmaintenance.nohttp.HttpCallBack;
import com.computerdmaintenance.ui.activity.WorkingActivity;
import com.computerdmaintenance.ui.component.FeatureListView;
import com.loudmaintenance.util.BindView;
import com.loudmaintenance.util.Constants;
import com.mr.cm.common.base.domain.FuncationInfo;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.Response;

import java.util.Map;

/**
 * 引擎模式页面
 *
 * @author JekShow
 */
public class EngineModeWgt extends BaseWgt implements
        ViewPager.OnPageChangeListener, View.OnClickListener,
        AdapterView.OnItemClickListener {
    /**
     * 热门搜索适配器
     */
    private HotSearchListAdapter mHotSearchListAdapter;
    /**
     * 热门搜索
     */
    @BindView(id = R.id.fl_enginemode)
    private FeatureListView fl_enginemode;
    /**
     * 历史纪录适配器
     */
    private HistoricalRecordsListAdapter mRecordsListAdapter;
    /**
     * 历史纪录
     */
    @BindView(id = R.id.fl_historicalrecords)
    private FeatureListView fl_historicalrecords;

    public EngineModeWgt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public EngineModeWgt(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public EngineModeWgt(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void initView(Context context) {
        View mView = inflate(context, R.layout.enginemodewgt, this);
        initBindView(EngineModeWgt.this, mView);
        mHotSearchListAdapter = new HotSearchListAdapter(mContext,
                R.layout.enginemode_gridview_item, 5, appcontext);
        mRecordsListAdapter = new HistoricalRecordsListAdapter(mContext,
                R.layout.historicalrecords_gridview_item, 5, appcontext);
        getInfo();
    }

    /**
     * 方法名：getInfo<BR>
     * 此方法描述的是： 获取信息
     */
    private void getInfo() {
        // 获取热门信息
        getEnginemode();
        // 获取历史纪录
        getHotSearchList();

    }

    /**
     * 方法名：getHotSearchList<BR>
     * 此方法描述的是： 获取历史纪录
     */
    private void getHotSearchList() {
        Request<FuncationInfo> mRequest = new AllModuleReqeust<FuncationInfo>(
                appcontext.config.SERVER_MR_VPS
                        + Constants.Api.INDEX_FUNCATIONS, FuncationInfo.class);
        CallServer.getInstance().add(mContext, mRequest,
                new HttpCallBack<FuncationInfo>() {

                    @Override
                    public void onSucceed(int what,
                                          Response<FuncationInfo> response) {

                        if (what == Constants.What.WHAT_INDEX_FUNCTION) {// 处理登录结果
                            FuncationInfo info = response.get();
                            if (null != info) {

                                if (info.returnCode.equals("0000")) {

                                    // 跳转到个人中心
                                    // appcontext.openToast(mContext,
                                    // info.returnMessage);
                                    if (null != info.list
                                            && info.list.size() > 0) {

                                        mHotSearchListAdapter
                                                .setmDatas(info.list);

                                        fl_enginemode
                                                .setAdapter(mHotSearchListAdapter);
                                    }

                                } else {
                                    appcontext.openToast(mContext,
                                            info.returnMessage);
                                }
                            }
                        }

                    }

                    @Override
                    public void onFailed(int what, String url, Object tag,
                                         Exception exception, int responseCode,
                                         long networkMillis) {
                        // TODO Auto-generated method stub

                    }
                }, Constants.What.WHAT_INDEX_FUNCTION, false, false, true);
    }

    /**
     * 方法名：getEnginemode<BR>
     * 此方法描述的是： 获取功能分类信息
     */
    private void getEnginemode() {
        Request<FuncationInfo> mRequest = new AllModuleReqeust<FuncationInfo>(
                appcontext.config.SERVER_MR_VPS
                        + Constants.Api.INDEX_FUNCATIONS, FuncationInfo.class);
        CallServer.getInstance().add(mContext, mRequest,
                new HttpCallBack<FuncationInfo>() {

                    @Override
                    public void onSucceed(int what,
                                          Response<FuncationInfo> response) {

                        if (what == Constants.What.WHAT_INDEX_FUNCTION) {// 处理登录结果
                            FuncationInfo info = response.get();
                            if (null != info) {

                                if (info.returnCode.equals("0000")) {

                                    // 跳转到个人中心
                                    // appcontext.openToast(mContext,
                                    // info.returnMessage);
                                    if (null != info.list
                                            && info.list.size() > 0) {

                                        mRecordsListAdapter
                                                .setmDatas(info.list);

                                        fl_historicalrecords
                                                .setAdapter(mRecordsListAdapter);
                                    }

                                } else {
                                    appcontext.openToast(mContext,
                                            info.returnMessage);
                                }
                            }
                        }

                    }

                    @Override
                    public void onFailed(int what, String url, Object tag,
                                         Exception exception, int responseCode,
                                         long networkMillis) {
                        // TODO Auto-generated method stub

                    }
                }, Constants.What.WHAT_INDEX_FUNCTION, false, false, true);
    }

    @Override
    public void onBringToFront() {
        // TODO Auto-generated method stub
        hideFooter();

    }

    @Override
    public void onSentToBack() {
        showFooter(View.VISIBLE);

    }

    @Override
    public void onAddToStack() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestory() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public int leftBackVisibility() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View setRightView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLifeCycle() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean needLogin() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected String getTitleString() {
        // TODO Auto-generated method stub
        return getResources().getString(R.string.enginemode_title);
    }

    @Override
    protected boolean needTitle() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected void startFromAction(Map<String, Object> params) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getPageName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void hideFooter() {
        showFooter(View.GONE);

    }

    /**
     * 显示底部菜单
     */
    protected void showFooter(int show) {
        WorkingActivity workingActivity = (WorkingActivity) getContext();
        if (workingActivity != null) {
            workingActivity.footerVisibility(show);
        }
    }
}
