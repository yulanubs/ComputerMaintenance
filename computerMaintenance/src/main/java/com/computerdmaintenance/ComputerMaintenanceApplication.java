package com.computerdmaintenance;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import com.computerdmaintenance.device.deviceManager;
import com.computerdmaintenance.ui.activity.WorkingActivity;
import com.computerdmaintenance.ui.view.MrLoadingDialog;
import com.computerdmaintenance.util.Consts;
import com.computerdmaintenance.util.DIdUtil;
import com.computerdmaintenance.util.MrLog;
import com.computerdmaintenance.util.StorageUtils;
import com.computerdmaintenance.util.UtilTools;
import com.computerdmaintenance.util.ValueUtils;
import com.mr.cm.common.base.domain.AppInfo;
import com.mr.cm.common.base.domain.UserInfo;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:ComputerMaintenanceApplication <BR>
 * @Describe： 系统的Application的继承。<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-3 上午10:05:43
 */
public class ComputerMaintenanceApplication extends Application {
    /**
     * 登录标志
     */
    public static final String LOGINUSER = "loginUser";
    public final static String MR_PREFERENCES = "MRPreferences";
    /**
     * Appecret
     */
    public final static String appsecret = "2e5413a688c0497fbc9bdafe7394435f";
    /**
     * UUID的SPKey
     */
    public final static String COMPUTERMAINTENANCE_PREFERENCES = "computermaintenance_preferences";
    /**
     * UUID
     */
    public static final String UUID = "UUID";
    /***/
    public static ComputerMaintenanceApplication instance;
    /**
     * 是否处于联网状态
     */
    public static boolean networkAlive = true;
    public static String ITEM_TRACK = "";
    public static String STRONG_ITEM_TRACK = "";
    /**
     * 加解密key
     */
    public static String PASSWORD_KEY = "";
    /***
     * 加解密向量
     */
    public static String PASSWORD_IV = "";
    /**
     * SessionID
     */
    public static String MR_ANDROID_ID = "";
    /**
     * AppKey
     */
    private static String appkey = "";
    public Map<String, Object> launchParams = new HashMap<String, Object>();
    /**
     * 设备管理器
     */
    public deviceManager dev = null;
    /**
     * 用户信息
     */
    public UserInfo domainUser;
    public String TAG = "cmapp";
    /**
     * 进度对话框
     */
    public MrLoadingDialog bar = null;
    /**
     * 测试环境开关,false为Beta环境，true为生产环境
     */
    public boolean isBetaorPrd = true;
    /**
     * 应用包信息
     */
    public PackageInfo packageinfo = null;
    /**
     * 配置信息
     */
    public Config config = null;
    /**
     * App信息
     */
    public AppInfo appinfo = null;
    public DisplayImageOptions option;
    public WorkingActivity wk;
    /**
     * Activity容器
     */
    private List<WeakReference<Activity>> as = new ArrayList<WeakReference<Activity>>();
    /**
     * 是否打开日志的调试模式
     */
    private boolean debug = true;
    /**
     * 百度推送api_key
     */
    private String baidu_api_key = "QrtW23Sk0lWxERAOp5V6gspC";

    /**
     * 方法名：getInstance<BR>
     * 此方法描述的是：得到应用程序的application
     *
     * @return {@link Application}
     */
    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init(this);
        // 密码加密
        if (isBetaorPrd) {
            // 生产环境
            PASSWORD_KEY = "r1dpfQlEKuACcSGTM4zqFvx1olbGWtZP";
            PASSWORD_IV = "9MNGK48cV3fsKyyI";
            appkey = "06ae28b476174ad383a799f6ddaebbe4";

        } else {
            // Beta环境

            PASSWORD_KEY = "8K4AMdGsSeepdEV6wlcVov1qlDO4mAR8";
            PASSWORD_IV = "ohCE8IUspYjbJnwD";
            appkey = "fe286343f5a24115bdae32d93f638fc4";
        }

        if (debug) {
            MrLog.setEnable(true, true, true, true, true, true);
            MrLog.setPackageName(getPackageName());
            MrLog.setfileLogEnable(true);
        } else {
            MrLog.setEnable(false, false, false, false, false, false);
            MrLog.setfileLogEnable(false);
        }
    }

    /**
     * 方法名：init<BR>
     * 此方法描述的是： 初始化
     *
     * @param app
     */
    public void init(ComputerMaintenanceApplication app) {
        /** 获取登录信息 */
        getSavedLogin();
        // 初始化NoHttp
        initNoHttp();
        // 加载包信息
        LoadPackageInfo();
        // 加载配置信息
        LoadConfig();
        // 创建APP信息
        CreatAPPInfo();
        //初始化百度推送
        initBaiDuPush();
        // 初始化ImageLoader
        initImageLoader(getApplicationContext());


    }

    /**
     * 方法名：initBaiDuPush<BR>
     * 此方法描述的是：     初始化百度推送
     */

    private void initBaiDuPush() {
        // 启动百度push


    }

    /**
     * 加载ImageLoader
     *
     * @param context
     */

    public void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,
                "ComputerMaintenance/Cache");
        if (cacheDir == null) {
            cacheDir = Environment.getDownloadCacheDirectory();
        }




        option = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.plugin_camera_no_pictures)
                .showImageOnFail(R.drawable.plugin_camera_no_pictures)
                .bitmapConfig(Bitmap.Config.ARGB_4444).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true).build();

        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .threadPoolSize(3)
                .memoryCache(new LRULimitedMemoryCache(3 * 1024 * 1024))
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .imageDownloader(new BaseImageDownloader(getApplicationContext()))
                .defaultDisplayImageOptions(option);

        ImageLoaderConfiguration config = builder.build();
        ImageLoader.getInstance().init(config);

    }

    /**
     * 方法名：LoadDeviceInfo<BR>
     * 此方法描述的是： 加载设备信息
     */
    public void LoadDeviceInfo(Activity activity) {
        dev = new deviceManager();
        dev.init(activity);
        // add by xqq
        dev.deviceInfo.setSessionID(getSessionID());
        dev.deviceInfo.setAppVersionName(packageinfo.versionName);
        dev.deviceInfo.setUUID(getcachedUUID());
        dev.refrashstoragestatic(this);
    }

    /**
     * 方法名：CreatAPPInfo<BR>
     * 此方法描述的是： 创建APP信息
     */
    public void CreatAPPInfo() {

        // String.valueOf(packageinfo.versionCode)

        appinfo = new AppInfo("apr_2016_build01",

                packageinfo.versionName, this.getSessionID(),
                getSharedPreferences(UUID), appkey, appsecret, config.marketId,
                "ule");
    }

    /**
     * 方法名：getcachedUUID<BR>
     * 此方法描述的是：获得CcachedUUID
     *
     * @return String
     */
    public String getcachedUUID() {
        try {
            SharedPreferences sp = getSharedPreferences(
                    COMPUTERMAINTENANCE_PREFERENCES, 0);
            return sp.getString("UUID", "");
        } catch (Exception e) {
            MrLog.error(deviceManager.class.toString(), e.toString());
            return "";
        }
    }

    /**
     * 方法名LoadConfig：<BR>
     * 此方法描述的是： 加载配置信息
     */
    private void LoadConfig() {
        config = new Config();
        config.marketId = this.getResources().getString(R.string.marketId);
        config.SERVER_Game = this.getResources()
                .getString(R.string.SERVER_Game).trim();
        config.SERVER_Game_Push = this.getResources().getString(
                R.string.SERVER_Game_Push).trim();
        config.IMAGE_SERVER=this.getResources().getString(
                R.string.IMAGE_SERVER).trim();
        if (isBetaorPrd) {
            // 生产环境
            config.SERVER_ULE_BASE = this.getResources().getString(
                    R.string.SERVER_MR_PRD_BASE).trim();
            config.SERVER_MR_VPS = this.getResources()
                    .getString(R.string.SERVER_MR_PRD_VPS).trim();

        } else {
            // Beta环境
            config.SERVER_ULE_BASE = this.getResources().getString(
                    R.string.SERVER_MR_BETA_BASE).trim();
            config.SERVER_MR_VPS = this.getResources()
                    .getString(R.string.SERVER_MR_BETA_VPS).trim();

        }

        config.UPDATE_KEY = this.getResources().getString(R.string.UPDATE_KEY).trim();

    }

    /**
     * 方法名：getSessionID<BR>
     * 此方法描述的是：获取SessionID
     *
     * @return String
     */
    public String getSessionID() {

        if (MR_ANDROID_ID == null) {
            MR_ANDROID_ID = DIdUtil.getInstance(this).getDId();
        }
        MrLog.error("ddddddddddddddddddddddddddd", MR_ANDROID_ID);
        return MR_ANDROID_ID;
    }

    /**
     * 方法名：LoadPackageInfo<BR>
     * 此方法描述的是： 加载包信息
     */
    public void LoadPackageInfo() {
        PackageManager pm = getPackageManager();
        try {
            packageinfo = pm.getPackageInfo(this.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            MrLog.excaption(e);
            packageinfo = null;
        }
    }

    /**
     * 方法名：initNoHttp<BR>
     * 此方法描述的是：初始化NoHttp
     */
    private void initNoHttp() {
        // 初始化NoHttp
        NoHttp.init(this);
        // 打开NoHttp的调试模式
        Logger.setDebug(true);
        // 设置NoHttp的日志的tag
        Logger.setTag("ComputerMaintenance");

    }

    /**
     * 方法名：LogOut<BR>
     * 此方法描述的是：退出登录的方法
     */
    public void LogOut() {
        domainUser = new UserInfo();
        removeSharedPreferences(LOGINUSER);
        removeBindStatus();
    }

    /**
     * 方法名：removeSharedPreferences<BR>
     * 此方法描述的是：保存sp
     */
    public void removeSharedPreferences(String key) {
        try {
            SharedPreferences sp = this.getSharedPreferences(MR_PREFERENCES, 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.remove(key);
            editor.commit();
        } catch (Exception e) {
            MrLog.error(this.toString(), e.toString());
        }
    }

    /**
     * 方法名：removeBindStatus<BR>
     * 此方法描述的是： 移除绑定
     */
    public void removeBindStatus() {
        try {
            SharedPreferences sp = getSharedPreferences(
                    ComputerMaintenanceApplication.MR_PREFERENCES,
                    Context.MODE_PRIVATE);
            sp.edit().remove(Consts.Preference.MOBILE_IS_BIND).commit();
        } catch (Exception e) {
            MrLog.error(deviceManager.class.toString(), e.toString());
        }
    }

    Toast toast;
    public void openToast(Context context, String message) {
        if (ValueUtils.isEmpty(context))
            context=getApplicationContext();
        if(null!=toast){
            toast.setText(message);
        }else{
            toast=Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        toast.show();
    }
    public void openToast(String message){
        if(null!=toast){
            toast.setText(message);
        }else{
            toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public void openToast(Context context, int resId) {
        if (ValueUtils.isEmpty(context))
            context=getApplicationContext();
        if(null!=toast){
            toast.setText(this.getResources().getString(resId));
        }else{
            toast=Toast.makeText(context, this.getResources().getString(resId), Toast.LENGTH_SHORT);
        }
        toast.show();
    }


    /**
     * 方法名：islogin<BR>
     * 此方法描述的是：判断是否登录
     */
    public boolean islogin() {
        if (domainUser == null || domainUser.userToken == null
                || domainUser.userToken.length() <= 0) {
            return getSavedLogin();
        } else {
            return true;
        }
    }

    /**
     * 保存登陆
     *
     * @param user
     */
    public void saveLogin(UserInfo user) {
        try {
            domainUser = user;
            setSharedPreferences(LOGINUSER, UtilTools.toString(user));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            MrLog.error(this.toString(), "save login error!");
            // UleLog.excaption(e);
        }
    }

    public void loginSuccess(UserInfo userInfo) {
        domainUser = userInfo;
        // getBindStatus();
    }

    /**
     * 方法名：getSavedLogin<BR>
     * 此方法描述的是： 获得登录信息
     */
    public boolean getSavedLogin() {
        try {
            domainUser = (UserInfo) UtilTools
                    .fromString(getSharedPreferences(LOGINUSER));
            if (domainUser.bindMobile == null) {
                LogOut();
                return false;
            }
            getBindStatus();
            return true;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            MrLog.error(this.toString(), "get saved login error!");
            // MRLog.excaption(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            MrLog.error(this.toString(), "get saved login error!");
            // MRLog.excaption(e);
        }
        return false;
    }

    /**
     * 方法名：getSharedPreferences<BR>
     * 此方法描述的是：获得sp
     *
     * @param key
     * @return String
     */
    public String getSharedPreferences(String key) {
        try {
            SharedPreferences sp = this.getSharedPreferences(MR_PREFERENCES, 0);
            return sp.getString(key, "");

        } catch (Exception e) {
            MrLog.error(this.toString(), e.toString());
        }
        return "";
    }

    /**
     * 方法名：mobileIsBind<BR>
     * 此方法描述的是： 是否绑定手机号
     *
     * @return boolean
     */
    public boolean mobileIsBind() {
        return domainUser.bindMobile.trim().equals("1") ? true : false;
    }

    /**
     * 方法名：getBindStatus<BR>
     * 此方法描述的是： 获得绑定状态
     */
    public void getBindStatus() {
        MrLog.debug(TAG, "user.bindMobile:" + domainUser.bindMobile);
        if (mobileIsBind()) {
            return;
        }
    }

    /**
     * 方法名：add<BR>
     * 此方法描述的是： 添加activity到缓存容器中
     */
    public void add(Activity a) {
        WeakReference<Activity> w = new WeakReference<Activity>(a);
        as.add(w);
    }

    /**
     * 方法名：endLoading<BR>
     * 此方法描述的是：关闭加载进度对话框
     */
    public void endLoading() {
        if (bar != null) {
            bar.dismiss();
            bar = null;
        }
    }

    /**
     * 方法名：remove<BR>
     * 此方法描述的是： 移除activity
     */
    public void remove(Activity a) {
        for (int i = 0; i < as.size(); i++) {
            WeakReference<Activity> w = as.get(i);
            Activity ca = w.get();
            if (a == ca) {
                as.remove(w);
                w.clear();
                break;
            }
        }
    }

    /**
     * 方法名：setSharedPreferences<BR>
     * 此方法描述的是：保存sp
     *
     * @param key
     * @param value
     */
    public void setSharedPreferences(String key, String value) {
        try {
            SharedPreferences sp = this.getSharedPreferences(MR_PREFERENCES, 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, value);
            editor.commit();
        } catch (Exception e) {
            MrLog.error(this.toString(), e.toString());
        }
    }

    public void startLoading(Context context) {
        if (bar == null) {
            bar = new MrLoadingDialog(context, R.style.mydialog, "数据加载中,请稍候...");
        } else {
            if (bar.isShowing()) {
                bar.dismiss();
            }
            if (bar.getContext() != context) {
                bar = new MrLoadingDialog(context, R.style.mydialog,
                        "数据加载中,请稍候...");
            }
        }
        if (context instanceof Activity) {
            Activity a = (Activity) context;
            if (a.isFinishing()) {
                MrLog.debug("MR_TAG", a.isFinishing() + "");
            } else {
                bar.show();
            }
        } else {
            bar.show();
        }
    }

    public void startLoading(Context context, String message) {
        if (bar == null)
            bar = new MrLoadingDialog(context, R.style.mydialog, message);
        if (context instanceof Activity) {
            Activity a = (Activity) context;
            if (a.isFinishing()) {
                MrLog.debug("MR_TAG", a.isFinishing() + "");
            } else {
                bar.show();
            }
        } else {
            bar.show();
        }
    }

}
