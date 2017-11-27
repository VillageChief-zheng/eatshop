package com.stateunion.eatshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;

import retrofit2.Call;

public class MainActivity extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirm();
    }
     private void confirm(){
        RequestCommand.passWordLogin(new LoginCallBack(this),"aaa","aaaa","a","123445");
     }

    private  static  class  LoginCallBack extends DialogCallback<UserInfoBean,MainActivity> {

        public LoginCallBack(MainActivity requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(UserInfoBean userInfoBean, Call<UserInfoBean> call) {
            super.onResponseSuccess(userInfoBean, call);
            Log.e("tag",userInfoBean.getCode());
         }
    }
}
