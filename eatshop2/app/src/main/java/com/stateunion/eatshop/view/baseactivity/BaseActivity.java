package com.stateunion.eatshop.view.baseactivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.stateunion.eatshop.retrofit.view.IBaseDialogView;
import com.stateunion.eatshop.util.ActivityTaskManager;
import com.stateunion.eatshop.util.ErrorDialogUtil;
import com.stateunion.eatshop.util.SysDiaLog;
import com.stateunion.eatshop.widget.SliderFrame;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admini on 2017/11/6.
 */

public class BaseActivity extends AppCompatActivity implements IBaseDialogView {
    protected FragmentManager mFragmentManager;
    protected FragmentTransaction mFragmentTransaction;
    private SliderFrame frame;
    private boolean isAlive = false;

    // <-------------生命周期开始------------->
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isAlive = true;
        ActivityTaskManager.getInstance().add(this);
        mFragmentManager = getSupportFragmentManager();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frame = new SliderFrame(this);
        setContentView(frame, params);
    }
    @Override
    protected void onStart() {
        logLifeCycle("onStart");
        super.onStart();
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        logLifeCycle("onPostCreate");
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        logLifeCycle("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        logLifeCycle("onPause");
        super.onPause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        logLifeCycle("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onStop() {
        logLifeCycle("onStop");
        super.onStop();
    }
    /**
     * 清理activity 和dialog
     */
    @Override
    protected void onDestroy() {
        isAlive = true;
        ActivityTaskManager.getInstance().remove(this);
        for (WeakReference<Dialog> weakReference : dialogManager) {
            if (weakReference.get() != null) {
                weakReference.get().dismiss();
            }
        }
        dialogManager.clear();
        dialogManager = null;
        super.onDestroy();
    }
// <-------------生命周期结束------------->

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        logLifeCycle("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    List<WeakReference<Dialog>> dialogManager = new ArrayList<>();

    public Dialog createDialog(@StyleRes int themeResId) {
        Dialog dialog = new Dialog(this, themeResId);
        dialogManager.add(new WeakReference<>(dialog));
        return dialog;
    }

    /**
     * @param message
     * 重写 IBaseDialogView方法
     */
    @Override
    public void showError(String message) {
        showError(message, new SysDiaLog.EmptyETongDaiDialogListener());

    }
    public void showError(String errStr, SysDiaLog.EtdSingleListener listeners) {
        //做activity级的提示框
        ErrorDialogUtil.showErrorDialog(this, errStr, listeners);
    }
    @Override
    public BaseActivity getBaseActivity() {
        return this;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * @param method
     * 保存生命周期状态
     */
    private void logLifeCycle(String method) {
        StringBuilder sb = new StringBuilder(method);
        for (; sb.length() < 40; sb.append("-")) ;
        Log.e("life-cycle-activity", sb.toString() + toString());
    }


}
