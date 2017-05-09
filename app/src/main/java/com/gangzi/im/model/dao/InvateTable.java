package com.gangzi.im.model.dao;

/**
 * Created by Administrator on 2017/5/9.
 */

public class InvateTable {
    public static final String TAB_NAME="tab_invate";

    public static final String COL_USER_HXID="user_hxid";
    public static final String COL_USER_NAME="user_name";
    public static final String COL_GROUP_NAME="group_name";
    public static final String COL_GROUP_HXID="group_hxid";
    public static final String COL_REASON="reason";
    public static final String COL_STATUS="status";

    public static final String CREATE_TABLE="create table "+TAB_NAME+" ("+COL_USER_HXID+" text primary key,"
            + COL_USER_NAME+" text,"+COL_GROUP_NAME+" text,"+COL_GROUP_HXID+" text,"+COL_REASON+" text,"
            + COL_STATUS+" integer);";

}
