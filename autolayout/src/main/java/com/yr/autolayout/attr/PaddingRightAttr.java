package com.yr.autolayout.attr;

import android.view.View;

/**
 * Created by yangrui on 2018/4/10.
 */
public class PaddingRightAttr extends AutoAttr {

    public PaddingRightAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.PADDING_RIGHT;
    }

    @Override
    protected BaseType defaultBaseType() {
        return BaseType.BASE_WIDTH;
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = val;
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
    }
}
