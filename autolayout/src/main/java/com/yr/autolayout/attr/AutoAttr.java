package com.yr.autolayout.attr;

import android.view.View;

import com.yr.autolayout.utils.AutoUtils;

import static com.yr.autolayout.attr.BaseType.BASE_HEIGHT;
import static com.yr.autolayout.attr.BaseType.BASE_WIDTH;

/**
 * autolayout的支持px换算的属性父类
 * <p>
 * Created by yangrui on 2018/4/9.
 */
public abstract class AutoAttr {

    /**
     * 属性px值
     */
    private int pxVal;

    /**
     * 基于宽度的属性集合(二进制)
     */
    private int baseWidth;

    /**
     * 基于高度的属性集合(二进制)
     */
    private int baseHeight;

    public AutoAttr(int pxVal, int baseWidth, int baseHeight) {
        this.pxVal = pxVal;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }

    /**
     * 获取px的值是根据屏幕高还是宽来进行换算
     * @return
     */
    protected BaseType getBaseType() {
        if (contains(baseHeight, attrVal())) {
            return BASE_HEIGHT;
        } else if (contains(baseWidth, attrVal())) {
            return BASE_WIDTH;
        } else {
            return defaultBaseType();
        }
    }

    /**
     * 判断控件的basewidth或baseheight的值是否包含了该属性
     *
     * @param baseVal 从控件传入的basewidth或baseheight属性的值
     * @param flag    属性自身的name值
     * @return
     */
    protected boolean contains(int baseVal, int flag) {
        return (baseVal & flag) != 0;
    }

    /**
     * 在控件上应用对应的属性
     * @param view
     */
    public void apply(View view) {
        int val;
        BaseType baseType=getBaseType();
        if (baseType==BASE_HEIGHT) {
            val = AutoUtils.getPercentHeightSizeBigger(pxVal);
        } else {
            val = AutoUtils.getPercentWidthSizeBigger(pxVal);
        }
        execute(view, val);
    }

    /**
     * 获取属性的name值(注意这里不是属性值，类似于控件的id)
     *
     * @return
     */
    protected abstract int attrVal();

    /**
     * 获取属性值的px是根据屏幕高还是宽进行换算的默认值
     *
     * @return
     */
    protected abstract BaseType defaultBaseType();

    /**
     * 将换算后的值赋予对应的属性
     * @param view
     * @param val
     */
    protected abstract void execute(View view, int val);
}