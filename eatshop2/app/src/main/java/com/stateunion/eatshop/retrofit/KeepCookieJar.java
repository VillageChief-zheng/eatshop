package com.stateunion.eatshop.retrofit;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
  * Created by zhangguozheng on 2017/8/24.
  */

public class KeepCookieJar implements okhttp3.CookieJar {
    public static List<Cookie> cookies = new ArrayList<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        this.cookies = cookies;
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return cookies;
    }
}
