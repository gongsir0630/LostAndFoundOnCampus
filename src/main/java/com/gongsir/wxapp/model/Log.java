package com.gongsir.wxapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gongsir
 * @date 2020/2/17 13:48
 * 编码不要畏惧变化，要拥抱变化
 */
public class Log implements Serializable {
    /**
     * 用户名
     */
    private String username;
    /**
     * IP地址
     */
    private String ip;
    /**
     * ip地理位置
     */
    private String address;
    /**
     * 登陆时间
     */
    private Date loginTime;
    /**
     * 在线状态
     */
    private boolean online;

    private static final long serialVersionUID = 7814549012513903697L;

    public Log() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Log(String username, String ip, String address, Date loginTime, boolean online) {
        this.username = username;
        this.ip = ip;
        this.address = address;
        this.loginTime = loginTime;
        this.online = online;
    }

    @Override
    public String toString() {
        return "Log{" +
                "username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", address='" + address + '\'' +
                ", loginTime=" + loginTime +
                ", online=" + online +
                '}';
    }
}
