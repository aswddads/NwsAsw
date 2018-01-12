package com.tjun.asw.nwsasw.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by asw on 2018/1/12.
 * 管理Activity 栈
 */

public class AppManager {

    private static Stack<Activity> mActivityStack;

    private AppManager(){
    }

    private static class AppManagerHolder{
        private static final AppManager INSTANCE = new AppManager();
    }

    public static final AppManager getInstance(){
        return AppManagerHolder.INSTANCE;
    }

    public void addActivity(Activity activity){
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }

    public Activity getCurrentActivity(){
        return mActivityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = mActivityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }
}
