package com.yr.autolayout.utils;

import android.util.TypedValue;

/**
 * 长度单位工具类
 *
 * Created by yangrui on 2018/3/31.
 */
public class DimenUtils {

    /**
     * 获取数据的单位
     * @param data
     * @return
     */
    private static int getComplexUnit(int data) {
        return TypedValue.COMPLEX_UNIT_MASK & (data >> TypedValue.COMPLEX_UNIT_SHIFT);
    }

    /**
     * 判断一个属性值单位是否是px
     * @param val
     * @return
     */
    public static boolean isPxVal(TypedValue val) {
        if (val != null && val.type == TypedValue.TYPE_DIMENSION &&
                getComplexUnit(val.data) == TypedValue.COMPLEX_UNIT_PX) {
            return true;
        }
        return false;
    }
}
