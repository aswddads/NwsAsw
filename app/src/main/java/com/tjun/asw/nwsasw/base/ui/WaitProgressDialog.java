package com.tjun.asw.nwsasw.base.ui;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by asw on 2018/1/11.
 */

public class WaitProgressDialog extends ProgressDialog {
    public WaitProgressDialog(Context context, int theme) {
        super(context, theme);
        setCanceledOnTouchOutside(false);
    }

    public WaitProgressDialog(Context context) {
        super(context);
    }
}
