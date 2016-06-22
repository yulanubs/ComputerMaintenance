package com.computerdmaintenance.ui.wgt;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.computerdmaintenance.R;
import com.computerdmaintenance.adapter.SearchFunctionAdapter;
import com.computerdmaintenance.adapter.SearchListAdapter;
import com.computerdmaintenance.ui.component.Search_GridView;
import com.computerdmaintenance.ui.component.Search_List_ItemView;
import com.mr.cm.common.base.domain.SearchFeatured;
import com.mr.cm.common.base.domain.SearchInfo;
import com.mr.cm.common.base.domain.SearchLogo;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tom.ule.ui.view.image.UleImageView;

import java.util.ArrayList;
import java.util.Map;

/**
 * 诊断页面
 */
public class SearchWgt extends BaseWgt implements OnClickListener {

    public static final int RESULT_OK = -1;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private SearchFunctionAdapter mFunctionAdapter;
    private Search_List_ItemView sl_search_listview;
    private SearchListAdapter mSearchListAdapter;
    private Search_GridView sg_search_gridview;
    private UleImageView iv_searchlogo;
    private String TAG = "SearchFragment";
    private LinearLayout layout;

    public SearchWgt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public SearchWgt(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public SearchWgt(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void initView(Context context) {
        /* TODO Auto-generated method stub */
        View mView = inflate(context, R.layout.searchwgt_layout, this);
        init();
        initBindView(SearchWgt.this, mView);
        setData();

    }

    /**
     * 设置数据
     */
    private void setData() {

        ArrayList<SearchLogo> SearchLogoList = new ArrayList<SearchLogo>();
        SearchLogoList
                .add(new SearchLogo("002", "0x633",
                        "/computerdoctor/images/advertisement/jumi_flash_2.jpg"));
        ArrayList<SearchFeatured> SearchFeaturedList = new ArrayList<SearchFeatured>();

        ArrayList<SearchInfo> SearchInfolist = new ArrayList<SearchInfo>();
        SearchFeaturedList.add(new SearchFeatured("034", "0x992", "免费咨询", "专业团队 贴心服务",
                "http://photographic.paas.capitalcloud.net/images/2.png"));
        SearchFeaturedList.add(new SearchFeatured("022", "0x887", "免费维修", "足不出户 技比三家",
                "http://photographic.paas.capitalcloud.net/images/4.png"));
        SearchInfolist.add(new SearchInfo("011", "0x0001", "附近商家",
                "http://photographic.paas.capitalcloud.net/images/4.png"));
        SearchInfolist.add(new SearchInfo("011", "0x0001", "附近高手",
                "http://photographic.paas.capitalcloud.net/images/1.png"));
        SearchInfolist.add(new SearchInfo("011", "0x0001", "扫一扫",
                "http://photographic.paas.capitalcloud.net/images/3.png"));
        SearchInfolist.add(new SearchInfo("011", "0x0001", "去哪儿",
                "http://photographic.paas.capitalcloud.net/images/4.png"));

        ImageLoader.getInstance().displayImage(
                appcontext.config.SERVER_MR_VPS + SearchLogoList.get(0).getImgurl(), iv_searchlogo,
                appcontext.option);

        // 推荐位
        mFunctionAdapter = new SearchFunctionAdapter(mContext,
                R.layout.search_gridview_top, 5, appcontext);
        mFunctionAdapter.setmDatas(SearchFeaturedList);
        sg_search_gridview.setAdapter(mFunctionAdapter);

        // 功能列表
        mSearchListAdapter = new SearchListAdapter(mContext,
                R.layout.search_list_item, 5, appcontext);
        mSearchListAdapter.setmDatas(SearchInfolist);
        sl_search_listview.setAdapter(mSearchListAdapter);

    }

    @Override
    public void onBringToFront() {

    }

    private void init() {
        mContext = getContext();

        sl_search_listview = (Search_List_ItemView) findViewById(R.id.sl_search_listview);
        sg_search_gridview = (Search_GridView) findViewById(R.id.sg_search_gridview);
        iv_searchlogo = (UleImageView) findViewById(R.id.iv_searchlogo);
        layout = (LinearLayout) findViewById(R.id.notnetwork);
        layout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                initData();

            }
        });

        setViewListener();

    }

    /**
     * 方法名：setViewListener<BR>
     * 此方法描述的是： 给控件设置事件监听器
     */
    private void setViewListener() {
        // TODO Auto-generated method stub

    }

    /**
     * 方法名：initData<BR>
     * 此方法描述的是：初始化数据
     */

    protected void initData() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResume() {
        onBringToFront();
    }

    @Override
    public void onSentToBack() {
        // TODO Auto-generated method stub
        clearViewFocus();
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
        return View.INVISIBLE;
    }

    @Override
    public View setRightView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLifeCycle() {
        // TODO Auto-generated method stub
        return SINGLE | TRACE;
    }

    @Override
    public boolean needLogin() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected String getTitleString() {
        // TODO Auto-generated method stub
        return "发现";
    }

    @Override
    protected boolean needTitle() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void startFromAction(Map<String, Object> params) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getPageName() {
        // TODO Auto-generated method stub
        return "SEARCHWGT";
    }

    /**
     * 显示软键盘
     *
     * @param editText
     */
    private void showKeyboard(EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, 0);
        }
        moveCursor(editText);
    }

    /**
     * 隐藏软键盘
     */
    public void cancelKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        View vFocus = ((Activity) getContext()).getCurrentFocus();
        if (imm != null && vFocus != null) {
            imm.hideSoftInputFromWindow(vFocus.getWindowToken(), 0);
        }
        this.clearFocus();
    }

    /**
     * 光标移动到最后
     *
     * @param editText
     */
    private void moveCursor(EditText editText) {
        Editable etext = editText.getText();
        if (etext.length() < 1) {
        } else {
            Selection.setSelection(etext, etext.length());
        }
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        clearViewFocus();
        return super.dispatchKeyEventPreIme(event);
    }

    private void clearViewFocus() {
        View fadeView = ((Activity) getContext()).getCurrentFocus();
        if (fadeView != null)
            fadeView.clearFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }

    }

    @Override
    public void hideFooter() {
        // TODO Auto-generated method stub

    }
}
