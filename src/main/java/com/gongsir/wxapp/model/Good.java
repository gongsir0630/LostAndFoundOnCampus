package com.gongsir.wxapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 龚涛
 */
public class Good implements Serializable,Comparable<Good> {
    /**
     * id
     */
    private Integer id;

    /**
     * 发布者
     */
    private String openid;

    /**
     * 标题
     */
    private String goodTitle;

    /**
     * 描述（可选）
     */
    private String goodTexts;

    /**
     * 物品分类
     */
    private String goodClass;

    /**
     * 图片
     */
    private String goodImage;

    /**
     * 联系方式
     */
    private String relation;

    /**
     * 丢失分类
     */
    private String goodType;

    /**
     * 发布时间
     */
    private Date time;

    /**
     * 发布的用户信息
     */
    private User user;

    /**
     * 状态:ok/no
     */
    private String goodStatus;

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

    public String getGoodTitle() {
        return goodTitle;
    }

    public void setGoodTitle(String goodTitle) {
        this.goodTitle = goodTitle;
    }

    public String getGoodTexts() {
        return goodTexts;
    }

    public void setGoodTexts(String goodTexts) {
        this.goodTexts = goodTexts;
    }

    public String getGoodClass() {
        return goodClass;
    }

    public void setGoodClass(String goodClass) {
        this.goodClass = goodClass;
    }

    public String getGoodImage() {
        return goodImage;
    }

    public void setGoodImage(String goodImage) {
        this.goodImage = goodImage;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getGoodStatus() {
        return goodStatus;
    }

    public void setGoodStatus(String goodStatus) {
        this.goodStatus = goodStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", goodTitle='" + goodTitle + '\'' +
                ", goodTexts='" + goodTexts + '\'' +
                ", goodClass='" + goodClass + '\'' +
                ", goodImage='" + goodImage + '\'' +
                ", relation='" + relation + '\'' +
                ", goodType='" + goodType + '\'' +
                ", time=" + time +
                ", user=" + user +
                ", goodStatus='" + goodStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Good)) {
            return false;
        }
        Good good = (Good) o;
        return Objects.equals(getId(), good.getId()) &&
                Objects.equals(getOpenid(), good.getOpenid()) &&
                Objects.equals(getGoodTitle(), good.getGoodTitle()) &&
                Objects.equals(getGoodTexts(), good.getGoodTexts()) &&
                Objects.equals(getGoodClass(), good.getGoodClass()) &&
                Objects.equals(getGoodImage(), good.getGoodImage()) &&
                Objects.equals(getRelation(), good.getRelation()) &&
                Objects.equals(getGoodType(), good.getGoodType()) &&
                Objects.equals(getTime(), good.getTime()) &&
                Objects.equals(getUser(), good.getUser()) &&
                Objects.equals(getGoodStatus(), good.getGoodStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOpenid(), getGoodTitle(), getGoodTexts(), getGoodClass(), getGoodImage(), getRelation(), getGoodType(), getTime(), getUser(), getGoodStatus());
    }

    @Override
    public int compareTo(Good o) {
        return id.compareTo(o.getId());
    }
}