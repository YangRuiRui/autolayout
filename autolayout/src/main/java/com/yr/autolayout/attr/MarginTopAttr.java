package com.yr.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangrui on 2018/4/10.
 */
public class MarginTopAttr extends AutoAttr {

    public MarginTopAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.MARGIN_TOP;
    }

    @Override
    protected BaseType defaultBaseType() {
        return BaseType.BASE_HEIGHT;
    }

    @Override
    protected void execute(View view, int val) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.topMargin = val;
    }
}
