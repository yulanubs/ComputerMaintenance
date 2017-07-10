package com.computerdmaintenance.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.computerdmaintenance.R;
import com.computerdmaintenance.ui.Action;
import com.computerdmaintenance.ui.Base;
import com.computerdmaintenance.ui.WGTContainer;

public class SplashActivity extends Base {
    protected static final int NET_FAIL = 2;
    protected static final int VERSION_FAIL = 3;
    protected static final int VERSION_SUCCESS = 4;
    /**
     * 设置线程睡眠时间
     */
    private static final int TIME = 1000;
    /**
     * 进入主页常量为1000
     */
    private static final int GO_HOSE = 1000;
    /**
     * 进入引导页常量为1001
     */
    private static final int GO_GUIDE = 1001;
    protected WGTContainer container;
    /**
     * 是否进行引导页面
     */
    private boolean isFirstIn = false;
    // 线程通信
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GO_HOSE:
                    // 跳转登录界面
                    goHose();

                    break;

                case GO_GUIDE:
                    // 跳转到引导界面

                    // goGuide();
                    goHose();
                    break;
            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // checkUpVersionb();
        PushManager.startWork(getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY, "QrtW23Sk0lWxERAOp5V6gspC");
        show();

    }

    @Override
    protected void onResume() {
        // JPushInterface.onResume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        // JPushInterface.onPause(this);
        super.onPause();
    }

    @Override
    protected void jumpInner(Action act) {

    }

    @Override
    protected void notifyNetworkChange(boolean connected) {

    }

    private void show() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(TIME);
                    runApp();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }.start();

    }

    // private void checkUpVersionb() {
    // Updata mUpdata = new Updata(SplashActivity.this);
    //
    // UpdateUtil.LoadPackageInfo();
    // mUpdata.startUpdateCheck();
    // }

    /**
     * 方法名： init <BR>
     * 此方法描述的是：判断是否是首次进入应用 <BR>
     */
    public void runApp() {
        // 实例化一个共享首选项
        SharedPreferences perPreferences = getSharedPreferences("ben",
                MODE_PRIVATE);
        isFirstIn = perPreferences.getBoolean("isFirstIn", true);
        if (!isFirstIn) {
            // 首次获取没有值，发生进入登录界面消息
            mHandler.sendEmptyMessage(GO_HOSE);
        } else {
            mHandler.sendEmptyMessage(GO_GUIDE);
            // 实例化编辑对象
            Editor editor = perPreferences.edit();
            // 存入isFirstIn状态
            editor.putBoolean("isFirstIn", false);
            // 提交修改
            editor.commit();
        }
    }

    /**
     * 方法名： goLogin <BR>
     * 此方法描述的是：跳转到登录界面 <BR>
     */
    private void goHose() {
        Intent intent = new Intent(SplashActivity.this, WorkingActivity.class);
        this.startActivity(intent);
        this.finish();

    }

}
