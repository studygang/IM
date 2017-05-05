package com.gangzi.im.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gangzi.im.model.bean.UserInfo;
import com.gangzi.im.model.db.UserAccountDB;

/**
 * Created by Administrator on 2017/5/4.
 */

public class UserAccountDao {

    private UserAccountDB mDB;

    public UserAccountDao(Context context) {
            mDB=new UserAccountDB(context);
    }
    public void addAccount(UserInfo userInfo){
        SQLiteDatabase db=mDB.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(UserAccountTable.COL_NAME,userInfo.getUserName());
        values.put(UserAccountTable.COL_HXID,userInfo.getHxId());
        values.put(UserAccountTable.COL_PHOTO,userInfo.getPhoto());
        values.put(UserAccountTable.COL_NICK,userInfo.getNick());
        db.replace(UserAccountTable.TABLE_NAME,null,values);
        db.close();
    }
    public UserInfo getAccountByHxId(String hxId){
        SQLiteDatabase database=mDB.getReadableDatabase();
        String sql="select * from "+UserAccountTable.TABLE_NAME + " where "+ UserAccountTable.COL_HXID + "=?";
        Cursor cursor= database.rawQuery(sql,new String[]{hxId});
        UserInfo userInfon=null;
        if (cursor.moveToNext()){
           userInfon= new UserInfo();
            userInfon.setHxId(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
            userInfon.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NICK)));
            userInfon.setUserName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            userInfon.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));
        }
        cursor.close();
        database.close();
        return userInfon;
    }
}
