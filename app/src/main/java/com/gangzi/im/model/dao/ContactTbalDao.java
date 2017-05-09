package com.gangzi.im.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gangzi.im.model.bean.UserInfo;
import com.gangzi.im.model.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */

public class ContactTbalDao {

    private DBHelper mDBHelper;
    public ContactTbalDao(DBHelper dbHelper) {
        this.mDBHelper=dbHelper;
    }
    //获取所有联系人
    public List<UserInfo>getContacts(){
        List<UserInfo> userInfoList=new ArrayList<>();
        SQLiteDatabase database=mDBHelper.getReadableDatabase();
        String sql="select * from "+ContactTable.TABLE_NAME+" where "+ContactTable.COL_IS_CONTACT+ "=1";
        Cursor cursor=database.rawQuery(sql,null);
        while (cursor.moveToNext()){
            UserInfo userInfo=new UserInfo();
            userInfo.setHxId(cursor.getString(cursor.getColumnIndex(ContactTable.COL_HXID)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(ContactTable.COL_PHOTO)));
            userInfo.setUserName(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NICK)));
            userInfoList.add(userInfo);
        }
        cursor.close();
        database.close();
        return userInfoList;
    }
    //通过环形ID获取联系人单个信息
    public UserInfo getContactByHx(String hxId){
        UserInfo userInfo=null;
        if (hxId==null){
            return null;
        }
        SQLiteDatabase database=mDBHelper.getReadableDatabase();
        String sql="select * from "+ContactTable.TABLE_NAME+" where "+ContactTable.COL_HXID+"=?";
        Cursor cursor=database.rawQuery(sql,new String[]{hxId});
        if (cursor.moveToNext()){
            userInfo=new UserInfo();
            userInfo.setHxId(cursor.getString(cursor.getColumnIndex(ContactTable.COL_HXID)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(ContactTable.COL_PHOTO)));
            userInfo.setUserName(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NICK)));
        }
        cursor.close();
        database.close();
        return userInfo;
    }
    //通过环形ID获取用户联系人信息
    public List<UserInfo>getContactsByHx(List<String> hxIds){
        if (hxIds==null||hxIds.size()<=0){
            return null;
        }
        List<UserInfo>contacts=new ArrayList<>();
        //遍历hxIds,来查找
        for (String hxId:hxIds){
            UserInfo contact=getContactByHx(hxId);
            contacts.add(contact);
        }
        return contacts;
    }
    //保存单个联系人
    public void saveContacts(UserInfo conatact,boolean isMyContact){
        if (conatact==null){
            return ;
        }
        SQLiteDatabase database=mDBHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ContactTable.COL_HXID,conatact.getHxId());
        values.put(ContactTable.COL_NAME,conatact.getUserName());
        values.put(ContactTable.COL_NICK,conatact.getNick());
        values.put(ContactTable.COL_PHOTO,conatact.getPhoto());
        values.put(ContactTable.COL_IS_CONTACT,isMyContact?1:0);
        database.replace(ContactTable.TABLE_NAME,null,values);
    }
    //保存联系人
    public void saveContacts(List<UserInfo> conatacts,boolean isMyContact){
        if (conatacts==null|conatacts.size()<=0){
            return;
        }
        for (UserInfo contact:conatacts){
            saveContacts(contact,isMyContact);
        }
    }
    //删除联系人信息
    public void deleteContactByHxId(String hxId){
        if (hxId==null){
            return;
        }
        SQLiteDatabase database=mDBHelper.getReadableDatabase();
        database.delete(ContactTable.TABLE_NAME,ContactTable.COL_HXID+"=?",new String[]{hxId});
        database.close();
    }
}
