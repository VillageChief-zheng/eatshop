package com.stateunion.eatshop.retrofit;

import com.stateunion.eatshop.retrofit.callback.RequestCallback;

import retrofit2.Call;

/**
 * Created by admini on 2017/11/6.
 */

public class RequestCommand {

    private static void send(Call call, RequestCallback callback) {
        callback.onPreRequest(call);
        call.enqueue(callback);
    }
    public static void call(RequestCallback callback, Call call) {
        send(call, callback);
    }

    private static ServiceApi getApi() {
        return RetrofitBuilder.getServiceApiInstance();
    }
    //密码登录
    public static void passWordLogin(RequestCallback callback, String useLoginName,
                                     String useLoginPswd, String identify, String uuid) {
        Call call = getApi().passwordLogin(useLoginName, useLoginPswd, identify, uuid);
        send(call, callback);
    }
}
