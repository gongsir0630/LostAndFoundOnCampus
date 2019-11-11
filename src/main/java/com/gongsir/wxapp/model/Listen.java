package com.gongsir.wxapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Listen implements Serializable {
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Listen other = (Listen) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getLisType() == null ? other.getLisType() == null : this.getLisType().equals(other.getLisType()))
            && (this.getLisNum() == null ? other.getLisNum() == null : this.getLisNum().equals(other.getLisNum()))
            && (this.getFormId() == null ? other.getFormId() == null : this.getFormId().equals(other.getFormId()))
            && (this.getLisStatus() == null ? other.getLisStatus() == null : this.getLisStatus().equals(other.getLisStatus()))
            && (this.getLisTime() == null ? other.getLisTime() == null : this.getLisTime().equals(other.getLisTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getLisType() == null) ? 0 : getLisType().hashCode());
        result = prime * result + ((getLisNum() == null) ? 0 : getLisNum().hashCode());
        result = prime * result + ((getFormId() == null) ? 0 : getFormId().hashCode());
        result = prime * result + ((getLisStatus() == null) ? 0 : getLisStatus().hashCode());
        result = prime * result + ((getLisTime() == null) ? 0 : getLisTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openid=").append(openid);
        sb.append(", lisType=").append(lisType);
        sb.append(", lisNum=").append(lisNum);
        sb.append(", formId=").append(formId);
        sb.append(", lisStatus=").append(lisStatus);
        sb.append(", lisTime=").append(lisTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}