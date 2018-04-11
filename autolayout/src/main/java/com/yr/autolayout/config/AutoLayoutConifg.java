package com.yr.autolayout.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.yr.autolayout.utils.LogUtils;
import com.yr.autolayout.utils.ScreenUtils;

/**
 * autolayout配置信息
 * <p>
 * Created by yangrui on 2018/3/31.
 */
public class AutoLayoutConifg {

    private static AutoLayoutConifg sIntance = new AutoLayoutConifg();

    /**
     * 设计稿宽度meta name
     */
    private static final String KEY_DESIGN_WIDTH = "design_width";

    /**
     * 设计稿高度meta name
     */
    private static final String KEY_DESIGN_HEIGHT = "design_height";

    /**
     * mainifest中设置宽高是否使用设备宽高(是否包含状态栏)meta name
     */
    private static final String KEY_USE_DEVICE = "use_device";

    /**
     * 屏幕宽度
     */
    private int mScreenWidth;

    /**
     * 屏幕高度
     */
    private int mScreenHeight;

    /**
     * 设计搞宽度
     */
    private int mDesignWidth;

    /**
     * 设计稿高度
     */
    private int mDesignHeight;

    /**
     * 是否使用设备宽高(是否包含状态栏),默认使用
     */
    private boolean useDeviceSize;

    private AutoLayoutConifg() {
    }

    /**
     * 检查是否设置设计稿宽高
     */
    public void checkParams() {
        if (mDesignHeight <= 0 || mDesignWidth <= 0) {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.");
        }
    }

    /**
     * 宽高是否使用设备宽高
     *
     * @return
     */
    public AutoLayoutConifg useDeviceSize() {
        useDeviceSize = true;
        return this;
    }

    public static AutoLayoutConifg getInstance() {
        return sIntance;
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public int getDesignWidth() {
        return mDesignWidth;
    }

    public int getDesignHeight() {
        return mDesignHeight;
    }

    /**
     * 获取manifest配置参数及屏幕物理宽高
     * @param context
     */
    public void init(Context context) {
        getMetaData(context);

        int[] screenSize = ScreenUtils.getScreenSize(context, useDeviceSize);
        mScreenWidth = screenSize[0];
        mScreenHeight = screenSize[1];
        LogUtils.e(" screenWidth =" + mScreenWidth + " ,screenHeight = " + mScreenHeight);
    }

    /**
     * 获取manifest中配置参数
     * @param context
     */
    private void getMetaData(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(context
                    .getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                mDesignWidth = (int) applicationInfo.metaData.get(KEY_DESIGN_WIDTH);
                mDesignHeight = (int) applicationInfo.metaData.get(KEY_DESIGN_HEIGHT);
                if(applicationInfo.metaData.get(KEY_USE_DEVICE)!=null)
                    useDeviceSize = (boolean) applicationInfo.metaData.get(KEY_USE_DEVICE);
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.", e);
        }
        LogUtils.e(" designWidth =" + mDesignWidth + " , designHeight = " + mDesignHeight);
    }


}
