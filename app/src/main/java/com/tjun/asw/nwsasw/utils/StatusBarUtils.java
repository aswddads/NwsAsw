package com.tjun.asw.nwsasw.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toolbar;

import java.lang.reflect.Field;

/**
 * Created by asw on 2018/1/13.
 * StatusBar 工具类
 */

public class StatusBarUtils {
    /**
     * 设置状态栏颜色
     * @param activity
     * @param color
     */
    public static void setColor(Activity activity, @ColorInt int color){
        setBarColor(activity,color);
    }

    /**
     * 设置状态栏背景色
     * 4.4以下不做处理,4.4使用默认沉浸式状态栏
     * @param activity
     * @param color
     */
    public static void setBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏(4.4-5.0透明，5.0以上半透明)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0以上
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//清除flag，使得5.0以上也全透明
                //让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);
            }
        }
    }

    /**
     * 设置状态栏全透明
     * @param activity
     */
    public static void steTransparent(Activity activity){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        setColor(activity, Color.TRANSPARENT);
    }

    /**
     * 修正 Toolbar 的位置
     * 在 Android 4.4 版本下无法显示内容在 StatusBar 下，所以无需修正 Toolbar 的位置
     *
     * @param toolbar
     */
    public static void fixToolbar(Toolbar toolbar,Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusHeight = getStatusBarHeight(activity);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
            layoutParams.setMargins(0,statusHeight,0,0);
        }
    }

    /**
     * 获取系统状态栏高度
     * @param context
     * @return
     */
    private static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object o = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            o = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(o).toString());
            statusBarHeight = context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
