package com.gangzi.im.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gangzi.im.model.bean.GroupInfo;
import com.gangzi.im.model.bean.InvationInfo;
import com.gangzi.im.model.bean.UserInfo;
import com.gangzi.im.model.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */

public class InvateTableDao {
    private DBHelper mDBHelper;
    public InvateTableDao(DBHelper mDBHelper) {
        this.mDBHelper=mDBHelper;
    }
    //添加邀请
    public void addInvatation(InvationInfo info){
        SQLiteDatabase database=mDBHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(InvateTable.COL_REASON,info.getReason());//原因
        values.put(InvateTable.COL_STATUS,info.getStatus().ordinal());//状态
        UserInfo userInfo=info.getUserInfo();
        if (userInfo!=null){//联系人
            values.put(InvateTable.COL_USER_HXID,info.getUserInfo().getHxId());
            values.put(InvateTable.COL_USER_NAME,info.getUserInfo().getUserName());
        }else{
            values.put(InvateTable.COL_GROUP_HXID,info.getGroupInfo().getGroupId());
            values.put(InvateTable.COL_GROUP_NAME,info.getGroupInfo().getGroupName());
            values.put(InvateTable.COL_USER_HXID,info.getGroupInfo().getInvatePerson());
        }
        database.replace(InvateTable.TAB_NAME,null,values);
    }
    //获取所有邀请信息
    public List<InvationInfo>getInvitations(){
       SQLiteDatabase database=mDBHelper.getReadableDatabase();
        String sql="select * from " +InvateTable.TAB_NAME;
        Cursor cursor=database.rawQuery(sql,null);
        List<InvationInfo>invationInfos=new ArrayList<>();
        while (cursor.moveToNext()){
            InvationInfo info=new InvationInfo();
            info.setReason(cursor.getString(cursor.getColumnIndex(InvateTable.COL_REASON)));
            info.setStatus(intToInviteStatus(cursor.getInt(cursor.getColumnIndex(InvateTable.COL_STATUS))));
            String groupId=cursor.getString(cursor.getColumnIndex(InvateTable.COL_GROUP_HXID));
            if (groupId==null){//联系人的邀请信息
                UserInfo userInfo=new UserInfo();
                userInfo.setHxId(cursor.getString(cursor.getColumnIndex(InvateTable.COL_USER_HXID)));
                userInfo.setUserName(cursor.getString(cursor.getColumnIndex(InvateTable.COL_USER_NAME)));
                userInfo.setNick(cursor.getString(cursor.getColumnIndex(InvateTable.COL_USER_NAME)));
            }else{
                //群组的邀请信息
                GroupInfo groupInfo=new GroupInfo();
                groupInfo.setGroupId(cursor.getString(cursor.getColumnIndex(InvateTable.COL_GROUP_HXID)));
                groupInfo.setGroupName(cursor.getString(cursor.getColumnIndex(InvateTable.COL_GROUP_NAME)));
                groupInfo.setInvatePerson(cursor.getString(cursor.getColumnIndex(InvateTable.COL_USER_HXID)));
            }
            invationInfos.add(info);
        }
        cursor.close();
        return invationInfos;
    }
    //将int类型状态转换为邀请的状态
    private InvationInfo.InvitationStatus intToInviteStatus(int instatus) {
        if (instatus == InvationInfo.InvitationStatus.NEW_INVITE.ordinal()) {
            return InvationInfo.InvitationStatus.NEW_INVITE;
        }
        if (instatus == InvationInfo.InvitationStatus.INVITE_ACCEPT.ordinal()) {
            return InvationInfo.InvitationStatus.INVITE_ACCEPT;
        }
        if (instatus == InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER.ordinal()) {
            return InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER;
        }
        if (instatus == InvationInfo.InvitationStatus.NEW_GROUP_INVITE.ordinal()) {
            return InvationInfo.InvitationStatus.NEW_GROUP_INVITE;
        }
        if (instatus == InvationInfo.InvitationStatus.NEW_GROUP_APPLICATION.ordinal()) {
            return InvationInfo.InvitationStatus.NEW_GROUP_APPLICATION;
        }
        if (instatus == InvationInfo.InvitationStatus.GROUP_INVITE_ACCEPTED.ordinal()) {
            return InvationInfo.InvitationStatus.GROUP_INVITE_ACCEPTED;
        }
        if (instatus==InvationInfo.InvitationStatus.GROUP_APPLICATION_ACCEPTED.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_APPLICATION_ACCEPTED;
        }
        if (instatus==InvationInfo.InvitationStatus.GROUP_INVITE_DECLINED.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_INVITE_DECLINED;
        }
        if (instatus==InvationInfo.InvitationStatus.GROUP_APPLICATION_DECLINED.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_APPLICATION_DECLINED;
        }
        if (instatus==InvationInfo.InvitationStatus.GROUP_ACCEPT_INVITE.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_INVITE_ACCEPTED;
        }
        if (instatus==InvationInfo.InvitationStatus.GROUP_ACCEPT_APPLICATION.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_ACCEPT_APPLICATION;
        }
        if (instatus==InvationInfo.InvitationStatus.GROUP_REJECT_APPLICATION.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_REJECT_APPLICATION;
        }
        if (instatus==InvationInfo.InvitationStatus.GROUP_REJECT_INVITE.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_REJECT_INVITE;
        }
        return null;
    }
    //删除邀请
    public void removeInvitation(String hxId){
        if (hxId==null){
            return;
        }
        SQLiteDatabase database=mDBHelper.getReadableDatabase();
        database.delete(InvateTable.TAB_NAME,InvateTable.COL_USER_HXID + "=?",new String[]{hxId});
    }
    //更新邀请状态
    public void updateInvitationStatus(InvationInfo.InvitationStatus invitationStatus,String hxid){
        if (hxid==null){
            return;
        }
        SQLiteDatabase database=mDBHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(InvateTable.COL_STATUS,invitationStatus.ordinal());
        database.update(InvateTable.TAB_NAME,values,InvateTable.COL_USER_HXID+"=?",new String[]{hxid});
    }
}
