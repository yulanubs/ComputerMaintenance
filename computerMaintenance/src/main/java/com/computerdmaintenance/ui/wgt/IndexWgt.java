package com.computerdmaintenance.ui.wgt;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.benframework.utils.UtilsToast;
import com.computerdmaintenance.R;
import com.computerdmaintenance.adapter.BusinessInfoListAdapter;
import com.computerdmaintenance.adapter.ClassVideoListAdapter;
import com.computerdmaintenance.adapter.IndexFunctionListAdapter;
import com.computerdmaintenance.adapter.ViewFlowAdapter;
import com.computerdmaintenance.nohttp.AllModuleReqeust;
import com.computerdmaintenance.nohttp.CallServer;
import com.computerdmaintenance.nohttp.HttpCallBack;
import com.computerdmaintenance.ui.ClassRoomActivity;
import com.computerdmaintenance.ui.WGTContainer;
import com.computerdmaintenance.ui.activity.WorkingActivity;
import com.computerdmaintenance.ui.component.BusinessView;
import com.computerdmaintenance.ui.component.CustomScrollView;
import com.computerdmaintenance.ui.component.FeatureListView;
import com.computerdmaintenance.ui.view.wgt.CircleFlowIndicator;
import com.computerdmaintenance.ui.view.wgt.ViewFlow;
import com.computerdmaintenance.ui.wgt.event.MrEvent;
import com.computerdmaintenance.util.BindView;
import com.computerdmaintenance.util.Constants;
import com.computerdmaintenance.util.Consts;
import com.mr.cm.common.base.domain.AdvertisementInfo;
import com.mr.cm.common.base.domain.Business;
import com.mr.cm.common.base.domain.FuncationInfo;
import com.mr.cm.common.base.domain.Funcations;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tom.ule.ui.view.image.UleImageView;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.Response;
import com.zbar.lib.CaptureActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndexWgt extends BaseWgt implements
        ViewPager.OnPageChangeListener, View.OnClickListener,
        AdapterView.OnItemClickListener {
    /**
     * 图片轮播指示器
     */
    CircleFlowIndicator indic;
    private ViewFlow mViewFlow;
    /**
     * 功能区列表
     */
    private IndexFunctionListAdapter mFunctionListAdapter;
    /**
     * 扫描二维码
     */
    @BindView(id = R.id.iv_goQrcode, click = true)
    private ImageView iv_goQrcode;
    /**
     * 图片轮播View
     */
    private ViewFlowAdapter mViewFlowAdapter;
    /**
     * 商家信息列表
     */
    private BusinessInfoListAdapter mBusinessInfoListAdapter;
    /**
     * 功能分类
     */
    @BindView(id = R.id.gv_dome)
    private FeatureListView gv_dome;
    /**
     * 搜索
     */
    @BindView(id = R.id.et_search)
    private EditText et_search;
    /**
     * 商家列表
     */
    @BindView(id = R.id.fl_business)
    private BusinessView fl_business;
    /**
     * 课程广告
     */
    @BindView(id = R.id.iv_Vlass_Video)
    private UleImageView iv_Vlass_Video;
    /**
     * 推荐课程列表
     */
    @BindView(id = R.id.bv_class_video)
    private BusinessView bv_class_video;
    /**
     * 推荐课程适配器
     */
    private ClassVideoListAdapter mClassVideoListAdapter;
    /***/
    @BindView(id = R.id.csv_index)
    private CustomScrollView mCustomScrollView;

    public IndexWgt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public IndexWgt(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public IndexWgt(Context context) {
        super(context, null);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void initView(Context context) {
        // TODO Auto-generated method stub
        View mView = inflate(context, R.layout.wgt_index, this);
        initBindView(IndexWgt.this, mView);
        iv_goQrcode = (ImageView) findViewById(R.id.iv_goQrcode);
        mViewFlow = (ViewFlow) findViewById(R.id.viewflow);
        mViewFlowAdapter = new ViewFlowAdapter(context, appcontext);
        indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
        mViewFlow.setmSideBuffer(1); // 实际图片张数， 我的ImageAdapter实际图片张数为3
        mViewFlow.setSelection(1000); // 设置初始位置
        mViewFlow.setFlowIndicator(indic);
        mFunctionListAdapter = new IndexFunctionListAdapter(mContext,
                R.layout.index_gridview_item, 5, appcontext);
        mBusinessInfoListAdapter = new BusinessInfoListAdapter(mContext,
                R.layout.business_list_item, 5, appcontext);
        mClassVideoListAdapter = new ClassVideoListAdapter(mContext,
                R.layout.classvideo_list_item, 5, appcontext);
        mViewFlow.setAdapter(mViewFlowAdapter, 0);
        checkIndic();
        mViewFlow.startAutoFlowTimer(); // 启动自动播放
        // 搜索框焦点事件处理
        et_search.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // 隐藏
                hideSoftKeyword();
                if (container != null) {
                    container.switchView(EngineModeWgt.class);
                }

            }
        });
        setViewListener();
        getInfo();
        setEventListener();

    }

    private void checkIndic() {
        if (mViewFlow.getmSideBuffer()>1){
            indic.setVisibility(View.VISIBLE);
        }else {
            indic.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 方法名：setViewListener<BR>
     * 此方法描述的是： 为View设置事件监听器
     */
    private void setViewListener() {
        // 云课堂
        bv_class_video.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Business item = mClassVideoListAdapter.getItem(position);
                // 跳转到云课堂
                if (isNullObj(item)) {
                    if (container != null) {
//						appcontext.launchParams.put(Consts.Actions.CLASSROOM_INFO, item);
//						container.switchView(ClassRoomWgt.class);
                        Intent intent = new Intent();
                        intent.setClass(mContext, ClassRoomActivity.class);
                        intent.putExtra(Consts.Actions.CLASSROOM_INFO, item);
                        mContext.startActivity(intent);
                    }
                }

            }
        });

    }

    /**
     * 获取信息
     */
    public void getInfo() {
        // 获取广告栏信息
        getAB();
        // 获取功能分类信息
        getFunctions();
        // 商品列表信息
        getBusinessList();
        // 推荐课程
        getClassVideoList();

    }

    /**
     * 方法名：getClassVideoList<BR>
     * 此方法描述的是： 推荐课程信息
     */

    private void getClassVideoList() {
        ImageLoader.getInstance().displayImage(
                appcontext.config.IMAGE_SERVER
                        + "/images/classvideo/classvideo_logo.jpg",
                iv_Vlass_Video, appcontext.option);
        List<Business> mDatas = new ArrayList<Business>();
        mDatas.add(new Business("100", "1000", "日常保养篇", "来，给您的电脑敷块面膜！",
                "/images/advertisement/jumi_flash_1.jpg", "100", "19"));
        mDatas.add(new Business("100", "1400", "系统安装篇", "贴心系统安装指南！",
                "/images/advertisement/jumi_flash_2.jpg", "490", "37"));
        mDatas.add(new Business("100", "1200", "办公技能篇", "独家办公技巧！",
                "/images/advertisement/jumi_flash_3.jpg", "700", "23"));
        mDatas.add(new Business("100", "1000", "蓝屏处理篇", "电脑死机，蓝屏故障！",
                "/images/advertisement/jumi_flash_4.jpg", "980", "87"));
        if (null != mDatas && mDatas.size() > 0) {
            mClassVideoListAdapter.setmDatas(mDatas);
            bv_class_video.setAdapter(mClassVideoListAdapter);
        }

    }

    /**
     * 方法名：getBusinessList<BR>
     * 此方法描述的是： 商品信息列表
     */
    private void getBusinessList() {
        List<Business> mDatas = new ArrayList<Business>();
        mDatas.add(new Business("100", "1000", "好运来电脑维修", "专业维修，微笑服务，好运来电脑！",
                "/images/functions/manage_update_icon.png", "100", "1.5"));
        mDatas.add(new Business("100", "1400", "鹏程电脑", "鹏程电脑竭诚为您服务！",
                "/images/functions/manage_tools_clear.png", "490", "1.2"));
        mDatas.add(new Business("100", "1200", "德州计算机服务中心", "专业的技术团队，周到的售后服务！",
                "/images/functions/manage_hongbao_icon.png", "700", "2.5"));
        mDatas.add(new Business("100", "1000", "广兰计算机科技", "一流的技术，周到的服务！",
                "/images/functions/manage_update_icon.png", "980", "3.2"));
        if (null != mDatas && mDatas.size() > 0) {
            mBusinessInfoListAdapter.setmDatas(mDatas);
            fl_business.setAdapter(mBusinessInfoListAdapter);
        }

    }

    /**
     * 获取广告栏信息
     */
    public void getAB() {
        {
            Request<AdvertisementInfo> mRequest = new AllModuleReqeust<AdvertisementInfo>(
                    appcontext.config.SERVER_MR_VPS + Constants.Api.INDEX_AB,
                    AdvertisementInfo.class);
            mRequest.add("type", "1");
            CallServer.getInstance().add(mContext, mRequest,
                    new HttpCallBack<AdvertisementInfo>() {

                        @Override
                        public void onSucceed(int what,
                                              Response<AdvertisementInfo> response) {

                            if (what == Constants.What.WHAT_INDEX_AB) {// 处理登录结果
                                AdvertisementInfo info = response.get();
                                if (null != info) {

                                    if (null!=info.returnCode&&info.returnCode.equals("0000")) {

                                        // 跳转到个人中心
                                        // appcontext.openToast(mContext,
                                        // info.toString());
                                        if (null != info.list
                                                && info.list.size() > 0) {
                                            mViewFlowAdapter.setList(info.list);
                                            mViewFlow.setmSideBuffer(info.list
                                                    .size()); // 实际图片张数，
                                            // 我的ImageAdapter实际图片张数为3
                                            mViewFlow.setSelection(info.list
                                                    .size() * 1000); // 设置初始位置
                                            mViewFlow.setAdapter(
                                                    mViewFlowAdapter, 0);
                                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                                    LayoutParams.WRAP_CONTENT,
                                                    LayoutParams.WRAP_CONTENT);
                                            layoutParams.gravity = Gravity.CENTER;

                                            indic.setLayoutParams(layoutParams);
                                            mViewFlow.setFlowIndicator(indic);
                                            checkIndic();
                                            mViewFlow.startAutoFlowTimer(); // 启动自动播放
                                            mViewFlowAdapter
                                                    .notifyDataSetInvalidated();

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
                    }, Constants.What.WHAT_INDEX_AB, false, false, true);
        }

    }

    /**
     * 获取功能分类信息
     */
    private void getFunctions() {
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

                                if (null!=info.returnCode&&info.returnCode.equals("0000")) {

                                    // 跳转到个人中心
                                    // appcontext.openToast(mContext,
                                    // info.returnMessage);
                                    if (null != info.list
                                            && info.list.size() > 0) {

                                        mFunctionListAdapter
                                                .setmDatas(info.list);

                                        gv_dome.setAdapter(mFunctionListAdapter);
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

    private void setEventListener() {
        iv_goQrcode.setOnClickListener(IndexWgt.this);
        gv_dome.setOnItemClickListener(IndexWgt.this);

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
    public void onBringToFront() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSentToBack() {

    }

    @Override
    public void onAddToStack() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestory() {

    }

    @Override
    public void onDispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public int leftBackVisibility() {
        // TODO Auto-generated method stub
        return View.GONE;
    }

    @Override
    public View setRightView() {
        // 标题右边添加View
        return null;
    }

    @Override
    public int getLifeCycle() {
        // TODO Auto-generated method stub
        return SINGLE | TRACE;
    }

    @Override
    public boolean needLogin() {
        // 是否需要登录
        return false;
    }

    @Override
    protected boolean needTitle() {
        // 是否需要标题
        return false;
    }

    @Override
    public boolean onUleEvent(MrEvent event, BaseWgt wgt, WGTContainer container) {
        // TODO Auto-generated method stub
        return super.onUleEvent(event, wgt, container);
    }

    @Override
    protected void startFromAction(Map<String, Object> params) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getPageName() {
        // 显示页面
        return "INDEX";
    }

    // 2.2系统以下都得加这个
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mViewFlow.onConfigurationChanged(newConfig);
    }

    @Override
    protected String getTitleString() {
        // 标题的文字
        return getResources().getString(R.string.foot_index);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_goQrcode:
                // appcontext.openToast(mContext,"扫描二维码");
                WorkingActivity wk = appcontext.wk;
                if (null != wk) {

                    // 扫描二维码
                    Intent dummy = new Intent(getContext(), CaptureActivity.class);

                    wk.startActivityForResult(dummy,
                            Consts.Intents.REQUEST_MORE_READBARCODE);
                    wk.setmCodeListerner(new WorkingActivity.CodeListerner() {
                        @Override
                        public void getCode(String code) {
                            UtilsToast.myToast(mContext, code);

                        }
                    });
                } else {
                    UtilsToast.myToast(mContext, "启动异常！");
                }
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Funcations funcations = mFunctionListAdapter.getItem(position);
        if (null != funcations) {

            UtilsToast.myToast(mContext, funcations.name);
        }

    }

    @Override
    public void hideFooter() {

    }

}