package com.stateunion.eatshop.retrofit.callback;

import android.util.Log;

import com.stateunion.eatshop.retrofit.bean.IBaseBean;
import com.tencent.bugly.crashreport.BuglyLog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by zhangguozheng on 2017/8/24.
 */

public abstract class RequestCallback <E extends IBaseBean> implements Callback<E> {
    // 会话超时
    public static final String ERROR_CODE_SESSION_TIMEOUT = "800000";

    // 用户被挤掉
    protected static final String ERROR_CODE_LOGIN_CROWD = "800001";
    // 账户冻结
    protected static final String ERROR_CODE_ACCOUNT_FREEZE = "103";
    @Override
    public void onResponse(Call<E> call, Response<E> response) {
         E e = response.body();

        onResponse(e, call);
    }

    @Override
    public void onFailure(Call<E> call, Throwable t) {
        BuglyLog.e("onResponse", call.toString(),t);
        logRequest("onFailure" + t.toString());
        onFailure(call);
    }

    public void onResponse(E e, Call<E> call) {
          if (e == null) {
            onFailure(call);
            return;
        }
        if (e.isSuccess()) {
            onResponseSuccess(e, call);
        } else {
            onResponseFailure(e, call);

        }
    }


    /**
     * 错误信息的时候调用
     *
     * @param e
     * @param call
     */
    protected abstract void onResponseFailureMessage(E e, Call<E> call);

    /**
     * 异地登录的时候调用
     *
     * @param e
     * @param call
     */
    protected abstract void onResponseFailureForceSignOut(E e, Call<E> call);

    protected abstract  void onResponseFailureFrozen(E e, Call<E> call);

    /**
     * 返回失败信息的时候调用
     *
     * @param e
     * @param call
     */
    protected void onResponseFailure(E e, Call<E> call) {
        logRequest("onResponseFailure" + e);
        if (ERROR_CODE_SESSION_TIMEOUT.equals(e.getCode())) {
            onResponseFailureForceSignOut(e, call);
        } else if (ERROR_CODE_LOGIN_CROWD.equals(e.getCode())) {
            onResponseFailureForceSignOut(e, call);
        } else if (ERROR_CODE_ACCOUNT_FREEZE.equals(e.getCode())) {
            onResponseFailureFrozen(e, call);
        }else {
            onResponseFailureMessage(e, call);
        }
    }

    /**
     * 返回成功信息的时候调用
     *
     * @param e
     * @param call
     */
    protected abstract void onResponseSuccess(E e, Call<E> call);

    /**
     * 网络出错的时候调用
     *
     * @param call
     */
    public abstract void    onFailure(Call<E> call);

    public abstract void onPreRequest(Call<E> call);

    private void logRequest(String message) {
        Log.w("logRequest", message);
    }
}
