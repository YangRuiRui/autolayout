package com.yr.autolayout.utils;

import android.util.Log;

/**
 * 日志工具类
 *
 * Created by yangrui on 2018/3/31.
 */
public class LogUtils {

    public static LogLevel LOG_LEVEL = LogLevel.WARN;

    private static final String TAG = "AUTO_LAYOUT";

    public static void d(String msg) {
        if (LOG_LEVEL.compareTo(LogLevel.DEBUG)<=0) {
            Log.e(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (LOG_LEVEL.compareTo(LogLevel.INFO)<=0) {
            Log.e(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (LOG_LEVEL.compareTo(LogLevel.WARN)<=0) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (LOG_LEVEL.compareTo(LogLevel.ERROR)<=0) {
            Log.e(TAG, msg);
        }
    }

}

/**
 * log级别
 */
enum LogLevel{
    DEBUG,INFO,WARN,ERROR,FATAL;
}
