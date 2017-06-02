package com.gangzi.im.model.db;

import android.content.Context;

import com.gangzi.im.model.dao.ContactTbalDao;
import com.gangzi.im.model.dao.InvateTableDao;

/**
 * Created by Administrator on 2017/6/1.
 */

public class DBManager {

    private final DBHelper mDBHelper;
    private final ContactTbalDao mContactTbalDao;
    private final InvateTableDao mInvateTableDao;
    DBManager dbManager;

    public DBManager(Context context,String name) {
        mDBHelper= new DBHelper(context,name);
        //创建该数据库中两张表的操作类
        mContactTbalDao=new ContactTbalDao(mDBHelper);
        mInvateTableDao=new InvateTableDao(mDBHelper);
    }

    //获取联系人表的操作类对象
    public ContactTbalDao getContactTbalDao(){
        return mContactTbalDao;
    }
    //获取邀请信息表的操作类对象
    public InvateTableDao getInvateTableDao(){
        return mInvateTableDao;
    }

    /**
     * 关闭数据库的方法
     */
    public void close() {
        mDBHelper.close();
    }
}
