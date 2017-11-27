package com.stateunion.eatshop.util;

import android.view.View;

import java.util.Calendar;

/**
 * Created by zhangguozheng on 2017/8/28.
 */

public abstract class OnSafetyClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 400;
    private long lastClickTime = 0;

    @Override
    public final void onClick(View v) {

        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > getTime()) {
            lastClickTime = currentTime;
            onSafetyClick(v);
        }
    }

    public abstract void onSafetyClick(View v);

    public int getTime() {
        return MIN_CLICK_DELAY_TIME;
    }
}
