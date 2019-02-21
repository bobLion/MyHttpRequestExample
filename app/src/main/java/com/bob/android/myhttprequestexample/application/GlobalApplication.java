package com.bob.android.myhttprequestexample.application;

import android.app.Activity;
import android.app.Application;
import android.widget.TextView;

import com.bob.android.myhttprequestexample.util.NetworkUtils;
import com.rey.material.app.ThemeManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by eagle on 2017-9-15 17:27
 */

public class GlobalApplication extends Application {
    private static GlobalApplication application;
    private List<Activity> activityList = new ArrayList<>();
    TextView bg_tv;
    private String mName="";
    private String mUseCode="";


    public static GlobalApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //material  UI组件
        ThemeManager.init(this, 2, 0, null);

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

    }


    public String getIp() {
        return NetworkUtils.getIPAddress(getApplicationContext());
    }

    //遍历所有Activity并finish
    public void exit() {
        for(Activity activity:activityList) {
            activity.finish();
        }
        System.exit(0);
    }

}