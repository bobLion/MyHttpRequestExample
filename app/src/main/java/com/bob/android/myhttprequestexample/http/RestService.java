package com.bob.android.myhttprequestexample.http;

import com.bob.android.myhttprequestexample.constant.AppConfig;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by eagle on 2017-10-9 15:06
 */

public interface RestService {

    /**
     * 用户登录
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("/sip3/login")
    Call<ResponseResult> login(@Field(AppConfig.ENCRYPT_REQUEST_PARAM) String jsonParam);

    /**
     * 用户登录
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("/sip3/facelogin")
    Call<ResponseResult> faceLogin(@Field(AppConfig.ENCRYPT_REQUEST_PARAM) String jsonParam);
//    Call<ResponseResult> login(@Field(AppConfig.ENCRYPT_REQUEST_PARAM) String jsonParam,@Field(AppConfig.ENCRYPT_KEY_PARAM) String keyParam);


    /**
     * 上传图片
     * */
    @Multipart
    @POST("/zuul/Sys-Operation/upload")
    Call<ResponseResult> uploadControlPic(@Part MultipartBody.Part img);

    /**
     * 上传多数据
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("/server-control-alarm/insertVehicleControlTask")
    Call<ResponseResult> vehicleControlUpload(@Field(AppConfig.ENCRYPT_REQUEST_PARAM) String jsonParam);


    /**
     * 下载应用apk
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/download")
    Call<ResponseBody> downloadApp(@Field(AppConfig.ENCRYPT_REQUEST_PARAM) String jsonParam);
    /**
     * 请求应用APK版本号
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/version")
    Call<ResponseResult> getVersion(@Field(AppConfig.ENCRYPT_REQUEST_PARAM) String jsonParam);

}
