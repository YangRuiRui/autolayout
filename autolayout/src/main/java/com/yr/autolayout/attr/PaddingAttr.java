package com.yr.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangrui on 2018/4/10.
 */
public class PaddingAttr extends AutoAttr {

    public PaddingAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.PADDING;
    }

    @Override
    protected BaseType defaultBaseType() {
        return BaseType.BASE_WIDTH;
    }

    @Override
    protected void execute(View view, int val) {
        view.setPadding(val,val,val,val);
    }
}
