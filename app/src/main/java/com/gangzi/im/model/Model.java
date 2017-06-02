package com.gangzi.im.model;

/**
 * Created by Administrator on 2017/5/4.
 */

import android.content.Context;
import android.graphics.PorterDuff;

import com.gangzi.im.model.bean.UserInfo;
import com.gangzi.im.model.dao.UserAccountDao;
import com.gangzi.im.model.db.DBManager;

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
    private DBManager dbManager;

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
        //开启全局监听
        EventListener eventListener=new EventListener(mContext);
    }
    public ExecutorService getGlobalThreadPool(){
        return mService;
    }

    /**
     * 用户登录成功后处理
     */
    public void loginSuccess(UserInfo account) {
        if (account==null){
            return;
        }
        if (dbManager!=null){
            dbManager.close();
        }
        dbManager=new DBManager(mContext,account.getUserName());
    }
    public DBManager getDbManager(){
        return  dbManager;
    }

    /**
     * 获取用户账号数据库的操作类对象
     * @return
     */
    public UserAccountDao getUserDao(){
        return userAccountDao;
    }
}
