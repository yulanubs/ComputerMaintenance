package com.computerdmaintenance.ui.wgt;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.computerdmaintenance.R;
import com.computerdmaintenance.adapter.MessageInfoGridViewAdapter;
import com.computerdmaintenance.ui.component.MessageInfoGridView;
import com.computerdmaintenance.util.MrEventWebLogin;
import com.mr.cm.common.base.domain.MessageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrivateMessagWgt extends BaseWgt implements View.OnClickListener,
        OnItemClickListener {
    Handler handler = new Handler();
    private boolean isBack = false;
    /**
     * 消息列表
     */
    private MessageInfoGridView migv_InnovativeActivities;
    private MessageInfoGridViewAdapter mInfoGridViewAdapter;

    public PrivateMessagWgt(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

    }

    public PrivateMessagWgt(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

    }

    public PrivateMessagWgt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub

    }

    @Override
    protected void initView(Context context) {
        inflate(context, R.layout.privatemessagwgt_layout,
                PrivateMessagWgt.this);
        migv_InnovativeActivities = (MessageInfoGridView) findViewById(R.id.migv_InnovativeActivities);
        mInfoGridViewAdapter = new MessageInfoGridViewAdapter(mContext,
                R.layout.message_gridview_item, 5, appcontext);
        migv_InnovativeActivities.setOnItemClickListener(PrivateMessagWgt.this);
        migv_InnovativeActivities.setAdapter(mInfoGridViewAdapter);
        initdata();

    }

    private void initdata() {

        List<MessageInfo> list = new ArrayList<MessageInfo>();
        list.add(new MessageInfo("", "你好！", appcontext.config.IMAGE_SERVER
                + "/images/ic_launcher.png", "", "今天 15:30", "七里香"));
        list.add(new MessageInfo("", "你好！", appcontext.config.IMAGE_SERVER
                + "/images/ic_launcher.png", "", "今天 15:30", "七里香"));
        list.add(new MessageInfo("", "你好！", appcontext.config.IMAGE_SERVER
                + "/images/ic_launcher.png", "", "今天 15:30", "七里香"));
        list.add(new MessageInfo("", "你好！", appcontext.config.IMAGE_SERVER
                + "/images/ic_launcher.png", "", "今天 15:30", "七里香"));
        list.add(new MessageInfo("", "你好！", appcontext.config.IMAGE_SERVER
                + "/images/ic_launcher.png", "", "今天 15:30", "七里香"));
        list.add(new MessageInfo("", "你好！", appcontext.config.IMAGE_SERVER
                + "/images/ic_launcher.png", "", "今天 15:30", "七里香"));
        list.add(new MessageInfo("", "你好！", appcontext.config.IMAGE_SERVER
                + "/images/ic_launcher.png", "", "今天 15:30", "七里香"));
        list.add(new MessageInfo("", "你好！", appcontext.config.IMAGE_SERVER
                + "/images/ic_launcher.png", "", "今天 15:30", "七里香"));
        mInfoGridViewAdapter.setmDatas(list);

        mInfoGridViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBringToFront() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSentToBack() {
        // TODO Auto-generated method stub

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
        return View.GONE;
    }

    @Override
    public View setRightView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLifeCycle() {
        // TODO Auto-generated method stub
        return BaseWgt.SINGLE | BaseWgt.UNTRACE;
    }

    @Override
    public boolean needLogin() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected String getTitleString() {
        // TODO Auto-generated method stub
        return getResources().getString(R.string.login_login);
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
    public void onClick(View v) {
    }

    @Override
    public String getPageName() {
        // TODO Auto-generated method stub
        return "PRIVATEMESSAGWGT";
    }

	/*
     * @Override public void onBackClick() { // TODO Auto-generated method stub
	 * notLoginBack(); super.onBackClick(); }
	 */

    @Override
    public boolean onBackKeyDown() {
        // TODO Auto-generated method stub
        if (!isBack) {
            if (notLoginBack()) {
                return true;
            }
        }
        return super.onBackKeyDown();
    }

    private boolean notLoginBack() {
        isBack = true;
        if (container != null) {
            container.stepBack();
            container.alertUleEvent(new MrEventWebLogin("false"));
            return true;
        }
        return false;
    }

    @Override
    public void hideFooter() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

    }

}