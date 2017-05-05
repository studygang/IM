package com.gangzi.im.model;

/**
 * Created by Administrator on 2017/5/4.
 */

import android.content.Context;
import android.graphics.PorterDuff;

import com.gangzi.im.model.dao.UserAccountDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 数据模型层全局类
 */
public class Model {

    private static Model mModel;
    private Context mContext;
    private ExecutorService mService= Executors.newCachedThreadPool();
    private UserAccountDao userAccountDao;

    private Model(){

    }

    /**
     * 获取单例对象
     * @return
     */
    public static Model getInstance(){
        if (mModel==null){
            mModel=new Model();
        }
        return mModel;
    }

    /**
     * 初始化的方法
     * @param context
     */
    public void init(Context context){
        mContext=context;
        userAccountDao=new UserAccountDao(mContext);
    }
    public ExecutorService getGlobalThreadPool(){
        return mService;
    }

    /**
     * 用户登录成功后处理
     */
    public void loginSuccess() {
    }

    /**
     * 获取用户账号数据库的操作类对象
     * @return
     */
    public UserAccountDao getUserDao(){
        return userAccountDao;
    }
}
