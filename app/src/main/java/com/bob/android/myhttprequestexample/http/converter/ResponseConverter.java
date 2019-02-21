package com.bob.android.myhttprequestexample.http.converter;

import com.alibaba.fastjson.JSON;
import com.bob.android.myhttprequestexample.encrypt.AESEncrypt;
import com.bob.android.myhttprequestexample.encrypt.RSAPublicKeyUtils;
import com.bob.android.myhttprequestexample.http.ResponseResult;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by eagle on 2017-10-9 20:04
 */

public class ResponseConverter implements Converter<ResponseBody, ResponseResult> {
    public static final ResponseConverter INSTANCE = new ResponseConverter();

    @Override
    public ResponseResult convert(ResponseBody value) throws IOException {
        String mResponseBody=value.string();
        ResponseResult mResponseResult = JSON.parseObject(mResponseBody, ResponseResult.class);

        String key = mResponseResult.getCheckCode();
        try {
            String decodeKey = RSAPublicKeyUtils.decodeKey(key);
            String content = AESEncrypt.decode(decodeKey, mResponseResult.getContent());
            mResponseResult.setContent(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mResponseResult;
    }

}
