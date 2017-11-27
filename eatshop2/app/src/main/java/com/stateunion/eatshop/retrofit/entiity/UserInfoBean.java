package com.stateunion.eatshop.retrofit.entiity;


import com.stateunion.eatshop.retrofit.bean.BaseBean;

/**
 * Created by zhangguozheng on 2017/8/25.
 */

public class UserInfoBean extends BaseBean {
         private LoginResultEntity body;
     public LoginResultEntity getBody() {
        return body;
    }

    public void setBody(LoginResultEntity body) {
        this.body = body;
    }
}
