package com.yr.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangrui on 2018/4/10.
 */

public class HeightAttr extends AutoAttr {

    public HeightAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.HEIGHT;
    }

    @Override
    protected BaseType defaultBaseType() {
        return BaseType.BASE_HEIGHT;
    }

    @Override
    protected void execute(View view, int val) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = val;
    }

}
