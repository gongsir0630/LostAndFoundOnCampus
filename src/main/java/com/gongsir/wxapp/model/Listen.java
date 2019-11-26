package com.gongsir.wxapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 龚涛
 */
public class Listen implements Serializable,Comparable<Listen> {
    private Integer id;

    /**
     * 接收消息通知的用户
     */
    private String openid;

    /**
     * 监听的证件类型
     */
    private String lisType;

    /**
     * 监听证件号码
     */
    private String lisNum;

    /**
     * formId,用于发送消息
     */
    private String formId;

    /**
     * 监听状态
     */
    private String lisStatus;

    /**
     * 监听时间
     */
    private Date lisTime;

    /**
     * user
     */
    private User user;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLisType() {
        return lisType;
    }

    public void setLisType(String lisType) {
        this.lisType = lisType;
    }

    public String getLisNum() {
        return lisNum;
    }

    public void setLisNum(String lisNum) {
        this.lisNum = lisNum;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getLisStatus() {
        return lisStatus;
    }

    public void setLisStatus(String lisStatus) {
        this.lisStatus = lisStatus;
    }

    public Date getLisTime() {
        return lisTime;
    }

    public void setLisTime(Date lisTime) {
        this.lisTime = lisTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Listen{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", lisType='" + lisType + '\'' +
                ", lisNum='" + lisNum + '\'' +
                ", formId='" + formId + '\'' +
                ", lisStatus='" + lisStatus + '\'' +
                ", lisTime=" + lisTime +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Listen)) {
            return false;
        }
        Listen listen = (Listen) o;
        return Objects.equals(getId(), listen.getId()) &&
                Objects.equals(getOpenid(), listen.getOpenid()) &&
                Objects.equals(getLisType(), listen.getLisType()) &&
                Objects.equals(getLisNum(), listen.getLisNum()) &&
                Objects.equals(getFormId(), listen.getFormId()) &&
                Objects.equals(getLisStatus(), listen.getLisStatus()) &&
                Objects.equals(getLisTime(), listen.getLisTime()) &&
                Objects.equals(getUser(), listen.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOpenid(), getLisType(), getLisNum(), getFormId(), getLisStatus(), getLisTime(), getUser());
    }

    @Override
    public int compareTo(Listen o) {
        return id.compareTo(o.getId());
    }
}