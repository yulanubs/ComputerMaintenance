package com.computerdmaintenance.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushManager;
import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.loudmaintenance.util.Consts;
import com.loudmaintenance.util.MrLog;
import com.loudmaintenance.util.ScreenUtil;

import java.util.List;
import java.util.Map.Entry;

/**
 * @ClassName:Base <BR>
 * @Describe：BaseActivity<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-3-6 下午4:18:46
 */
public abstract class Base extends Activity {
    protected boolean isFirst = false;

    private ScreenBroadcastReceiver screenReceiver;
    private NetworkReceiver networkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        registerNetworkReceiver();
        // Log.e(this.toString(), "onCreate~~~");
        ((ComputerMaintenanceApplication) getApplication()).add(this);
        Resources resource = this.getResources();
        String pkgName = this.getPackageName();
        // Push: 如果想基于地理位置推送，可以打开支持地理位置的推送的开关
        // PushManager.enableLbs(getApplicationContext());

        // Push: 设置自定义的通知样式，具体API介绍见用户手册，如果想使用系统默认的可以不加这段代码
        // 请在通知推送界面中，高级设置->通知栏样式->自定义样式，选中并且填写值：1，
        // 与下方代码中 PushManager.setNotificationBuilder(this, 1, cBuilder)中的第二个参数对应
        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
                resource.getIdentifier(
                        "notification_custom_builder", "layout", pkgName),
                resource.getIdentifier("notification_icon", "id", pkgName),
                resource.getIdentifier("notification_title", "id", pkgName),
                resource.getIdentifier("notification_text", "id", pkgName));
        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
        cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);
        cBuilder.setLayoutDrawable(resource.getIdentifier(
                "simple_notification_icon", "drawable", pkgName));
        cBuilder.setNotificationSound(Uri.withAppendedPath(
                Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
        // 推送高级设置，通知栏样式设置为下面的ID
        PushManager.setNotificationBuilder(this, 1, cBuilder);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Log.e(this.toString(), "start onRestart~~~");
        registerScreenReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Log.e(this.toString(), "start onStart~~~");
        registerScreenReceiver();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Log.e(this.toString(), "start onResume~~~");
        checkNetwork();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Log.e(this.toString(), "start onPause~~~");
        ComputerMaintenanceApplication app = (ComputerMaintenanceApplication) getApplication();
        app.endLoading();
    }

    @Override
    protected void onDestroy() {
        // Log.e(this.toString(), "start onDestroy~~~");
        super.onDestroy();
        unregisterNetworkReceiver();
        ((ComputerMaintenanceApplication) getApplication()).remove(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MrLog.error(this.toString(), "start onStop~~~");
        unregisterScreenReceiver();

        if (!isAppOnForeground()) {
            ComputerMaintenanceApplication.ITEM_TRACK = "";
            ComputerMaintenanceApplication.STRONG_ITEM_TRACK = "";
        }
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    protected boolean goByAction() {
        Intent incoming = getIntent();
        if (incoming == null) {
            return false;
        }

        Action action = (Action) incoming
                .getSerializableExtra(Consts.Intents.INTENT_LAUNCH_ACTION);
        if (action == null) {
            return false;
        }
        jumpInner(action);
        return true;
    }

    public void jump(Action act) {
        if (act.actyName.startsWith(Consts.ULE_PACKAGE)) {
            if (getClass().getName().equals(act.actyName)) {
                jumpInner(act);
            } else {
                try {
                    Class<?> acty = Class.forName(act.actyName);
                    Intent jump = new Intent(this, acty);
                    jump.putExtra(Consts.Intents.INTENT_LAUNCH_ACTION, act);
                    startActivity(jump);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            try {
                Class<?> acty = Class.forName(act.actyName);
                Intent go = new Intent(this, acty);
                if (act.parameters != null && act.parameters.size() > 0) {
                    Bundle bundle = new Bundle();
                    for (Entry<String, Object> entry : act.parameters
                            .entrySet()) {
                        String key = entry.getKey();
                        String value = (String) entry.getValue();
                        bundle.putString(key, value);
                    }
                    go.putExtra(Consts.Intents.INTENT_JUMP_BUNDLE, bundle);
                }
                startActivity(go);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void registerScreenReceiver() {
        if (screenReceiver == null) {
            screenReceiver = new ScreenBroadcastReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        this.registerReceiver(screenReceiver, filter);
    }

    private void unregisterScreenReceiver() {
        if (screenReceiver != null) {
            this.unregisterReceiver(screenReceiver);
        }
    }

    private void registerNetworkReceiver() {
        if (networkReceiver == null) {
            networkReceiver = new NetworkReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(networkReceiver, filter);
    }

    private void unregisterNetworkReceiver() {
        if (networkReceiver != null) {
            this.unregisterReceiver(networkReceiver);
        }
    }

    protected abstract void jumpInner(Action act);

    private boolean checkNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        boolean connected = (ni != null);
        ComputerMaintenanceApplication.networkAlive = connected;
        notifyNetworkChange(connected);
        return connected;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);
        this.setIntent(intent);
    }

    protected abstract void notifyNetworkChange(boolean connected);

    protected void setImmerseLayout(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int statusBarHeight = ScreenUtil.getStatusBarHeight(this.getBaseContext());
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
                MrLog.info(Base.this.getClass().getSimpleName(),
                        "ACTION_SCREEN_ON");
            } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
                MrLog.info(Base.this.getClass().getSimpleName(),
                        "ACTION_SCREEN_OFF");
                ComputerMaintenanceApplication.ITEM_TRACK = "";
                ComputerMaintenanceApplication.STRONG_ITEM_TRACK = "";
            }
        }
    }

    private class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent == null) {
                return;
            }
            Bundle extras = intent.getExtras();
            if (extras == null) {
                return;
            }
            checkNetwork();
        }
    }


}