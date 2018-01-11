package com.tjun.asw.nwsasw.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;

import com.tjun.asw.nwsasw.app.MyApp;
import com.tjun.asw.nwsasw.base.ui.WaitProgressDialog;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by asw on 2018/1/11.
 */

public class BaseActivity extends SupportActivity {
    protected MyApp mApp;
    protected Context mContext;
    protected WaitProgressDialog mWaitProgressDialog;
    protected boolean isTransAnim;

    static {
        //5.0以下兼容vector
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
