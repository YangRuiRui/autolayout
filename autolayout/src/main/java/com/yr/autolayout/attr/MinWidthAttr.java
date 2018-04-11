package com.yr.autolayout.attr;

import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by yangrui on 2018/4/10.
 */
public class MinWidthAttr extends AutoAttr {

    public MinWidthAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.MIN_WIDTH;
    }

    @Override
    protected BaseType defaultBaseType() {
        return BaseType.BASE_WIDTH;
    }

    @Override
    protected void execute(View view, int val) {
        view.setMinimumWidth(val);
    }


}
