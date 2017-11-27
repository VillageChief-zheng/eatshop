package com.stateunion.eatshop.commons.engine;

import android.content.Context;
import android.content.SharedPreferences;

import com.stateunion.eatshop.application.ProjectApplication;
import com.stateunion.eatshop.retrofit.entiity.LoginResultEntity;
import com.stateunion.eatshop.retrofit.entiity.UserInfoEntity;

/**
 * Created by admini on 2017/11/6.
 */

public class AppSessionEngine {


    private static final String SP_KEY_LOGIN_INFO = "sp_key_login_info";
    private static final String SP_NAME_APP_SESSION = "sp_name_app_session";

    private static String getString(String SPKey) {
        SharedPreferences sp = getSP();
        String result = sp.getString(SPKey, null);
        return result;
    }

    private static void setString(String param, String SPKey) {
        SharedPreferences sp = getSP();
        sp.edit().putString(SPKey, param)
                .apply();
    }

    public static String getSession() {
        LoginResultEntity loginBody = getLoginInfo();
        if (loginBody == null) {
            return null;
        } else {
            return loginBody.getSessionId();
        }
    }

    private static SharedPreferences getSP() {
        return ProjectApplication.sApplication.getSharedPreferences(SP_NAME_APP_SESSION, Context.MODE_PRIVATE);
    }

    public static UserInfoEntity getUserInfo() {
        LoginResultEntity loginInfo = getLoginInfo();
        if (loginInfo == null) {
            return null;
        } else {
            return loginInfo.getSftUserMdl();
        }
    }

    public static LoginResultEntity getLoginInfo() {
        String loginInfo = getString(SP_KEY_LOGIN_INFO);
        if (loginInfo == null) {
            return null;
        } else {
            return GsonEngine.getDefaultGson().fromJson(loginInfo, LoginResultEntity.class);
        }
    }

    public static void setLoginInfo(LoginResultEntity loginInfo) {
        String result;
        if (loginInfo == null) {
            result = null;
        } else {
            result = GsonEngine.getDefaultGson().toJson(loginInfo);
        }
        setString(result, SP_KEY_LOGIN_INFO);
    }

    public static String getUseId() {
        UserInfoEntity userInfo = AppSessionEngine.getUserInfo();
        if (userInfo == null) {
            return null;
        }
        return userInfo.getUseId();
    }

    public static boolean isSignIn() {
        return getUserInfo()!=null&&getSession() != null;
    }

    public static void logout() {
        clear();
    }

    public static void clear() {
        getSP().edit().clear().apply();
    }

    public static int getNotReadMsg() {
        LoginResultEntity loginBody = getLoginInfo();
        if (loginBody == null) {
            return 0;
        } else {
            return loginBody.getNotReadMsg();
        }
    }

    public static String getFirstOfMonth() {
        LoginResultEntity loginBody = getLoginInfo();
        if (loginBody == null) {
            return null;
        } else {
            return loginBody.getFirstOfMonth();
        }
    }

    public static String getHasRepayOfMonth() {
        LoginResultEntity loginBody = getLoginInfo();
        if (loginBody == null) {
            return null;
        } else {
            return loginBody.getHasRepayOfMonth();
        }
    }


    public static void setNotReadMsg(int notReadMsg) {
        LoginResultEntity loginInfo = getLoginInfo();
        if (loginInfo != null) {
            loginInfo.setNotReadMsg(notReadMsg);
            setLoginInfo(loginInfo);
        }
    }

    public static void setUserInfo(UserInfoEntity userInfo) {
        LoginResultEntity loginInfo = getLoginInfo();
        if (loginInfo != null) {
            loginInfo.setSftUserMdl(userInfo);
            setLoginInfo(loginInfo);
        }
    }
}
