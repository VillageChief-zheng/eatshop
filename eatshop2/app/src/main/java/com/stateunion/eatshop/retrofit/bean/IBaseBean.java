package com.stateunion.eatshop.retrofit.bean;

/**
 * Created by admini on 2017/11/6.
 */

public interface IBaseBean {
     String getBizRequestId();
    String getTime();
    boolean isSuccess();
    String getCode();
    String getInfo();
    String getRequestId();
}
