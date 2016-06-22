package com.computerdmaintenance.device;

import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;

import com.loudmaintenance.util.UtilTools;
import com.mr.cm.common.base.domain.DeviceInfo;

import java.io.File;


public class androiddevice extends DeviceInfo {

    protected String PromotionSales_CACHE_PATH = "/PromotionSales/Cache";
    protected String PromotionSales_DOWNLOAD_PATH = "/PromotionSales/download";
    protected String macAddress;
    protected String androidID;
    protected String line1num;
    protected int displayDensity = 160;
    protected int displayWidth = 480;
    protected int displayHeight = 800;
    protected float refrashRatio = 60;
    protected float density = 1;
    protected String netType;
    protected String net;
    protected String OS_Product; // android.os.Build.PRODUCT;
    protected String OS_CPU_ABI; // android.os.Build.CPU_ABI;
    protected String OS_TAGS;// android.os.Build.TAGS;
    protected int OS_VERSION_CODES_BASE;// android.os.Build.VERSION_CODES.BASE;
    protected String OS_MODEL;// android.os.Build.MODEL;
    protected String OS_SDK;// android.os.Build.VERSION.SDK;
    protected int OS_SDK_INT;// android.os.Build.VERSION.SDK_INT;
    protected String OS_VERSION_RELEASE;// android.os.Build.VERSION.RELEASE;
    protected String OS_DEVICE;// android.os.Build.DEVICE;
    protected String OS_DISPLAY;// android.os.Build.DISPLAY;
    protected String OS_BRAND;// android.os.Build.BRAND;
    protected String OS_BOARD;// android.os.Build.BOARD;
    protected String OS_FINGERPRINT;// android.os.Build.FINGERPRINT;
    protected String OS_ID;// android.os.Build.ID;
    protected String OS_MANUFACTURER;// android.os.Build.MANUFACTURER;
    protected String OS_USER;// android.os.Build.USER;
    protected boolean isSdPresent;
    protected double AvailableBlocksInSD;
    protected double AvailableBlocksInRoot;
    protected double AvailableBlocksInApp;
    protected File DataDirectory;// 获取应用存储空间路径
    protected File ExternalStorageDirectory;// 获取SD卡文件路径。
    protected File RootDirectory; // 获取系统空间文件路径。
    protected boolean netconnected;
    protected int networktype;
    protected String extranetworkinfo;
    protected String SessionID;
    protected String ipAddress;
    protected String APN;
    protected File externalcachepath;
    protected File externaldownloadpath;
    protected File internalcachepath;
    protected File internaldownload;
    protected boolean isSharedState;
    protected String appVersionName;
    protected String UUID;
    protected String android_market;

    public androiddevice(String imei, String imsi, String user_agent,
                         String device_id, String device_type) {
        super(imei, imsi, user_agent, device_id, device_type);
        // TODO Auto-generated constructor stub
    }

    public androiddevice() {
        super("", "", "android", "", "android");
    }

    // protected boolean cachelocal=false;

    public String getPromotionSales_CACHE_PATH() {
        return PromotionSales_CACHE_PATH;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String uUID) {
        UUID = uUID;
    }

    public String getAndroid_market() {
        return android_market;
    }

    public void setAndroid_market(String android_market) {
        this.android_market = android_market;
    }

    public void setULE_CACHE_PATH(String uLE_CACHE_PATH) {
        PromotionSales_CACHE_PATH = uLE_CACHE_PATH;
    }

    public String getULE_DOWNLOAD_PATH() {
        return PromotionSales_DOWNLOAD_PATH;
    }

    public void setULE_DOWNLOAD_PATH(String promotionSales_DOWNLOAD_PATH) {
        PromotionSales_DOWNLOAD_PATH = promotionSales_DOWNLOAD_PATH;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getAndroidID() {
        return androidID;
    }

    public void setAndroidID(String androidID) {
        this.androidID = androidID;
        super.setDeviceId(this.androidID);
    }

    public String getLine1num() {
        return line1num;
    }

    public void setLine1num(String line1num) {
        this.line1num = line1num;
    }

    public int getDisplayDensity() {
        return displayDensity;
    }

    public void setDisplayDensity(int displayDensity) {
        this.displayDensity = displayDensity;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public float getRefrashRatio() {
        return refrashRatio;
    }

    public void setRefrashRatio(float refrashRatio) {
        this.refrashRatio = refrashRatio;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getOS_Product() {
        return OS_Product;
    }

    public void setOS_Product(String oS_Product) {
        OS_Product = oS_Product;
    }

    public String getOS_CPU_ABI() {
        return OS_CPU_ABI;
    }

    public void setOS_CPU_ABI(String oS_CPU_ABI) {
        OS_CPU_ABI = oS_CPU_ABI;
    }

    public String getOS_TAGS() {
        return OS_TAGS;
    }

    public void setOS_TAGS(String oS_TAGS) {
        OS_TAGS = oS_TAGS;
    }

    public int getOS_VERSION_CODES_BASE() {
        return OS_VERSION_CODES_BASE;
    }

    public void setOS_VERSION_CODES_BASE(int oS_VERSION_CODES_BASE) {
        OS_VERSION_CODES_BASE = oS_VERSION_CODES_BASE;
    }

    public String getOS_MODEL() {
        return OS_MODEL;
    }

    public void setOS_MODEL(String oS_MODEL) {
        OS_MODEL = oS_MODEL;
    }

    public String getOS_SDK() {
        return OS_SDK;
    }

    public void setOS_SDK(String oS_SDK) {
        OS_SDK = oS_SDK;
    }

    public int getOS_SDK_INT() {
        return OS_SDK_INT;
    }

    public void setOS_SDK_INT(int oS_SDK_INT) {
        OS_SDK_INT = oS_SDK_INT;
    }

    public String getOS_VERSION_RELEASE() {
        return OS_VERSION_RELEASE;
    }

    public void setOS_VERSION_RELEASE(String oS_VERSION_RELEASE) {
        OS_VERSION_RELEASE = oS_VERSION_RELEASE;
    }

    public String getOS_DEVICE() {
        return OS_DEVICE;
    }

    public void setOS_DEVICE(String oS_DEVICE) {
        OS_DEVICE = oS_DEVICE;
    }

    public String getOS_DISPLAY() {
        return OS_DISPLAY;
    }

    public void setOS_DISPLAY(String oS_DISPLAY) {
        OS_DISPLAY = oS_DISPLAY;
    }

    public String getOS_BRAND() {
        return OS_BRAND;
    }

    public void setOS_BRAND(String oS_BRAND) {
        OS_BRAND = oS_BRAND;
    }

    public String getOS_BOARD() {
        return OS_BOARD;
    }

    public void setOS_BOARD(String oS_BOARD) {
        OS_BOARD = oS_BOARD;
    }

    public String getOS_FINGERPRINT() {
        return OS_FINGERPRINT;
    }

    public void setOS_FINGERPRINT(String oS_FINGERPRINT) {
        OS_FINGERPRINT = oS_FINGERPRINT;
    }

    public String getOS_ID() {
        return OS_ID;
    }

    public void setOS_ID(String oS_ID) {
        OS_ID = oS_ID;
    }

    public String getOS_MANUFACTURER() {
        return OS_MANUFACTURER;
    }

    public void setOS_MANUFACTURER(String oS_MANUFACTURER) {
        OS_MANUFACTURER = oS_MANUFACTURER;
    }

    public String getOS_USER() {
        return OS_USER;
    }

    public void setOS_USER(String oS_USER) {
        OS_USER = oS_USER;
    }

    public boolean isSdPresent() {
        return isSdPresent;
    }

    public void setSdPresent(boolean isSdPresent) {
        this.isSdPresent = isSdPresent;
    }

    public double getAvailableBlocksInSD() {
        return AvailableBlocksInSD;
    }

    public void setAvailableBlocksInSD(double availableBlocksInSD) {
        AvailableBlocksInSD = availableBlocksInSD;
    }

    public double getAvailableBlocksInRoot() {
        return AvailableBlocksInRoot;
    }

    public void setAvailableBlocksInRoot(double availableBlocksInRoot) {
        AvailableBlocksInRoot = availableBlocksInRoot;
    }

    public double getAvailableBlocksInApp() {
        return AvailableBlocksInApp;
    }

    public void setAvailableBlocksInApp(double availableBlocksInApp) {
        AvailableBlocksInApp = availableBlocksInApp;
    }

    public File getDataDirectory() {
        return DataDirectory;
    }

    public void setDataDirectory(File dataDirectory) {
        DataDirectory = dataDirectory;
    }

    public File getExternalStorageDirectory() {
        return ExternalStorageDirectory;
    }

    public void setExternalStorageDirectory(File externalStorageDirectory) {
        ExternalStorageDirectory = externalStorageDirectory;
    }

    public File getRootDirectory() {
        return RootDirectory;
    }

    public void setRootDirectory(File rootDirectory) {
        RootDirectory = rootDirectory;
    }

    public boolean isNetconnected() {
        return netconnected;
    }

    public void setNetconnected(boolean netconnected) {
        this.netconnected = netconnected;
    }

    public int getNetworktype() {
        return networktype;
    }

    public void setNetworktype(int networktype) {
        this.networktype = networktype;
    }

    public String getExtranetworkinfo() {
        return extranetworkinfo;
    }

    public void setExtranetworkinfo(String extranetworkinfo) {
        this.extranetworkinfo = extranetworkinfo;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String sessionID) {
        SessionID = sessionID;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAPN() {
        return APN;
    }

    public void setAPN(String aPN) {
        APN = aPN;
    }

    public File getExternalcachepath() {
        return externalcachepath;
    }

    public void setExternalcachepath(File externalcachepath,
                                     File externaldownloadpath) {
        if (!externalcachepath.exists())
            externalcachepath.mkdirs();
        if (!externaldownloadpath.exists())
            externaldownloadpath.mkdirs();

        this.externalcachepath = externalcachepath;
        this.externaldownloadpath = externaldownloadpath;
    }

    public File getExternaldownloadpath() {
        return externaldownloadpath;
    }

    public File getInternalcachepath() {
        return internalcachepath;
    }

    public void setInternalcachepath(File internalcachepath) {
        if (!internalcachepath.exists()) {

            internalcachepath.mkdirs();

            String[] args1 = {"chmod", "705", internalcachepath.getPath()};

            UtilTools.exec(args1);
        }

        this.internalcachepath = internalcachepath;
    }

    public File getInternaldownload() {
        return internalcachepath;
    }

    /*
        public File getInternaldownload() {
            return internaldownload;
        }

        public void setInternaldownload(File internaldownload) {
            this.internaldownload = internaldownload;
        }
    */
    public boolean isSharedState() {
        return isSharedState;
    }

    public void setSharedState(boolean isSharedState) {
        this.isSharedState = isSharedState;
    }

    public boolean isCachelocal() {
        return this.externalcachepath != null && externaldownloadpath.exists()
                && this.externaldownloadpath != null
                && this.externaldownloadpath.exists();
    }

    public String net(int nType) {
        switch (nType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "GSM"; // 2G中移，中联
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA"; // 2G中电
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "CDMA2000"; // 3G中电
            case TelephonyManager.NETWORK_TYPE_UMTS:
                // case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "WCDMA"; // 3G中联
            default:
                return "TD-SCDMA";
        }
    }

    public boolean isMobileNet() {
        return (networktype == ConnectivityManager.TYPE_MOBILE
        /*
         * || networktype == ConnectivityManager.TYPE_MOBILE_DUN || networktype
		 * == ConnectivityManager.TYPE_MOBILE_HIPRI || networktype ==
		 * ConnectivityManager.TYPE_MOBILE_MMS || networktype ==
		 * ConnectivityManager.TYPE_MOBILE_SUPL
		 */);

    }

    public boolean isWifiNet() {
        return (networktype == ConnectivityManager.TYPE_WIFI /*
                                                             * || networktype ==
															 * ConnectivityManager
															 * .TYPE_WIMAX
															 */);

    }

    public String deviceinfojson() {
        // TODO Auto-generated method stub
		/*StringBuilder devicemsg = new StringBuilder();
		JSONObject jo = new JSONObject();
		try {
			jo.put("M_SESID", getSessionID());
			jo.put("M_LINENUM", (line1num == null || line1num.equals(""))?"":line1num);
			jo.put("M_IMEI", (getImei() == null || getImei().equals(""))?"":getImei());
			jo.put("M_IMSI", (getImsi() == null || getImsi().equals(""))?"":getImsi());
			jo.put("M_NWT", String.valueOf(networktype));
			jo.put("M_ENKI", extranetworkinfo);
			//jo.put("M_MAC", macAddress.hashCode());
			jo.put("M_MAC", macAddress);
//			jo.put("M_IP", ipAddress);
			jo.put("M_APN", APN);
			//jo.put("M_ANDROID_ID",((imei == null || imei.equals(""))?"":imei.hashCode()));
			jo.put("M_RANDROID_ID", androidID);
			//jo.put("M_ANDROID_ID", M_ANDROID_ID);
			jo.put("M_RX", String.valueOf(displayWidth));
			jo.put("M_RY", String.valueOf(displayHeight));
			jo.put("M_PRD", OS_Product);
			jo.put("M_CPUABI", OS_CPU_ABI);
			jo.put("M_TAGS", OS_TAGS);
			jo.put("M_VCB", String.valueOf(OS_VERSION_CODES_BASE));
			jo.put("M_MODEL",OS_MODEL);
			
			jo.put("M_SDK",OS_SDK);
			jo.put("M_SDKINT",String.valueOf(OS_SDK_INT));
			jo.put("M_VL",OS_VERSION_RELEASE);
			jo.put("M_DVC",OS_DEVICE);
			jo.put("M_NET",netType);
			jo.put("M_DSP",OS_DISPLAY);
			jo.put("M_BRAND",OS_BRAND);
			jo.put("M_BOARD",OS_BOARD);
			jo.put("M_MARKET_ID", android_market);
			jo.put("M_FINGERPRINT",OS_FINGERPRINT);
			jo.put("M_ID",OS_ID);
			jo.put("M_MNFCT",OS_MANUFACTURER);
			jo.put("M_USR",OS_USER);
			jo.put("M_APP_VS", appVersionName);
			jo.put("UUID", UUID);
//			jo.put("UUID", "UU ID");
			
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// UleLog.excaption(e);
		}
		return jo.toString();*/
        return "";
    }


}
