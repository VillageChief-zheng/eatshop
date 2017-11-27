package com.stateunion.eatshop.retrofit;



import android.util.Log;


import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.stateunion.eatshop.commons.Config;
import com.stateunion.eatshop.commons.engine.GsonEngine;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangguozheng on 2017/8/23.
 */

public class RetrofitBuilder {
    private static ServiceApi ourInstance = createApi();
     public static ServiceApi getServiceApiInstance() {
        return ourInstance;
    }
    private  RetrofitBuilder(){}
    private static ServiceApi createApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Config.HTTPS_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonEngine.getResponseGson()))
                .client(createOkHttpClient())
                .build();
        return retrofit.create(ServiceApi.class);
    }
        private static OkHttpClient createOkHttpClient() {
            return new OkHttpClient.Builder()
                    .addInterceptor(new ParamInterceptor())
                    .addInterceptor(getLogInterceptor())
                    .hostnameVerifier(new NotHostnameVerifier())//不验证用户名
                    .sslSocketFactory(createSSLSocket())
                    .cookieJar(new KeepCookieJar())//保持cookie
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
        public static Interceptor getLogInterceptor() {
            return new LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Log.WARN)
                    .request("Request")
                    .response("Response")
                    .addHeader("version", BuildConfig.VERSION_NAME)
                    .build();
        }
  //创建ssl 链接
    private static SSLSocketFactory createSSLSocket() {
        try {
            SSLContext ssl = SSLContext.getInstance("SSL");
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    X509Certificate[] x509Certificates = new X509Certificate[0];
                        return x509Certificates;
                }
            };
            ssl.init(null, new TrustManager[]{xtm}, new SecureRandom());
            return ssl.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
            return null;
    }
}