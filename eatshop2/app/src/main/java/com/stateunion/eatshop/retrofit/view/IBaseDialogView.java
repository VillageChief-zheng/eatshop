package com.stateunion.eatshop.retrofit.view;

import android.app.Dialog;
import android.support.annotation.StyleRes;

import com.stateunion.eatshop.view.baseactivity.BaseActivity;

/**
 * Created by admini on 2017/11/6.
 */

public interface IBaseDialogView {

    Dialog createDialog(@StyleRes int themeResId);

    void showError(String message);

    BaseActivity getBaseActivity();

    boolean isAlive();
}
