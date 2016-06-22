package com.computerdmaintenance.ui.wgt;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.computerdmaintenance.R;

import java.util.Map;


public class NetworkErr extends BaseWgt implements View.OnClickListener {

    private Button checkNetwork;

    public NetworkErr(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public NetworkErr(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public NetworkErr(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void initView(Context context) {
        // TODO Auto-generated method stub
        inflate(context, R.layout.wgt_neterror_layout, this);
        checkNetwork = (Button) findViewById(R.id.gc_checknet_btn);
        checkNetwork.setOnClickListener(this);
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
        return null;
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
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == checkNetwork) {
            goNetworkSetting();
        }
    }

    private void goNetworkSetting() {
        if (android.os.Build.VERSION.SDK_INT > 10) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            getContext().startActivity(intent);
        } else {
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            getContext().startActivity(intent);
        }
    }

    @Override
    public void hideFooter() {
        // TODO Auto-generated method stub

    }
}
