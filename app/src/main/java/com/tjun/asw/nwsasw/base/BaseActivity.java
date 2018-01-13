package com.tjun.asw.nwsasw.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;

import com.tjun.asw.nwsasw.app.MyApp;
import com.tjun.asw.nwsasw.base.ui.WaitProgressDialog;
import com.tjun.asw.nwsasw.manager.AppManager;
import com.tjun.asw.nwsasw.utils.ModeThemeUtils;
import com.tjun.asw.nwsasw.utils.SpUtils;
import com.tjun.asw.nwsasw.utils.StatusBarUtils;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by asw on 2018/1/11.
 */

public abstract class BaseActivity extends SupportActivity {
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
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        setTheme(ModeThemeUtils.THEME_ARR[SpUtils.getThemeIndex(this)][SpUtils.getNightModel(this) ? 1 : 0]);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        StatusBarUtils.steTransparent(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initData();
        initView(savedInstanceState);
        AppManager.getInstance().addActivity(this);
    }

    protected abstract void initView(Bundle savedInstanceState);

    private void initData() {
    }

    /**
     * 获取当前layouty的布局ID,用于设置当前布局
     * <p>
     * 交由子类实现
     *
     * @return layout Id
     */
    protected abstract int getLayoutId();

    public FragmentAnimator onCreateFragmentAnimator() {
        //fragment切换使用默认Vertical动画
        return new DefaultVerticalAnimator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishAllActivity();
    }
}
