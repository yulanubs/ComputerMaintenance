package com.computerdmaintenance.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.Config;
import com.computerdmaintenance.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilTools {
    public final static String FILE_PATH_PRE = "file:///";
    public final static String ULE_PREFERENCES = "ulePreferences";
    public final static String HAVE_CITY_KEY = "1";
    public final static String NO_CITY_KEY = "0";
    /**
     * 零长度的字符串
     */
    public static final String NULL_STRING = "";
    public static int loadNum = 0;
    public static Map<String, String> shareWeb = new HashMap<String, String>();
    public static String saveTimeFormat = "yyyy-MM-dd HH:mm:ss";
    @SuppressWarnings("unused")
    private static char[] base64EncodeChars = new char[]{'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};
    @SuppressWarnings("unused")
    private static byte[] base64DecodeChars = new byte[]{-1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
            60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
            -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
            38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
            -1, -1};

    /**
     * 方法名：startCheck<BR>
     * 此方法描述的是： 正则验证方法
     *
     * @param reg
     * @param string
     * @return boolean
     */
    public static boolean startCheck(String reg, String string) {
        boolean tem = false;

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);

        tem = matcher.matches();
        return tem;
    }

    public static String getWeek(int week) {
        String toweek = "";
        if (week == 2) {
            // toweek = "Monday";
            toweek = "周一";
        } else if (week == 3) {
            // toweek = "Tuesday";
            toweek = "周二";
        } else if (week == 4) {
            // toweek = "Wednesday";
            toweek = "周三";
        } else if (week == 5) {
            // toweek = "Thursday";
            toweek = "周四";
        } else if (week == 6) {
            // toweek = "Friday";
            toweek = "周五";
        } else if (week == 7) {
            // toweek = "Saturday";
            toweek = "周六";
        } else if (week == 1) {
            // toweek = "Sunday";
            toweek = "周日";
        }
        return toweek;
    }

    public static int lastIndexOf(String src, String sp) {
        return src.lastIndexOf(sp);
    }

    public static String appedImageTypeSuffix(String url, String suffix) {
        try {
            return url.substring(0, UtilTools.lastIndexOf(url, ".")) + "_"
                    + suffix + url.substring(UtilTools.lastIndexOf(url, "."));
        } catch (Exception e) {
            return "";
        }
    }

    public static String formatYJWS(float number) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(number);
        return format;
    }

    public static String formatCurrency(String number) {
        if (number == null) {
            return "";
        }
        String outStr = number;
        if (outStr.indexOf(".") > 0) {
            String pre = outStr.substring(0, outStr.indexOf(".") + 1);
            String off = outStr.substring(outStr.indexOf(".") + 1);
            if (off.length() == 1) {
                outStr = pre + off + "0";
            } else {
                outStr = pre + off.substring(0, 2);
            }
        } else {
            outStr = outStr + ".00";
        }

        outStr = StringHelper.pricethousandformat
                .format(Double.valueOf(outStr));
        return outStr;
    }

    public static String getBigDecimal(String price) {
        if (price == null || price.equals("")) {
            return "";
        }
        float ft = Float.parseFloat(price);
        int scale = 2;// 设置位数
        int roundingMode = 4;// 表示四舍五入
        BigDecimal bd = new BigDecimal((double) ft);
        bd = bd.setScale(scale, roundingMode);
        ft = bd.floatValue();

        return String.valueOf(ft);
    }

    public static String stringMatch(Context context, String input,
                                     TextView textView) {
        int view_Length = getDisplayWidth(context) - dip2Px(context, 20)
                - dip2Px(context, 120);
        return stringMatch(context, input, textView, view_Length);
    }

    public static String stringMatch(Context context, String input,
                                     TextView textView, int view_Length) {
        boolean alreadyAppend = false;
        String outPutString = input;
        TextPaint paint = textView.getPaint();
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            if (paint.measureText(sb.toString()) >= view_Length
                    && !alreadyAppend) {
                sb.insert(i - 1, "\n");
                alreadyAppend = true;
            }
        }
        outPutString = sb.toString();
        return outPutString;
    }

    public static String filepathformat(String path) {
        if (!path.startsWith(FILE_PATH_PRE))
            path = FILE_PATH_PRE + path;
        String pattern = "/{3,}";
        return path.replaceAll(pattern, "///");
    }

    public static String exec(String[] args) {
        String result = "";
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process = null;
        InputStream errIs = null;
        InputStream inIs = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int read = -1;
            process = processBuilder.start();
            errIs = process.getErrorStream();
            while ((read = errIs.read()) != -1) {
                baos.write(read);
            }
            // baos.write('/n');
            inIs = process.getInputStream();
            while ((read = inIs.read()) != -1) {
                baos.write(read);
            }
            byte[] data = baos.toByteArray();
            result = new String(data);
        } catch (IOException e) {
            MrLog.excaption(e);
        } catch (Exception e) {
            MrLog.excaption(e);
        } finally {
            try {
                if (errIs != null) {
                    errIs.close();
                }
                if (inIs != null) {
                    inIs.close();
                }
            } catch (IOException e) {
                MrLog.excaption(e);
            }

            if (process != null) {
                process.destroy();
            }
        }
        return result;
    }

    /**
     * Read the object from Base64 string.
     */
    public static Object fromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.decode(s, Base64.DEFAULT);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
                data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    /**
     * Write the object to a Base64 string.
     */
    public static String toString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
    }

    /**
     * convert dip to px
     *
     * @param ctx Context
     * @param dip
     * @return
     */
    public static int dip2Px(Context ctx, float dip) {

        float density = ctx.getResources().getDisplayMetrics().density;
        float px = dip * density + 0.5f;

        return (int) px;
    }

    /**
     * convert px to dip
     *
     * @param context
     * @param px
     * @return
     */
    public static int px2dip(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + 0.5f);
        return dip;
    }

    /**
     * convert px to sp
     *
     * @param context
     * @param px
     * @return
     */
    public static int px2sp(Context context, float px) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }

    /**
     * convert sp to px
     *
     * @param context
     * @param sp
     * @return
     */
    public static int sp2px(Context context, float sp) {
//		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
//		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager windowManager = ((Activity) context).getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        float fontScale = outMetrics.scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    public static int getDisplayWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics = context.getApplicationContext().getResources()
                .getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getDisplayHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics = context.getApplicationContext().getResources()
                .getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static String getCurrentTime(SimpleDateFormat sdf) {
        Date d = new Date(System.currentTimeMillis());
        String current = sdf.format(d);
        return current;
    }

    public static Bitmap createBitmapFromRes(Context context, int resId,
                                             int sWidth) {
        Bitmap bmp = BitmapFactory
                .decodeResource(context.getResources(), resId);
        int screenWidth = sWidth;// context.getResources().getDisplayMetrics().widthPixels;
        int bmpW = bmp.getWidth();
        int bmpH = bmp.getHeight();
        int newBmpH = bmpH * screenWidth / bmpW;

        bmp = Bitmap.createScaledBitmap(bmp, screenWidth, newBmpH, true);
        return bmp;
    }

    public static Bitmap createBitmapFromDrawable(Drawable d, int sWidth) {
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bmp = bd.getBitmap();
        int screenWidth = sWidth;// getResources().getDisplayMetrics().widthPixels;
        int bmpW = bmp.getWidth();
        int bmpH = bmp.getHeight();
        int newBmpH = bmpH * screenWidth / bmpW;
        bmp = Bitmap.createScaledBitmap(bmp, screenWidth, newBmpH, true);
        return bmp;
    }

    public static Bitmap createSizeImage(Bitmap bitmap, int newWidth,
                                         int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return resizedBitmap;
    }

    public static String returnTime(long milliseconds) {
        String string = "";
        long s = 1000;
        long m = s * 60;
        long h = m * 60;
        long d = h * 24;

        long _d = milliseconds % d;
        long _h = _d % h;
        long _m = _h % m;
        if (milliseconds / d == 0) {
            return "最后疯抢中";
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(milliseconds / d).append("天").append("  ").append(_d / h)
                .append("时").append(_h / m).append("分").append(_m / s)
                .append("秒");
        string = buffer.toString();
        return string;
    }

    public static String returnTimeByHtml(long milliseconds) {
        String string = "";
        long s = 1000;
        long m = s * 60;
        long h = m * 60;
        long d = h * 24;

        long _d = milliseconds % d;
        long _h = _d % h;
        long _m = _h % m;

        StringBuffer buffer = new StringBuffer();
        buffer.append("剩余: ").append("<font color=\"#d94f8c\">")
                .append(to2Str(milliseconds / d)).append("</font>").append("天")
                .append("<font color=\"#d94f8c\">").append(to2Str(_d / h))
                .append("</font>").append("时")
                .append("<font color=\"#d94f8c\">").append(to2Str(_h / m))
                .append("</font>").append("分")
                .append("<font color=\"#d94f8c\">").append(to2Str(_m / s))
                .append("</font>").append("秒");
        string = buffer.toString();
        return string;
    }

    public static String returnTimeForMidAutmnEvent(long milliseconds) {

        long s = 1000;
        long m = s * 60;
        long h = m * 60;
        long d = h * 24;

        long _d = milliseconds % d;
        long _h = _d % h;
        long _m = _h % m;

        String hour;
        String minute;
        String second;

        if (_d / h < 10) {
            hour = "0" + _d / h;
        } else {
            hour = _d / h + "";
        }
        if (_h / m < 10) {
            minute = "0" + _h / m;

        } else {
            minute = _h / m + "";
        }
        if (_m / s < 10) {
            second = "0" + _m / s;
        } else {
            second = _m / s + "";
        }
        return " " + hour.substring(0, 1) + "   " + hour.substring(1, 2)
                + "  :  " + minute.substring(0, 1) + "   "
                + minute.substring(1, 2) + "  :  " + second.substring(0, 1)
                + "   " + second.substring(1, 2) + " ";
    }

    public static String returnTimeForVIMidAutmnEvent(long milliseconds) {

        long s = 1000;
        long m = s * 60;
        long h = m * 60;
        long d = h * 24;

        long _d = milliseconds % d;
        long _h = _d % h;
        long _m = _h % m;

        String hour;
        String minute;
        String second;

        if (milliseconds / h < 10) {
            hour = "0" + milliseconds / h;
        } else {
            hour = milliseconds / h + "";
        }
        if (_h / m < 10) {
            minute = "0" + _h / m;

        } else {
            minute = _h / m + "";
        }
        if (_m / s < 10) {
            second = "0" + _m / s;
        } else {
            second = _m / s + "";
        }
        return "   " + hour + "  :  " + minute + "  :  " + second + " ";
    }

    public static String returnTimeForVIMidAutmnEvent2(long milliseconds) {

        long s = 1000;
        long m = s * 60;
        long h = m * 60;
        long d = h * 24;

        long _d = milliseconds % d;
        long _h = _d % h;
        long _m = _h % m;

        String day;
        String hour;
        String minute;
        String second;
        if (milliseconds / d == 0) {
            day = "";
        } else if (milliseconds / d < 10 && milliseconds / d > 0) {
            day = "0" + milliseconds / d;
        } else {
            day = milliseconds / d + "";
        }

        if (_d / h < 10) {
            hour = "0" + _d / h;
        } else {
            hour = _d / h + "";
        }
        if (_h / m < 10) {
            minute = "0" + _h / m;

        } else {
            minute = _h / m + "";
        }
        if (_m / s < 10) {
            second = "0" + _m / s;
        } else {
            second = _m / s + "";
        }
        return TextUtils.isEmpty(day) ? " " + hour + " 时 " + minute + " 分 "
                + second + " 秒 " : " " + day + " 天 " + hour + " 时 " + minute
                + " 分 " + second + " 秒 ";
    }

    private static String to2Str(long data) {
        if (data < 10) {
            return "0" + data;
        } else
            return String.valueOf(data);
    }

    public static Bitmap createBitmapFromDrawable(Context context, Drawable d) {
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bmp = bd.getBitmap();
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int bmpW = bmp.getWidth();
        int bmpH = bmp.getHeight();
        int newBmpH = bmpH * screenWidth / bmpW;
        bmp = Bitmap.createScaledBitmap(bmp, screenWidth, newBmpH, true);
        return bmp;
    }

    public static void ToastShow(Activity activity, String text_String) {
        if (activity == null) {
            return;
        }
        TextView textView = new TextView(activity);
        textView.setText(text_String);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(30);
        textView.setBackgroundResource(R.drawable.rectangle);
        textView.setPadding(50, 5, 50, 5);

        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(textView);
        toast.show();
    }

    public static String getCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/" + Config.PromotionSales_CACHE_PATH;
        } else {
            return context.getCacheDir().getAbsolutePath();
        }
    }

    public static String getTempPath(Context context) {
        String mPicTempName = "";
        String mPathFile = "";
        ComputerMaintenanceApplication app = (ComputerMaintenanceApplication) context
                .getApplicationContext();
        if (app.dev.deviceInfo.isSdPresent()) {
            File storeFile = app.dev.deviceInfo.getExternaldownloadpath();
            if (storeFile != null) {
                if (!storeFile.exists()) {
                    storeFile.mkdirs();
                }
                mPathFile = storeFile.getAbsolutePath();
            } else {
                File cacheFile = app.dev.deviceInfo.getInternaldownload();
                if (cacheFile != null) {
                    if (!cacheFile.exists()) {
                        cacheFile.mkdirs();
                    }
                    mPathFile = cacheFile.getAbsolutePath();
                }
            }
        } else {
            File cacheFile = app.dev.deviceInfo.getInternaldownload();
            if (cacheFile != null) {
                if (!cacheFile.exists()) {
                    cacheFile.mkdirs();
                    mPathFile = cacheFile.getAbsolutePath();
                }
            }
            mPathFile = cacheFile.getAbsolutePath();
        }
        File pathFile = new File(mPathFile);
        if (!pathFile.exists() && !pathFile.isDirectory()) {
            pathFile.mkdirs();
        }
        mPicTempName = new StringBuilder(mPathFile)/*
                                                     * .append("/Ule")
													 * .append(timeStr
													 * ).append(".jpg")
													 */.toString();

        return mPicTempName;
    }

    public static String updateMessageFormat(String input) {
        if (input == null || input.equals("")) {
            return "";
        }
        String[] inputs = input.split("##");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputs.length; i++) {
            sb.append(inputs[i]).append("\n");
        }
        return sb.toString();
    }

    @SuppressLint("SimpleDateFormat")
    public static String Date2String(String dateString) {
        String timeStr = "";
        SimpleDateFormat timeFormat = new SimpleDateFormat(saveTimeFormat);
        try {
            Date date = timeFormat.parse(dateString);
            SimpleDateFormat format = new SimpleDateFormat("MM/dd");
            timeStr = format.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            MrLog.excaption(e);
        }
        return timeStr;
    }

    public static String getDiscount(String salePrice, String marketParice) {
        String count = "";
        try {
            double now = Double.valueOf(TextUtils.isEmpty(salePrice) ? "0"
                    : salePrice);
            double nor = Double.valueOf(marketParice);
            double dis = now / nor;
            BigDecimal bd = new BigDecimal(dis);
            DecimalFormat priceformat = new DecimalFormat("0.0");
            count = String.valueOf(priceformat.format(bd.setScale(2,
                    BigDecimal.ROUND_UP).doubleValue() * 10));
        } catch (Exception e) {
            // TODO: handle exception
            MrLog.excaption(e);
        }
        return count;
    }

    public static boolean checkLoacalVersionNew(String mVersionName,
                                                String netVersionName) {
        String[] mVersion = mVersionName.split("\\.");
        String[] netVersion = netVersionName.split("\\.");
        if (mVersion.length == 0 || mVersion.length != netVersion.length) {
            return false;
        } else {
            for (int i = 0; i < mVersion.length; i++) {
                int m = Integer.valueOf(mVersion[i]);
                int net = Integer.valueOf(netVersion[i]);
                if (m < net) {
                    return false;
                } else if (m == net) {
                    continue;
                } else if (m > net) {
                    return true;
                }
            }
            return true;
        }
    }

    /**
     * 返回android item版本区域
     *
     * @param versonStr
     * @return
     */
    public static String getVersionInterval(String versonStr) {
        String version = "";
        if (versonStr.indexOf("&") > 0) {
            String[] min = versonStr.split("&");
            version = min[0];
        } else {
            version = versonStr;
        }
        return version;
    }

    public static Bitmap convertStringToIcon(String st) {

        Bitmap bitmap = null;
        try {

            byte[] bitmapArray;
            String image = URLDecoder.decode(st, "UTF-8");
            bitmapArray = Base64.decode(image, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);

            return bitmap;
        } catch (Exception e) {
            MrLog.error("Exception", e.getMessage());
            return null;
        }
    }

    /**
     * 方法名：<BR>
     * 此方法描述的是：URL 双重转码
     *
     * @param pwd
     * @throws UnsupportedEncodingException void
     */
    public static String urlCode(String pwd) throws UnsupportedEncodingException {
        return pwd = URLEncoder.encode(URLEncoder.encode(pwd, "UTF-8"), "UTF-8");
    }

    public static String urlDecode(String pwd) throws UnsupportedEncodingException {
        return pwd = URLDecoder.decode(URLDecoder.decode(pwd, "UTF-8"), "UTF-8");

    }

    /**
     * 去除小数点后多余的0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }

}