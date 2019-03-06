package com.bob.android.myhttprequestexample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bob.android.myhttprequestexample.R;
import com.bob.android.myhttprequestexample.http.ResponseResult;
import com.bob.android.myhttprequestexample.http.RestCreator;
import com.bob.android.myhttprequestexample.param.TestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_main)
    TextView mTvMain;
    @BindView(R.id.btn_connect_mysql)
    Button mBtnConnectMySQL;
    @BindView(R.id.tv_get_mysql_data)
    TextView mTvMysqlData;

    private Connection connection = null;
    private Thread pThread;

    Context mContext;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = new Bundle();
            data = msg.getData();
            Toast.makeText(mContext,"id:" + data.get("id").toString()
                    +"class:"+data.get("class".toString()),Toast.LENGTH_LONG).show();
            mTvMysqlData.setText("获得数据库数据"+data.toString());
        }
    };
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

    @OnClick(R.id.btn_connect_mysql)
    void connectMysql(View view){
        new Thread(runnable).start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://172.20.26.211:3306/mytest?useSSL=false",
                        "admin111","admin111");
//                Toast.makeText(mContext,"连接成功",Toast.LENGTH_LONG).show();
                System.out.print("连接成功");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(null != connection){
                testConnection(connection);
            }else{
                System.out.print("连接数据库失败");
                return;
            }
        }

        public void testConnection(Connection connection){
            String sql = "select * from tb_student";
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                Bundle bundle = new Bundle();
                while (rs.next()){
                    bundle.clear();
                    bundle.putString("id",rs.getString("id"));
                    bundle.putString("class",rs.getString("class"));
                    Message msg = new Message();
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }

                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(null != connection){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    };
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
