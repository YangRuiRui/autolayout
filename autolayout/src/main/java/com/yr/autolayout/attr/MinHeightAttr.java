package com.yr.autolayout.attr;

import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by yangrui on 2018/4/10.
 */
public class MinHeightAttr extends AutoAttr {

    public MinHeightAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.MIN_HEIGHT;
    }

    @Override
    protected BaseType defaultBaseType() {
        return BaseType.BASE_HEIGHT;
    }

    @Override
    protected void execute(View view, int val) {
        view.setMinimumHeight(val);
    }
}
