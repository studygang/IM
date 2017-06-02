package com.gangzi.im.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gangzi.im.MainActivity;
import com.gangzi.im.R;
import com.gangzi.im.model.Model;
import com.gangzi.im.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;

public class SplashActivity extends Activity {

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isFinishing()){
                return;
            }
            toMainOrLogin();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.sendMessageDelayed(Message.obtain(),2000);
    }

    private void toMainOrLogin() {

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //判断当前账号是否已经登录过
                if (EMClient.getInstance().isLoggedInBefore()){
                    //调到主页面，同时获取到当前登录用户的信息
                    UserInfo userInfo=Model.getInstance().getUserDao().getAccountByHxId(EMClient.getInstance().getCurrentUser());
                    if (userInfo==null){
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }else{
                        Model.getInstance().loginSuccess(userInfo);
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }else{//没登录过
                    //调到登录界面
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                }
                finish();
            }
        });
    }

}
