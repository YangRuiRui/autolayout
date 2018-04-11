package com.yr.autolayout.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.yr.autolayout.AutoLayoutInfo;
import com.yr.autolayout.R;
import com.yr.autolayout.attr.HeightAttr;
import com.yr.autolayout.attr.MarginAttr;
import com.yr.autolayout.attr.MarginBottomAttr;
import com.yr.autolayout.attr.MarginLeftAttr;
import com.yr.autolayout.attr.MarginRightAttr;
import com.yr.autolayout.attr.MarginTopAttr;
import com.yr.autolayout.attr.MaxHeightAttr;
import com.yr.autolayout.attr.MaxWidthAttr;
import com.yr.autolayout.attr.MinHeightAttr;
import com.yr.autolayout.attr.MinWidthAttr;
import com.yr.autolayout.attr.PaddingAttr;
import com.yr.autolayout.attr.PaddingBottomAttr;
import com.yr.autolayout.attr.PaddingLeftAttr;
import com.yr.autolayout.attr.PaddingRightAttr;
import com.yr.autolayout.attr.PaddingTopAttr;
import com.yr.autolayout.attr.TextSizeAttr;
import com.yr.autolayout.attr.WidthAttr;
import com.yr.autolayout.config.AutoLayoutConifg;

/**
 * Created by yangrui on 2018/4/11.
 */
public class AutoLayoutHelper {

    private final ViewGroup mHost;

    private static final int[] LL = new int[]{
            android.R.attr.textSize,
            android.R.attr.padding,//
            android.R.attr.paddingLeft,//
            android.R.attr.paddingTop,//
            android.R.attr.paddingRight,//
            android.R.attr.paddingBottom,//
            android.R.attr.layout_width,//
            android.R.attr.layout_height,//
            android.R.attr.layout_margin,//
            android.R.attr.layout_marginLeft,//
            android.R.attr.layout_marginTop,//
            android.R.attr.layout_marginRight,//
            android.R.attr.layout_marginBottom,//
            android.R.attr.maxWidth,//
            android.R.attr.maxHeight,//
            android.R.attr.minWidth,//
            android.R.attr.minHeight//16843072
    };

    private static final int INDEX_TEXT_SIZE = 0;
    private static final int INDEX_PADDING = 1;
    private static final int INDEX_PADDING_LEFT = 2;
    private static final int INDEX_PADDING_TOP = 3;
    private static final int INDEX_PADDING_RIGHT = 4;
    private static final int INDEX_PADDING_BOTTOM = 5;
    private static final int INDEX_WIDTH = 6;
    private static final int INDEX_HEIGHT = 7;
    private static final int INDEX_MARGIN = 8;
    private static final int INDEX_MARGIN_LEFT = 9;
    private static final int INDEX_MARGIN_TOP = 10;
    private static final int INDEX_MARGIN_RIGHT = 11;
    private static final int INDEX_MARGIN_BOTTOM = 12;
    private static final int INDEX_MAX_WIDTH = 13;
    private static final int INDEX_MAX_HEIGHT = 14;
    private static final int INDEX_MIN_WIDTH = 15;
    private static final int INDEX_MIN_HEIGHT = 16;


    /**
     * autolayout配置相关信息
     */
    private static AutoLayoutConifg mAutoLayoutConifg;

    public AutoLayoutHelper(ViewGroup host) {
        mHost = host;
        if (mAutoLayoutConifg == null) {
            mAutoLayoutConifg = AutoLayoutConifg.getInstance();
            mAutoLayoutConifg.init(host.getContext());
        }
    }

    /**
     * 换算所有子元素
     */
    public void adjustChildren() {
        AutoLayoutConifg.getInstance().checkParams();
        for (int i = 0, n = mHost.getChildCount(); i < n; i++) {
            View view = mHost.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();

            if (params instanceof AutoLayoutParams) {
                AutoLayoutInfo info = ((AutoLayoutParams) params).getAutoLayoutInfo();
                if (info != null) {
                    info.fillAttrs(view);
                }
            }
        }

    }

    /**
     * 解析属性值,并且换算后存储在AutoLayoutInfo对象中
     *
     * @param context
     * @param attrs
     * @return
     */
    public static AutoLayoutInfo getAutoLayoutInfo(Context context,
                                                   AttributeSet attrs) {
        AutoLayoutInfo info = new AutoLayoutInfo();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoLayout);
        int baseWidth = a.getInt(R.styleable.AutoLayout_auto_basewidth, 0);
        int baseHeight = a.getInt(R.styleable.AutoLayout_auto_baseheight, 0);
        a.recycle();
        TypedArray array = context.obtainStyledAttributes(attrs, LL);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            //获取属性在传入的attrs对应的下标
            int index = array.getIndex(i);
            //判断属性值是否是px的
            //array.peekValue(index)获取带单位的属性值
            if (!DimenUtils.isPxVal(array.peekValue(index)))
                continue;

            int pxVal = 0;
            try {
                //获取px属性值
                pxVal = array.getDimensionPixelOffset(index, 0);
            } catch (Exception ignore) {
                //属性值不是长度单位
                continue;
            }
            switch (index) {
                case INDEX_TEXT_SIZE:
                    info.addAttr(new TextSizeAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING:
                    info.addAttr(new PaddingAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_LEFT:
                    info.addAttr(new PaddingLeftAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_TOP:
                    info.addAttr(new PaddingTopAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_RIGHT:
                    info.addAttr(new PaddingRightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_BOTTOM:
                    info.addAttr(new PaddingBottomAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_WIDTH:
                    info.addAttr(new WidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_HEIGHT:
                    info.addAttr(new HeightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN:
                    info.addAttr(new MarginAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_LEFT:
                    info.addAttr(new MarginLeftAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_TOP:
                    info.addAttr(new MarginTopAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_RIGHT:
                    info.addAttr(new MarginRightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_BOTTOM:
                    info.addAttr(new MarginBottomAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MAX_WIDTH:
                    info.addAttr(new MaxWidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MAX_HEIGHT:
                    info.addAttr(new MaxHeightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MIN_WIDTH:
                    info.addAttr(new MinWidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MIN_HEIGHT:
                    info.addAttr(new MinHeightAttr(pxVal, baseWidth, baseHeight));
                    break;
            }
        }
        array.recycle();
        return info;
    }

    public interface AutoLayoutParams {
        AutoLayoutInfo getAutoLayoutInfo();
    }

}
