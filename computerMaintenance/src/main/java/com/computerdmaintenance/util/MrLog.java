package com.computerdmaintenance.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MrLog {

    private static boolean mDebug = false;
    private static boolean mError = false;
    private static boolean mInfo = false;
    private static boolean mVerbose = false;
    private static boolean mWarn = false;
    private static boolean mFile = false;
    private static boolean mException = false;
    private static String fileName = "/.log.log";
    private static String packageName;
    private static int maxLength = 3800;

    public static void setEnable(boolean debug, boolean error, boolean info,
                                 boolean verbose, boolean warn, boolean exception) {
        mDebug = debug;
        mError = error;
        mInfo = info;
        mVerbose = verbose;
        mWarn = warn;
        mException = exception;
    }

    public static boolean isException() {
        return mException;
    }

    public static void excaption(Exception exception) {
        if (exception == null) {
            return;
        }
        if (mException) {
            exception.printStackTrace();
        }
    }

    private static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static void setPackageName(String package_name) {
        packageName = package_name;
    }

    public static void setfileLogEnable(boolean enable) {
        mFile = enable;
    }

    public static void debug(String tag, String message) {

        if (isEmpty(message)) {
            message = "null";
        }

        if (mDebug) {
            if (message.length() > maxLength) {
                Log.d(tag, message.substring(0, maxLength));
                debug(tag, message.substring(maxLength));
            } else {
                Log.d(tag, message);
            }
        }
        writeToLogFile(tag, message);
    }

    public static void error(String tag, String message) {
        if (isEmpty(message)) {
            message = "null";
        }

        if (mError) {
            if (message.length() > maxLength) {
                Log.e(tag, message.substring(0, maxLength));
                error(tag, message.substring(maxLength));
            } else {
                Log.e(tag, message);
            }
        }
        writeToLogFile(tag, message);
    }

    public static void info(String tag, String message) {
        if (isEmpty(message)) {
            message = "null";
        }

        if (mInfo) {
            if (message.length() > maxLength) {
                Log.i(tag, message.substring(0, maxLength));
                info(tag, message.substring(maxLength));
            } else {
                Log.i(tag, message);
            }
        }
        writeToLogFile(tag, message);
    }

    public static void verbose(String tag, String message) {
        if (isEmpty(message)) {
            message = "null";
        }

        if (mVerbose) {
            if (message.length() > maxLength) {
                Log.v(tag, message.substring(0, maxLength));
                verbose(tag, message.substring(maxLength));
            } else {
                Log.v(tag, message);
            }
        }
        writeToLogFile(tag, message);
    }

    public static void warn(String tag, String message) {
        if (isEmpty(message)) {
            message = "null";
        }

        if (mWarn) {
            if (message.length() > maxLength) {
                Log.w(tag, message.substring(0, maxLength));
                warn(tag, message.substring(maxLength));
            } else {
                Log.w(tag, message);
            }
        }
        writeToLogFile(tag, message);
    }

    public synchronized static void writeToLogFile(String tag, String message) {
        if (!mFile) {
            return;
        }
        File logFile = createOrOpenLogFile();

        if (logFile == null) {
            return;
        }

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(logFile, "rw");
            raf.seek(raf.length());
            raf.writeBytes(current());
            raf.writeBytes("\t");
            raf.writeBytes("TAG:\t");
            raf.write(tag.getBytes("gbk"));
            raf.writeBytes("\t\t");
            raf.write(message.getBytes("gbk"));
            raf.writeBytes("\n");
            raf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            MrLog.excaption(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            MrLog.excaption(e);
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    MrLog.excaption(e);
                }
            }
        }
    }

    private static String current() {
        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
        return sdf.format(new Date(now));
    }

    private static String getLogFileName() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            String sdcardRootDir = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
            if (packageName != null) {
                return sdcardRootDir + "/" + packageName + fileName;
            }
            return sdcardRootDir + "/.ULE" + fileName;
        }
        return null;
    }

    private static File createOrOpenLogFile() {
        String name = getLogFileName();
        if (name == null) {
            return null;
        }
        boolean suc = false;
        File log = new File(name);

        if (!log.exists()) {
            String parent = log.getParent();
            File dir = new File(parent);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                suc = log.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                MrLog.excaption(e);
                return null;
            }
        } else {
            long size = log.length();
            if (size > 10 * 1000 * 1000) {
                log.delete();
                try {
                    suc = log.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    MrLog.excaption(e);
                }
            } else {
                suc = true;
            }
        }
        if (suc) {
            return log;
        } else {
            return null;
        }
    }

}
