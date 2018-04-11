package com.yr.autolayout.attr;

import android.view.View;

/**
 * Created by yangrui on 2018/4/10.
 */
public class PaddingTopAttr extends AutoAttr {

    public PaddingTopAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.PADDING_TOP;
    }

    @Override
    protected BaseType defaultBaseType() {
        return BaseType.BASE_HEIGHT;
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int t = val;
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
    }
}
