package com.yr.autolayout.attr;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yangrui on 2018/4/10.
 */

public class TextSizeAttr extends AutoAttr {

    public TextSizeAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.TEXT_SIZE;
    }

    @Override
    protected BaseType defaultBaseType() {
        return BaseType.BASE_WIDTH;
    }

    @Override
    protected void execute(View view, int val) {
        if (view instanceof TextView) {
            //设置文本不包含默认的顶部和底部的空白
            ((TextView) view).setIncludeFontPadding(false);
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, val);
        }
    }
}
