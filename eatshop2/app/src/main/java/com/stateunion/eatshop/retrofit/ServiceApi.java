package com.stateunion.eatshop.retrofit;



import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhangguozheng on 2017/8/23.
 */

public interface ServiceApi {
    /**
     * 密码登录
     *
     * @param useLoginName
     * @param useLoginPswd
     * @param identify
     * @param uuid
     * @return
     */
    /**
     *
     */
    @POST("service/user/login")
    @FormUrlEncoded
    Call<UserInfoBean> passwordLogin(@Field("useLoginName") String useLoginName,
                                     @Field("useLoginPswd") String useLoginPswd,
                                     @Field("identify") String identify,
                                     @Field("uuid") String uuid
    );
}
