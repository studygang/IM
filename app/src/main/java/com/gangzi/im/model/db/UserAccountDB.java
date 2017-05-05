package com.gangzi.im.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gangzi.im.model.dao.UserAccountTable;

/**
 * Created by Administrator on 2017/5/4.
 */

public class UserAccountDB extends SQLiteOpenHelper{

    public UserAccountDB(Context context) {
        super(context,"account.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
         database.execSQL(UserAccountTable.CREATE_TAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

    }
}
