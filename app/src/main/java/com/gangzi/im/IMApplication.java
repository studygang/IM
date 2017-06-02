package com.gangzi.im;

import android.app.Application;
import android.content.Context;

import com.gangzi.im.model.Model;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by Administrator on 2017/5/2.
 */

public class IMApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options=new EMOptions();
        options.setAutoAcceptGroupInvitation(false);
        options.setAutoAcceptGroupInvitation(false);
        EaseUI.getInstance().init(this,options);
        Model.getInstance().init(this);
        //初始化全局上下文
        context=this;
    }
    //获取全局上下文对象
    public static Context getGlobalApplication(){
        return context;
    }
}
