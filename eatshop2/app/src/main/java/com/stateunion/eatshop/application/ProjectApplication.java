package com.stateunion.eatshop.application;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;

import com.growingio.android.sdk.collection.Configuration;
import com.growingio.android.sdk.collection.GrowingIO;
import com.stateunion.eatshop.commons.Constants;
import com.stateunion.eatshop.retrofit.BuildConfig;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by admini on 2017/11/6.
 */

public class ProjectApplication extends MultiDexApplication {
    public static ProjectApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayMetrics metric = getApplicationContext().getResources()
                .getDisplayMetrics();
        Constants.SCREEN_WIDTH_PX = metric.widthPixels;
        Constants.SCREEN_HEIGHT_PX = metric.heightPixels;
        // 获取系统版本
        Constants.SYSTEM_VERSION = android.os.Build.VERSION.SDK_INT;
        initBugly();
        initGroWingIO();
//        initTencentX5();
    }

    /**
     * GrowingIo注入
     */
    private void initGroWingIO() {
        try {
            ApplicationInfo appInfo = getApplicationContext().getPackageManager().
                    getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            GrowingIO.startWithConfiguration(this, new Configuration()
                    .useID()
                    .trackAllFragments()
                    .setChannel(appInfo.metaData.getString("FRIEND_ID")));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * X5浏览器初始化
     */
//    private void initTencentX5() {
//        QbSdk.initX5Environment(this, null);
//    }

    /**
     * 热修复
     */
    private void initBugly() {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppChannel(BuildConfig.FLAVOR);
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BUGLY_APP_ID, false, strategy);
    }
}
