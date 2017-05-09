package com.gangzi.im.model.dao;

/**
 * Created by Administrator on 2017/5/9.
 */

public class ContactTable {
    public static final String TABLE_NAME="tab_contact";
    public static final String COL_HXID="hxId";
    public static final String COL_NAME="name";
    public static final String COL_NICK="nick";
    public static final String COL_PHOTO="photo";
    public static final String COL_IS_CONTACT="is_contact";//是否是联系人

    public static final String CREATE_TABLE="create table "+TABLE_NAME+" ("+COL_HXID+" text primary key,"
            +COL_NAME+" text,"+COL_NICK+" text,"+COL_PHOTO+" text,"+COL_IS_CONTACT+" integer);";
}
