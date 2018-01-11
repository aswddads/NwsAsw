package com.tjun.asw.nwsasw.app;
import android.content.Context;

import com.mob.MobApplication;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tjun.asw.nwsasw.BuildConfig;

import android.os.Handler;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


/**
 * Created by asw on 2018/1/11.
 */

public class MyApp extends MobApplication{
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    private static final String ASW_LOGGER = "ASW_LOGGER";
    private static Context mContext;
    private static Handler mHandler;
    private static int mainThreadId;
    private static MyApp mApp;

    public static synchronized MyApp getInstance(){
        return mApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mHandler = new Handler();
        mainThreadId = Process.myTid();

        Logger.init(ASW_LOGGER).logLevel(BuildConfig.IS_SHOW_LOG ? LogLevel.FULL : LogLevel.NONE);
        
        getScreenSize();
    }

    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);

        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static Context getContext(){
        return mContext;
    }

    public static Handler getHandler(){
        return mHandler;
    }

    public static int getMainThreadId(){
        return mainThreadId;
    }
}
