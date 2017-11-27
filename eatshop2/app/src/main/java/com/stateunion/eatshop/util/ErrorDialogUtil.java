package com.stateunion.eatshop.util;

import android.content.Context;
import android.view.Gravity;

import com.stateunion.eatshop.R;

/**
 * Created by admini on 2017/11/6.
 */

public class ErrorDialogUtil {
    /**
     * 版本更新
     */
    public static void showVersionUpdateDialog(Context context, String title, String content,
                                               SysDiaLog.EtdDoubleListener listener) {
        SysDiaLog dialog = new SysDiaLog(context);

        dialog.getContentView().setGravity(Gravity.LEFT);
        String desc = content.replace("；", ""); // 中文分号作为分隔符
        dialog.buildDouble(desc,
                "发现新版本" + title,
                context.getResources().getString(R.string.update_no),
                context.getResources().getString(R.string.update_now),
                listener);
        dialog.show();
    }

    /**
     * 版本更新(强制更新)
     *
     * @param context
     * @param title
     * @param content
     * @param listener
     */
    public static void showForceUpdateDialog(Context context,
                                             String title,
                                             String content,
                                             SysDiaLog.EtdSingleListener listener) {
        SysDiaLog dialog = new SysDiaLog(context);

        dialog.getContentView().setGravity(Gravity.LEFT);
        String desc = content.replace("；", ""); // 中文分号作为分隔符
        dialog.buildSingle(desc,
                "发现新版本" + title,
                context.getResources().getString(R.string.update_right_now),
                listener);
        dialog.show();
    }


    /**
     * 错误提示dialog（单个按钮）
     *
     * @param context
     * @param errString
     */
    public static void showErrorDialog(Context context, String errString) {
        SysDiaLog dialog = new SysDiaLog(context);
        dialog.buildSingle(errString, new SysDiaLog.EmptyETongDaiDialogListener());
        dialog.show();
    }

    public static void showErrorDialog(Context context, String errString, SysDiaLog.EtdSingleListener listeners) {
        SysDiaLog dialog = new SysDiaLog(context);
        dialog.buildSingle(errString, listeners);
        dialog.show();
    }

    /**
     * 忘记手势密码和切换账号登录时的弹出框
     *
     * @param context
     * @param errString
     * @param buttonStringLeft
     * @param buttonStringRight
     * @param listener
     */
    public static void showAlertDialog2Btn(Context context, String errString, String buttonStringLeft, String buttonStringRight,
                                           SysDiaLog.EtdDoubleListener listener) {
        SysDiaLog dialog = new SysDiaLog(context);
        dialog.buildDouble(errString, buttonStringLeft, buttonStringRight, listener);
        dialog.show();
    }

}
