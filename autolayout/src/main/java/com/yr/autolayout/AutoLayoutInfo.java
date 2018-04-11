package com.yr.autolayout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yr.autolayout.attr.Attrs;
import com.yr.autolayout.attr.AutoAttr;
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
import com.yr.autolayout.attr.PaddingBottomAttr;
import com.yr.autolayout.attr.PaddingLeftAttr;
import com.yr.autolayout.attr.PaddingRightAttr;
import com.yr.autolayout.attr.PaddingTopAttr;
import com.yr.autolayout.attr.TextSizeAttr;
import com.yr.autolayout.attr.WidthAttr;

import java.util.ArrayList;
import java.util.List;

/**
 * 控件autolayout布局相关信息
 * <p>
 * Created by yangrui on 2018/4/10.
 */
public class AutoLayoutInfo {

    private List<AutoAttr> autoAttrs = new ArrayList<>();

    /**
     * 将换算后的所有属性值应用于控件
     *
     * @param view
     */
    public void fillAttrs(View view) {
        for (AutoAttr autoAttr : autoAttrs) {
            autoAttr.apply(view);
        }
    }

    /**
     * 添加需要换算的属性
     * @param autoAttr
     */
    public void addAttr(AutoAttr autoAttr){
        autoAttrs.add(autoAttr);
    }

    /**
     * 生成autolayout布局相关信息，换算px
     *
     * @param view
     * @param attrs      需要换算的属性(Attrs.HEIGHT|Attrs.WIDTH|Attrs,Attrs.MARGIN)
     * @param baseWidth  基于宽度的属性(Attrs.HEIGHT|Attrs.WIDTH|Attrs,Attrs.MARGIN)
     * @param baseHeight 基于高度的属性(Attrs.HEIGHT|Attrs.WIDTH|Attrs,Attrs.MARGIN)
     * @return
     */
    public static AutoLayoutInfo getAttrFromView(View view, int attrs, int baseWidth, int baseHeight) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) return null;
        AutoLayoutInfo autoLayoutInfo = new AutoLayoutInfo();

        // 换算宽度
        if ((attrs & Attrs.WIDTH) != 0 && params.width > 0) {
            autoLayoutInfo.autoAttrs.add(new WidthAttr(params.width, baseWidth, baseHeight));
        }
        //换算高度
        if ((attrs & Attrs.HEIGHT) != 0 && params.height > 0) {
            autoLayoutInfo.autoAttrs.add(new HeightAttr(params.height, baseWidth, baseHeight));
        }

        //换算margin
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) params;
            if ((attrs & Attrs.MARGIN) != 0) {
                autoLayoutInfo.autoAttrs.add(new MarginTopAttr(marginLayoutParams.topMargin, baseWidth, baseHeight));
                autoLayoutInfo.autoAttrs.add(new MarginBottomAttr(marginLayoutParams.bottomMargin, baseWidth, baseHeight));
                autoLayoutInfo.autoAttrs.add(new MarginLeftAttr(marginLayoutParams.leftMargin, baseWidth, baseHeight));
                autoLayoutInfo.autoAttrs.add(new MarginRightAttr(marginLayoutParams.rightMargin, baseWidth, baseHeight));
            }
            if ((attrs & Attrs.MARGIN_LEFT) != 0) {
                autoLayoutInfo.autoAttrs.add(new MarginLeftAttr(marginLayoutParams.leftMargin, baseWidth, baseHeight));
            }
            if ((attrs & Attrs.MARGIN_TOP) != 0) {
                autoLayoutInfo.autoAttrs.add(new MarginTopAttr(marginLayoutParams.topMargin, baseWidth, baseHeight));
            }
            if ((attrs & Attrs.MARGIN_RIGHT) != 0) {
                autoLayoutInfo.autoAttrs.add(new MarginRightAttr(marginLayoutParams.rightMargin, baseWidth, baseHeight));
            }
            if ((attrs & Attrs.MARGIN_BOTTOM) != 0) {
                autoLayoutInfo.autoAttrs.add(new MarginBottomAttr(marginLayoutParams.bottomMargin, baseWidth, baseHeight));
            }
        }

        //换算padding
        if ((attrs & Attrs.PADDING) != 0) {
            autoLayoutInfo.autoAttrs.add(new PaddingLeftAttr(view.getPaddingLeft(), baseWidth, baseHeight));
            autoLayoutInfo.autoAttrs.add(new PaddingBottomAttr(view.getPaddingBottom(), baseWidth, baseHeight));
            autoLayoutInfo.autoAttrs.add(new PaddingRightAttr(view.getPaddingRight(), baseWidth, baseHeight));
            autoLayoutInfo.autoAttrs.add(new PaddingTopAttr(view.getPaddingTop(), baseWidth, baseHeight));
        }
        if ((attrs & Attrs.PADDING_LEFT) != 0) {
            autoLayoutInfo.autoAttrs.add(new PaddingLeftAttr(view.getPaddingLeft(), baseWidth, baseHeight));
        }
        if ((attrs & Attrs.PADDING_TOP) != 0) {
            autoLayoutInfo.autoAttrs.add(new PaddingTopAttr(view.getPaddingTop(), baseWidth, baseHeight));
        }
        if ((attrs & Attrs.PADDING_RIGHT) != 0) {
            autoLayoutInfo.autoAttrs.add(new PaddingRightAttr(view.getPaddingRight(), baseWidth, baseHeight));
        }
        if ((attrs & Attrs.PADDING_BOTTOM) != 0) {
            autoLayoutInfo.autoAttrs.add(new PaddingBottomAttr(view.getPaddingBottom(), baseWidth, baseHeight));
        }

        //换算minWidth ,maxWidth , minHeight , maxHeight
        if ((attrs & Attrs.MIN_WIDTH) != 0) {
            autoLayoutInfo.autoAttrs.add(new MinWidthAttr(view.getMinimumWidth(), baseWidth, baseHeight));
        }
        if ((attrs & Attrs.MAX_WIDTH) != 0) {
            autoLayoutInfo.autoAttrs.add(new MaxWidthAttr(MaxWidthAttr.getMaxWidth(view), baseWidth, baseHeight));
        }
        if ((attrs & Attrs.MIN_HEIGHT) != 0) {
            autoLayoutInfo.autoAttrs.add(new MinHeightAttr(view.getMinimumHeight(), baseWidth, baseHeight));
        }
        if ((attrs & Attrs.MAX_HEIGHT) != 0) {
            autoLayoutInfo.autoAttrs.add(new MaxHeightAttr(MaxHeightAttr.getMaxHeight(view), baseWidth, baseHeight));
        }
        //换算textsize
        if (view instanceof TextView) {
            if ((attrs & Attrs.TEXT_SIZE) != 0) {
                autoLayoutInfo.autoAttrs.add(new TextSizeAttr((int) ((TextView) view).getTextSize(), baseWidth, baseHeight));
            }
        }
        return autoLayoutInfo;
    }
}
