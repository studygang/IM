package com.gangzi.im.model.bean;

/**
 * 用户账号的bean类
 * Created by Administrator on 2017/5/4.
 */

public class UserInfo {

    private String userName;
    private String hxId;//环形Id
    private String userPassword;
    private String nick;//用户昵称
    private String photo;//用户头像

    public UserInfo() {
    }

    public UserInfo(String userName) {
        this.userName = userName;
        this.nick=userName;
        this.hxId=userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHxId() {
        return hxId;
    }

    public void setHxId(String hxId) {
        this.hxId = hxId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", hxId='" + hxId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", nick='" + nick + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
