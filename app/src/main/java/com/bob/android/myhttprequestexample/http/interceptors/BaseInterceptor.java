package com.bob.android.myhttprequestexample.http.interceptors;

import com.bob.android.myhttprequestexample.application.GlobalApplication;
import com.bob.android.myhttprequestexample.constant.AppConfig;
import com.bob.android.myhttprequestexample.encrypt.AESEncrypt;
import com.bob.android.myhttprequestexample.encrypt.AppEncryptUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by eagle on 2017-10-9 21:23
 */

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        /*String credentials = ":";
        String basic = "Basic " +_WRAP);*/
        String ip = GlobalApplication.getInstance().getIp();
//        String authorization = GlobalApplication.getInstance().getUserInfoEntity()==null?"":GlobalApplication.getInstance().getUserInfoEntity().getAuthorization();
        Request originalRequest = chain.request();
        String cacheControl = originalRequest.cacheControl().toString();
        Request.Builder requestBuilder = originalRequest.newBuilder() //Basic Authentication,也可用于token验证,OAuth验证
//                .header("Authorization", authorization)
                .header("Accept", "application/json")
                .addHeader("packageName", AppConfig.PACKAGE_NAME)
                .addHeader("ip", ip)
                .addHeader("os", "Android").method(originalRequest.method(), originalRequest.body());
        if (originalRequest.body() instanceof FormBody) {
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oldFormBody = (FormBody) originalRequest.body();
            String key = AESEncrypt.randomKey();
            for (int i = 0; i < oldFormBody.size(); i++) {
                String name = oldFormBody.encodedName(i);
                String value = oldFormBody.value(i);

                if (name.equals(AppConfig.ENCRYPT_REQUEST_PARAM)) {
                    try {
                        //加密内容
                        newFormBody.addEncoded(name, AppEncryptUtils.encodeParam(key, value));
                        newFormBody.addEncoded(AppConfig.ENCRYPT_KEY_PARAM, AppEncryptUtils.encodeKey(key));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (name.equals(AppConfig.UNENCRYPTED_REQUEST_PARAM)) {
                    //图片信息不加密
                    newFormBody.addEncoded(name, value);
                } else if (name.equals(AppConfig.ENCRYPT_KEY_PARAM)) {
//                    //加密密钥
//                    try {
//                        newFormBody.addEncoded(name, AppEncryptUtils.encodeKey(key));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                } else {
                    newFormBody.addEncoded(name, value);
                }
            }
            requestBuilder.method(originalRequest.method(), newFormBody.build());
        } else if (originalRequest.body() instanceof MultipartBody) { // 文件
//
        } else {

        }

        Request request = requestBuilder.build();

        Response originalResponse = chain.proceed(request);
        Response.Builder responseBuilder = //Cache control设置缓存
                originalResponse.newBuilder().header("Cache-Control", cacheControl);
        return responseBuilder.build();

    }

    /**
     * 获取常规post请求参数
     */
    private String getParamContent(RequestBody body) throws IOException {
        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        return buffer.readUtf8();
    }

}
