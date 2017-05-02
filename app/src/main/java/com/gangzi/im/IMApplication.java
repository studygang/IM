package com.gangzi.im;

import android.app.Application;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by Administrator on 2017/5/2.
 */

public class IMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options=new EMOptions();
        options.setAutoAcceptGroupInvitation(false);
        options.setAutoAcceptGroupInvitation(false);
        EaseUI.getInstance().init(this,options);
    }
}
