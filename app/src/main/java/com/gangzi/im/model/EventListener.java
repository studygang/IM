package com.gangzi.im.model;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.gangzi.im.model.bean.InvationInfo;
import com.gangzi.im.model.bean.UserInfo;
import com.gangzi.im.utils.Constant;
import com.gangzi.im.utils.SpUtils;
import com.google.android.gms.games.multiplayer.Invitation;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

/**
 * Created by Administrator on 2017/6/1.
 */

public class EventListener {

    private Context mContext;
    private static LocalBroadcastManager lbm;

    public EventListener(Context context) {
        mContext=context;
        //创建一个发送广播的管理者
        lbm = LocalBroadcastManager.getInstance(mContext);
        //注册一个联系人变化的监听
        EMClient.getInstance().contactManager().setContactListener(emContactListener);
    }
    //注册一个联系人变化的监听
    private final EMContactListener emContactListener=new EMContactListener() {

        //联系人增加后执行的方法
        @Override
        public void onContactAdded(String hxid) {
            //数据库更新
            Model.getInstance().getDbManager().getContactTbalDao().saveContacts(new UserInfo(hxid),true);
            //发送联系人变化的广播
            lbm.sendBroadcast(new Intent(Constant.CONTACT_CHANGE));
        }

        //联系人删除执行
        @Override
        public void onContactDeleted(String hxid) {
            //数据更新
            Model.getInstance().getDbManager().getContactTbalDao().deleteContactByHxId(hxid);
            Model.getInstance().getDbManager().getInvateTableDao().removeInvitation(hxid);
            //发送联系人变化的广播
            lbm.sendBroadcast(new Intent(Constant.CONTACT_CHANGE));
        }

        //接收到联系人的新邀请
        @Override
        public void onContactInvited(String hxid, String reason) {
            //数据更新
            InvationInfo info=new InvationInfo();
            info.setUserInfo(new UserInfo(hxid));
            info.setReason(reason);
            info.setStatus(InvationInfo.InvitationStatus.NEW_INVITE);
            Model.getInstance().getDbManager().getInvateTableDao().addInvatation(info);
            //红点的处理
            SpUtils.getmSpUtils().saveData(SpUtils.IS_NEW_INVITE,true);
            //发送邀请信息变化的广播
            lbm.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHANGE));
        }
        //别人同意了你的好友邀请
        @Override
        public void onFriendRequestAccepted(String hxid) {
            //数据更新
            InvationInfo invitationInfo=new InvationInfo();
            invitationInfo.setUserInfo(new UserInfo(hxid));
            invitationInfo.setStatus(InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER);//别人同意了你的邀请
            Model.getInstance().getDbManager().getInvateTableDao().addInvatation(invitationInfo);
            //红点处理
            SpUtils.getmSpUtils().saveData(SpUtils.IS_NEW_INVITE,true);
            //发送邀请信息的变化
            lbm.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHANGE));
        }
        //别人拒绝了你的好友邀请
        @Override
        public void onFriendRequestDeclined(String s) {
            //红点处理
            SpUtils.getmSpUtils().saveData(SpUtils.IS_NEW_INVITE,true);
            //发送邀请信息的变化
            lbm.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHANGE));
        }
    };
}
