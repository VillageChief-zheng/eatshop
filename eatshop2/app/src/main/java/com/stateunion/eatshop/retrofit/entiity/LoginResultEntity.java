package com.stateunion.eatshop.retrofit.entiity;

/**
 * Created by zhangguozheng on 2017/8/23.
 */

public class LoginResultEntity {


    private String sessionId;
    private UserInfoEntity sftUserMdl;
    private int notReadMsg;
    /**
     * 是否为当月首次登录
     */
    private String firstOfMonth;
    /**
     * 当月是否有还款
     */
    private String hasRepayOfMonth;

    private boolean firstLogin;

    public int getNotReadMsg() {
        return notReadMsg;
    }


    public void setNotReadMsg(int notReadMsg) {
        this.notReadMsg = notReadMsg;
    }

    public UserInfoEntity getSftUserMdl() {
        return sftUserMdl;
    }


    public void setSftUserMdl(UserInfoEntity sftUserMdl) {
        this.sftUserMdl = sftUserMdl;
    }


    public String getSessionId() {
        return sessionId;
    }


    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public String getFirstOfMonth() {
        return firstOfMonth;
    }


    public void setFirstOfMonth(String firstOfMonth) {
        this.firstOfMonth = firstOfMonth;
    }


    public String getHasRepayOfMonth() {
        return hasRepayOfMonth;
    }


    public void setHasRepayOfMonth(String hasRepayOfMonth) {
        this.hasRepayOfMonth = hasRepayOfMonth;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}
