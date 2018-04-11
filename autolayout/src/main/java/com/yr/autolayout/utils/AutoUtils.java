package com.yr.autolayout.utils;

import android.view.View;

import com.yr.autolayout.AutoLayoutInfo;
import com.yr.autolayout.attr.Attrs;
import com.yr.autolayout.attr.BaseType;
import com.yr.autolayout.config.AutoLayoutConifg;

/**
 * px换算工具类
 * <p>
 * Created by yangrui on 2018/4/10.
 */
public class AutoUtils {

    /**
     * 将某控件某属性的px换算后赋值
     *
     * @param view       换算的控件
     * @param attrs      换算的属性
     * @param baseWidth  基于宽度的属性 (Attrs.HEIGHT|Attrs.WIDTH|Attrs,Attrs.MARGIN)
     * @param baseHeight 基于高度的属性 (Attrs.HEIGHT|Attrs.WIDTH|Attrs,Attrs.MARGIN)
     */
    public static void auto(View view, int attrs, int baseWidth, int baseHeight) {
        AutoLayoutInfo autoLayoutInfo = AutoLayoutInfo.getAttrFromView(view, attrs, baseWidth, baseHeight);
        if (autoLayoutInfo != null)
            autoLayoutInfo.fillAttrs(view);
    }

    /**
     * 适配文字大小
     * @param view
     */
    public static void autoTextSize(View view) {
        auto(view, Attrs.TEXT_SIZE, 0, 0);
    }

    /**
     * 根据屏幕宽或者高适配文字大小
     * @param view
     * @param baseType
     */
    public static void autoTextSize(View view, BaseType baseType) {
        if (baseType == BaseType.BASE_HEIGHT) {
            auto(view, Attrs.TEXT_SIZE, 0, Attrs.TEXT_SIZE);
        } else {
            auto(view, Attrs.TEXT_SIZE, Attrs.TEXT_SIZE, 0);
        }
    }

    /**
     * 适配边距
     * @param view
     */
    public static void autoMargin(View view) {
        auto(view, Attrs.MARGIN, 0, 0);
    }

    /**
     * 根据屏幕宽或者高适配边距
     * @param view
     * @param baseType
     */
    public static void autoMargin(View view, BaseType baseType) {
        if (baseType == BaseType.BASE_HEIGHT) {
            auto(view, Attrs.MARGIN, 0, Attrs.MARGIN);
        } else {
            auto(view, Attrs.MARGIN, Attrs.MARGIN, 0);
        }
    }

    /**
     * 适配内边距
     * @param view
     */
    public static void autoPadding(View view) {
        auto(view, Attrs.PADDING, 0, 0);
    }

    /**
     * 根据屏幕宽或者高适配内边距
     * @param view
     * @param baseType
     */
    public static void autoPadding(View view, BaseType baseType) {
        if (baseType == BaseType.BASE_HEIGHT) {
            auto(view, Attrs.PADDING, 0, Attrs.PADDING);
        } else {
            auto(view, Attrs.PADDING, Attrs.PADDING, 0);
        }
    }

    /**
     * 适配控件本身的宽高
     *
     * @param view
     */
    public static void autoSize(View view) {
        auto(view, Attrs.WIDTH | Attrs.HEIGHT, 0, 0);
    }

    /**
     * 基于屏幕宽或者高适配控件本身宽高
     *
     * @param view
     * @param baseType
     */
    public static void autoSize(View view, BaseType baseType) {
        if (baseType == BaseType.BASE_HEIGHT) {
            auto(view, Attrs.WIDTH | Attrs.HEIGHT, 0, Attrs.WIDTH | Attrs.HEIGHT);
        } else {
            auto(view, Attrs.WIDTH | Attrs.HEIGHT, Attrs.WIDTH | Attrs.HEIGHT, 0);
        }
    }

    /**
     * 获取以屏幕宽度为base换算过后的px值(并且大于0向上取整,小于0向下取整)
     *
     * @param val 换算前px值
     * @return
     */
    public static int getPercentWidthSizeBigger(int val) {
        int screenWidth = AutoLayoutConifg.getInstance().getScreenWidth();
        int designWidth = AutoLayoutConifg.getInstance().getDesignWidth();

        int res = val * screenWidth;
        //如果不能整除，加一是为了把零点几个px给显示出来，至少是1个px，避免小数位被直接舍弃掉
        if (res % designWidth == 0) {
            return res / designWidth;
        } else if (res > 0) {
            return res / designWidth + 1;
        } else {
            return res / designWidth - 1;
        }
    }

    /**
     * 获取以屏幕高度为base换算过后的px值(并且大于0向上取整,小于0向下取整)
     *
     * @param val
     * @return
     */
    public static int getPercentHeightSizeBigger(int val) {
        int screenHeight = AutoLayoutConifg.getInstance().getScreenHeight();
        int designHeight = AutoLayoutConifg.getInstance().getDesignHeight();

        int res = val * screenHeight;
        //如果不能整除，加一是为了把零点几个px给显示出来，至少是1个px，避免小数位被直接舍弃掉
        if (res % designHeight == 0) {
            return res / designHeight;
        } else if (res > 0) {
            return res / designHeight + 1;
        } else {
            return res / designHeight - 1;
        }
    }

    /**
     * 获取1px宽度换算后宽度
     *
     * @return
     */
    public static float getPercentWidth1px() {
        int screenWidth = AutoLayoutConifg.getInstance().getScreenWidth();
        int designWidth = AutoLayoutConifg.getInstance().getDesignWidth();
        return 1.0f * screenWidth / designWidth;
    }

    /**
     * 获取1px高度换算后高度
     *
     * @return
     */
    public static float getPercentHeight1px() {
        int screenHeight = AutoLayoutConifg.getInstance().getScreenHeight();
        int designHeight = AutoLayoutConifg.getInstance().getDesignHeight();
        return 1.0f * screenHeight / designHeight;
    }

    /**
     * 获取某px值按照宽度换算后的大小
     *
     * @param val
     * @return
     */
    public static int getPercentWidthSize(int val) {
        int screenWidth = AutoLayoutConifg.getInstance().getScreenWidth();
        int designWidth = AutoLayoutConifg.getInstance().getDesignWidth();
        return (int) (val * 1.0f / designWidth * screenWidth);
    }

    /**
     * 获取某px值按照高度换算后的大小
     *
     * @param val
     * @return
     */
    public static int getPercentHeightSize(int val) {
        int screenHeight = AutoLayoutConifg.getInstance().getScreenHeight();
        int designHeight = AutoLayoutConifg.getInstance().getDesignHeight();

        return (int) (val * 1.0f / designHeight * screenHeight);
    }
}
