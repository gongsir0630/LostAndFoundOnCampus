package com.gongsir.wxapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 龚涛
 */
public class Listen implements Serializable, Comparable<Listen> {
    /**
     * 监听id
     */
    private Integer id;

    /**
     * 接收用户
     */
    private String openid;

    /**
     * 证件类型
     */
    private String lisType;

    /**
     * 证件号码
     */
    private String lisNum;

    /**
     * 联系电话 手机号，用于短信通知用户
     */
    private String telephone;

    /**
     * 监听状态
     */
    private String lisStatus;

    /**
     * 监听时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lisTime;

    /**
     * formId-QQ QQ推送模板消息需要
     */
    private String formId;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
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
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getLisStatus() == null ? other.getLisStatus() == null : this.getLisStatus().equals(other.getLisStatus()))
            && (this.getLisTime() == null ? other.getLisTime() == null : this.getLisTime().equals(other.getLisTime()))
            && (this.getFormId() == null ? other.getFormId() == null : this.getFormId().equals(other.getFormId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getLisType() == null) ? 0 : getLisType().hashCode());
        result = prime * result + ((getLisNum() == null) ? 0 : getLisNum().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getLisStatus() == null) ? 0 : getLisStatus().hashCode());
        result = prime * result + ((getLisTime() == null) ? 0 : getLisTime().hashCode());
        result = prime * result + ((getFormId() == null) ? 0 : getFormId().hashCode());
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
        sb.append(", telephone=").append(telephone);
        sb.append(", lisStatus=").append(lisStatus);
        sb.append(", lisTime=").append(lisTime);
        sb.append(", formId=").append(formId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Listen o) {
        return id.compareTo(o.getId());
    }
}