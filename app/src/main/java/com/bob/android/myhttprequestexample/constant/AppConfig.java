package com.bob.android.myhttprequestexample.constant;

/**
 * @package com.bob.android.myhttprequestexample.constant
 * @fileName AppConfig
 * @Author Bob on 2019/2/15 16:41.
 * @Describe TODO
 */
public class AppConfig {

    /**
     * @Field testMode : 运行模式 测试与正式区分
     */
    public static boolean testMode = false;
    /**
     * @Fields BASE_URL:Web根路径
     */
    public static final String TEST_URL = "http://172.20.32.48:8081/";
    public static final String RELEASE_URL = "http://172.20.32.48:8081/";
    public static final String BASE_URL = testMode?TEST_URL:RELEASE_URL;


    /**
     * @Fields KEY_PASSWORD:AES加密密码
     */
    public static final String PACKAGE_NAME = "com.bob.android.myhttprequestexample";

    /**
     * @Fields ENCRYPT_REQUEST_PARAM:加密参数名
     */
    public static final String ENCRYPT_REQUEST_PARAM = "jsonParam";

    /**
     * @Fields ENCRYPT_KEY_PARAM:加密秘钥
     */
    public static final String ENCRYPT_KEY_PARAM = "keyParam";

    /**
     * @Fields UNENCRYPTED_REQUEST_PARAM:非加密参数名
     */
    public static final String UNENCRYPTED_REQUEST_PARAM = "fileParam";

    public static final String CRASH_LOG_PATH = "sdcard/crash/";
}
