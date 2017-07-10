package com.computerdmaintenance.device;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.computerdmaintenance.R;
import com.computerdmaintenance.config.Config;
import com.computerdmaintenance.util.MrLog;
import com.computerdmaintenance.util.UtilTools;

import java.io.File;

public class deviceManager {
    public androiddevice deviceInfo = null;
    private int REQUEST_READ_PHONE_STATE=0x12312;

    @SuppressLint("DefaultLocale")
    @SuppressWarnings("deprecation")
    public void init(Context conext) {
        TelephonyManager svr = (TelephonyManager) conext
                .getSystemService(Context.TELEPHONY_SERVICE);
        deviceInfo = new androiddevice();
        int permissionCheck = ContextCompat.checkSelfPermission(conext, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) conext, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            if (svr != null) {

                deviceInfo.setLine1num(svr.getLine1Number());
                deviceInfo.setImei(svr.getDeviceId());
                deviceInfo.setImsi(svr.getSubscriberId());
                deviceInfo.setNet(deviceInfo.net(svr.getNetworkType()));
            }
        }

        deviceInfo.setAndroid_market(conext.getString(R.string.marketId));
        WindowManager wm = (WindowManager) conext
                .getSystemService(Context.WINDOW_SERVICE);
        deviceInfo
                .setDisplayDensity(conext.getResources().getDisplayMetrics().densityDpi);
        if (wm != null) {
            Display dm = wm.getDefaultDisplay();
            deviceInfo.setDisplayWidth(dm.getWidth());
            deviceInfo.setDisplayHeight(dm.getHeight());
            deviceInfo.setRefrashRatio(dm.getRefreshRate());
            DisplayMetrics dmt = new DisplayMetrics();
            dm.getMetrics(dmt);
            deviceInfo.setDensity(dmt.density);

        }
        deviceInfo.setOS_Product(android.os.Build.PRODUCT);
        deviceInfo.setOS_CPU_ABI(android.os.Build.CPU_ABI);
        deviceInfo.setOS_TAGS(android.os.Build.TAGS);
        deviceInfo
                .setOS_VERSION_CODES_BASE(android.os.Build.VERSION_CODES.BASE);
        deviceInfo.setOS_MODEL(android.os.Build.MODEL);
        deviceInfo.setOS_SDK(android.os.Build.VERSION.SDK);
        deviceInfo.setOS_SDK_INT(android.os.Build.VERSION.SDK_INT);
        deviceInfo.setOS_VERSION_RELEASE(android.os.Build.VERSION.RELEASE);
        deviceInfo.setOS_DEVICE(android.os.Build.DEVICE);
        deviceInfo.setOS_DISPLAY(android.os.Build.DISPLAY);
        deviceInfo.setOS_BRAND(android.os.Build.BRAND);
        deviceInfo.setOS_BOARD(android.os.Build.BOARD);
        deviceInfo.setOS_FINGERPRINT(android.os.Build.FINGERPRINT);
        deviceInfo.setOS_ID(android.os.Build.ID);
        deviceInfo.setOS_MANUFACTURER(android.os.Build.MANUFACTURER);
        deviceInfo.setOS_USER(android.os.Build.USER);
        try {
            ConnectivityManager cm = (ConnectivityManager) conext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                deviceInfo.setNetworktype(info.getType()); // WIFI/MOBILE
                deviceInfo.setNetType(info.getTypeName());
                if (deviceInfo.getNetType().equals("mobile")) {
                    deviceInfo.setNetType(deviceInfo.net);
                }
                if (deviceInfo.isMobileNet())
                    deviceInfo.setExtranetworkinfo(info.getExtraInfo()
                            .toLowerCase());
            }
        } catch (Exception e) {

        }
        WifiManager wifiManager = (WifiManager) conext
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        deviceInfo.setMacAddress(wifiInfo.getMacAddress());
        deviceInfo.setAndroidID(Secure.getString(conext.getContentResolver(),
                Secure.ANDROID_ID));
        refrashstoragestatic(conext);
    }

    @SuppressWarnings("deprecation")
    public void refrashstoragestatic(Context conext) {
        double blocksize = 0;
        double bloccount = 0;
        deviceInfo.setSharedState(android.os.Environment
                .getExternalStorageState().equals(Environment.MEDIA_SHARED));
        deviceInfo.setSdPresent(android.os.Environment
                .getExternalStorageState().equals(
                        android.os.Environment.MEDIA_MOUNTED));
        if (deviceInfo.isSdPresent()) {
            // String sd="/mnt/sd";
            deviceInfo.setExternalStorageDirectory(Environment
                    .getExternalStorageDirectory());
            if (deviceInfo.getExternalStorageDirectory() != null) {
                StatFs sf = new StatFs(
                        deviceInfo.ExternalStorageDirectory.getPath());
                blocksize = sf.getBlockSize();
                bloccount = sf.getAvailableBlocks();
                MrLog.debug(deviceManager.class.toString(),
                        String.valueOf(blocksize));
                MrLog.debug(deviceManager.class.toString(),
                        String.valueOf(bloccount));
                deviceInfo.setAvailableBlocksInSD(blocksize * bloccount / 1024
                        / 1024);
                MrLog.debug(deviceManager.class.toString(),
                        String.valueOf(deviceInfo.getAvailableBlocksInSD()));
            }
        }
        deviceInfo.setDataDirectory(Environment.getDataDirectory());
        if (null != deviceInfo.getDataDirectory()) {
            StatFs sf = new StatFs(deviceInfo.DataDirectory.getPath());
            blocksize = sf.getBlockSize();
            bloccount = sf.getAvailableBlocks();
            MrLog.debug(deviceManager.class.toString(),
                    String.valueOf(blocksize));
            deviceInfo.setAvailableBlocksInApp(bloccount * blocksize / 1024
                    / 1024);
        }
        deviceInfo.setRootDirectory(Environment.getRootDirectory());
        if (deviceInfo.getRootDirectory() != null) {
            StatFs sf = new StatFs(deviceInfo.RootDirectory.getPath());
            blocksize = sf.getBlockSize();
            bloccount = sf.getAvailableBlocks();
            MrLog.debug(deviceManager.class.toString(),
                    String.valueOf(blocksize));
            deviceInfo.setAvailableBlocksInRoot(blocksize * bloccount / 1024
                    / 1024);
        }
        checkcachepath(conext);
    }

    private void checkcachepath(Context conext) {
        if (deviceInfo.isSdPresent()) {
            File temp = deviceInfo.getExternalStorageDirectory();
            File f = new File(temp.getPath()
                    + Config.COMPUTERMAINTENANCE_CACHE_PATH);
            File f2 = new File(temp.getPath()
                    + Config.COMPUTERMAINTENANCE_DOWNLOAD_PATH);
            deviceInfo.setExternalcachepath(f, f2);
        }
        File temp = conext.getCacheDir();
        File f = new File(temp.getPath() + "/download");
        deviceInfo.setInternalcachepath(f);
    }

    @SuppressWarnings("unused")
    private void modifyp() {
        // [文件夹705:drwx---r-x]
        String[] args1 = {"chmod", "705",
                deviceInfo.internaldownload.getPath()};
        UtilTools.exec(args1);
    }
}