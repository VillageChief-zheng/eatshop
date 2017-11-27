package com.stateunion.eatshop.retrofit.bean;

/**
 * Created by admini on 2017/11/6.
 */

public class BaseBean implements IBaseBean {


    /**
     * time : 1487920329011
     * success : true
     * code :
     * info :
     */
    private String time;
    private boolean success;
    private String code;
    private String info;
    private String requestId;
    private String bizRequestId;

    public String getBizRequestId() {
        return bizRequestId;
    }

    public void setBizRequestId(String bizRequestId) {
        this.bizRequestId = bizRequestId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
