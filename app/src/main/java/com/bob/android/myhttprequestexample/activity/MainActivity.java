package com.bob.android.myhttprequestexample.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bob.android.myhttprequestexample.R;
import com.bob.android.myhttprequestexample.http.ResponseResult;
import com.bob.android.myhttprequestexample.http.RestCreator;
import com.bob.android.myhttprequestexample.param.TestParam;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_main)
    TextView mTvMain;

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick(R.id.btn_click)
    void btnClick(View view){
        Intent intent = new Intent(mContext,NewPageActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_main)
    void clickTextView(View view){
        TestParam testParam = new TestParam();
        testParam.setPassword("admin123");
        Call<ResponseResult> call = RestCreator.getRestService().login(JSON.toJSONString(testParam));
        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {

            }
        });
    }
}
