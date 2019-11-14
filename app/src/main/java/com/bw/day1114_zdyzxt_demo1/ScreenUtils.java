package com.bw.day1114_zdyzxt_demo1;

import android.content.Context;

/**
 * Copyright (C)
 * <p>
 * FileName: ScreenUtils
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/14 13:51
 * <p>
 * Description:
 */
public class ScreenUtils {
    //把dp转换成px
    public static int getDptoPx(Context context, int dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        int pxValue = (int) (dpValue * density + 0.5);
        return pxValue;
    }

    //把px转换成dp
    public static int getPxtoDp(Context context, int pxValue) {
        float density = context.getResources().getDisplayMetrics().density;
        int dpValue = (int) (pxValue / density + 0.5);
        return dpValue;
    }
}
