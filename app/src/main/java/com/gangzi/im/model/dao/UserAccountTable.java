package com.gangzi.im.model.dao;

/**
 * Created by Administrator on 2017/5/4.
 */

public class UserAccountTable {

    public static final String TABLE_NAME="table_account";
    public static final String COL_NAME="userName";
    public static final String COL_HXID="hxId";
    public static final String COL_NICK="nick";
    public static final String COL_PHOTO="photo";

    public static final String CREATE_TAB = "create table " + TABLE_NAME + " ("
            + COL_HXID + " text primary key,"
            + COL_NAME + " text,"
            + COL_NICK + " text,"
            + COL_PHOTO + " text);";
}
