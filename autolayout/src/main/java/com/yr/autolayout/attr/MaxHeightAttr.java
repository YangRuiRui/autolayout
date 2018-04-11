package com.yr.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Method;

/**
 * Created by yangrui on 2018/4/10.
 */
public class MaxHeightAttr extends AutoAttr {

    public MaxHeightAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.MAX_HEIGHT;
    }

    @Override
    protected BaseType defaultBaseType() {
        return BaseType.BASE_HEIGHT;
    }

    @Override
    protected void execute(View view, int val) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("setMaxHeight", int.class);
            setMaxWidthMethod.invoke(view, val);
        } catch (Exception ignore) {
        }
    }

    /**
     * 获取maxheight属性
     * @param view
     * @return
     */
    public static int getMaxHeight(View view) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("getMaxHeight");
            return (int) setMaxWidthMethod.invoke(view);
        } catch (Exception ignore) {
        }
        return 0;
    }
}
