package com.loudmaintenance.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 此类根据手机属性生成设备唯一标识(DId) 生成规则如下： 1.先生成格式为imei,androidId的字符串
 * 如果其中之一为空，则用-1代替,如imei号为空串为:-1,androidId 如果是模拟器则为-1,emulator001
 * 2.对字符串取16位md5,再加上2位标识符，形成18位did
 */
public class DIdUtil {
    private volatile static DIdUtil instance = null;
    private Context mContext;
    private String androidId;
    private String deviceId;
    private String dId;

    /**
     * 构造
     *
     * @param context
     */
    private DIdUtil(Context context) {
        super();
        mContext = context;
    }

    /**
     * 实现单例
     *
     * @param context
     * @return
     */
    public static DIdUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (DIdUtil.class) {
                instance = new DIdUtil(context);
            }
        }
        return instance;
    }

    /**
     * md5对生成的deviceId取16位md5值 再加后成3位标志位
     *
     * @param str
     * @param is16Bit
     * @return
     */
    private String md5(String str, Boolean is16Bit) {
        String md5Str = null;
        String flagDevice = "00";
        String inputString = "";
        if (str.length() > 2) {
            flagDevice = str.substring(str.length() - 2, str.length());
            inputString = str.substring(0, str.length() - 2);
        }
        if (inputString != null && !TextUtils.isEmpty(inputString)) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(inputString.getBytes());
                byte b[] = md.digest();
                int i;
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }
                if (is16Bit) {
                    md5Str = buf.toString().substring(8, 24);
                } else {
                    md5Str = buf.toString();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        md5Str += flagDevice;
        return md5Str;
    }

    /**
     * 此类根据手机属性生成设备唯一标识(DId) 生成规则如下： 1.先生成格式为imei,androidId的字符串
     * 如果其中之一为空，则用-1代替,如imei号为空串为:-1,androidId 如果是模拟器则为-1,emulator001
     * 2.对字符串取16位md5,再加上2位标识符，形成18位did
     */
    public String getDId() {
        if (TextUtils.isEmpty(dId)) {
            dId = md5(getDiviceId(), true);
        }
        return dId;
    }

    /**
     * 返回
     *
     * @return
     */
    private String getDiviceId() {
        if (this.deviceId == null) {
            if (isEmulator()) {
                this.deviceId = "-1,emulator001";
            } else {
                this.deviceId = generateDeviceId();
            }
        }
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "kong3_uid";
        }
        return this.deviceId;
    }

    /**
     * 生成格式为imei,imsi,androidId的字符串
     *
     * @return
     */
    private String generateDeviceId() {
        StringBuffer localStringBuffer = new StringBuffer();
        String flagDevice = "";

        try {
            // imei
            String str1 = getImei();
            if (!TextUtils.isEmpty(str1)) {
                localStringBuffer.append(str1);
                flagDevice = "1";
            } else {
                localStringBuffer.append("-1");
                flagDevice = "0";
            }
            localStringBuffer.append(",");

            // androidId
            String str2 = getAndroidId();
            if (str2 != null && !TextUtils.isEmpty(str2)) {
                localStringBuffer.append(str2);
                flagDevice += "1";
            } else {
                localStringBuffer.append("-1");
                flagDevice += "0";
            }
            // 标识位
            localStringBuffer.append(flagDevice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localStringBuffer.toString();
    }

    /**
     * 获取IMEI
     *
     * @param context
     * @return
     */
    private String getImei() {
        TelephonyManager localTelephonyManager = (TelephonyManager) mContext.getSystemService("phone");
        return localTelephonyManager.getDeviceId();
    }

    /**
     * 获取androidId
     *
     * @param context
     * @return
     */
    private String getAndroidId() {
        if (TextUtils.isEmpty(this.androidId))
            this.androidId = Settings.Secure.getString(mContext.getContentResolver(), "android_id");
        return this.androidId;
    }

    /**
     * 判断是否是模拟器
     *
     * @param context
     * @return
     */
    private boolean isEmulator() {
        if (TextUtils.isEmpty(this.androidId))
            this.androidId = getAndroidId();
        return (TextUtils.isEmpty(this.androidId)) && ("sdk".equalsIgnoreCase(Build.MODEL));
    }

}
