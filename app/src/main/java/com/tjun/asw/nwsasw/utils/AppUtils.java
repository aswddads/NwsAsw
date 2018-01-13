package com.tjun.asw.nwsasw.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelUuid;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.tjun.asw.nwsasw.app.MyApp;

import java.io.File;

/**
 * Created by asw on 2018/1/13.
 * application 工具类
 */


public class AppUtils {

    /**
     * 获取上下文对象
     * @return
     */
    public static Context getContext(){
        return MyApp.getContext();
    }

    /**
     * 拿到全局的Handler
     * @return
     */
    public static Handler getHandler(){
        return MyApp.getHandler();
    }

    /**
     *  拿到MainThreadId
     * @return
     */
    public static int getMainThreadId(){
        return MyApp.getMainThreadId();
    }

    /**
     * 获取版本名称
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context){
        String versionName = "";
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),0);
            versionName = info.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 版本号
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = -1;
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取IMEI码　
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 显示软键盘
     * @param editText
     */
     public static void openSoftInput(EditText editText){
         InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
         inputMethodManager.showSoftInput(editText, InputMethodManager.HIDE_NOT_ALWAYS);
     }

    /**
     * 隐藏软键盘
     * @param editText
     */
    public static void closeSoftInput(EditText editText){
         InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
         inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
     }

    /**
     * 获取sd卡路径
     * @return
     */
    public static File getSDPath(){
        File sdDir = null;
        boolean sdCartExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);//判断是否存在sd卡
        if (sdCartExist){
            sdDir = Environment.getExternalStorageDirectory();//获取目录
        }
        return sdDir;
    }

    /**
     * 安装新的apk
     * @param context
     * @param data
     */
    public static void promptInstall(Context context, Uri data){
        Intent promptInstall =  new Intent(Intent.ACTION_VIEW)
                .setDataAndType(data,"application/vnd.android.package-archive");
        // FLAG_ACTIVITY_NEW_TASK 可以保证安装成功时可以正常打开 app
        promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(promptInstall);
    }

    /**
     * 判断当前是否在主线程
     * @return
     */
    public static boolean isRunOnUIThread(){
        // 获取当前线程id, 如果当前线程id和主线程id相同, 那么当前就是主线程
        int currentThreadId = android.os.Process.myTid();
        if (currentThreadId == getMainThreadId()) {
            return true;
        }
        return false;
    }

    /**
     * 在主线程执行
     * @param runnable
     */
    public static void runOnUIThread(Runnable runnable){
        if (isRunOnUIThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }
}
