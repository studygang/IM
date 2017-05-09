package com.gangzi.im.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gangzi.im.model.dao.ContactTable;
import com.gangzi.im.model.dao.InvateTable;

/**
 * Created by Administrator on 2017/5/9.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name) {
        super(context, name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //创建联系人的表
        database.execSQL(ContactTable.CREATE_TABLE);
        //创建邀请信息的表
        database.execSQL(InvateTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

    }
}
