package com.gongsir.wxapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 龚涛
 */
public class Good implements Serializable {
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
     * 状态:ok/no
     */
    private String goodStatus;

    /**
     * user信息
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
        Good other = (Good) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getGoodTitle() == null ? other.getGoodTitle() == null : this.getGoodTitle().equals(other.getGoodTitle()))
            && (this.getGoodTexts() == null ? other.getGoodTexts() == null : this.getGoodTexts().equals(other.getGoodTexts()))
            && (this.getGoodClass() == null ? other.getGoodClass() == null : this.getGoodClass().equals(other.getGoodClass()))
            && (this.getGoodImage() == null ? other.getGoodImage() == null : this.getGoodImage().equals(other.getGoodImage()))
            && (this.getRelation() == null ? other.getRelation() == null : this.getRelation().equals(other.getRelation()))
            && (this.getGoodType() == null ? other.getGoodType() == null : this.getGoodType().equals(other.getGoodType()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getGoodStatus() == null ? other.getGoodStatus() == null : this.getGoodStatus().equals(other.getGoodStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getGoodTitle() == null) ? 0 : getGoodTitle().hashCode());
        result = prime * result + ((getGoodTexts() == null) ? 0 : getGoodTexts().hashCode());
        result = prime * result + ((getGoodClass() == null) ? 0 : getGoodClass().hashCode());
        result = prime * result + ((getGoodImage() == null) ? 0 : getGoodImage().hashCode());
        result = prime * result + ((getRelation() == null) ? 0 : getRelation().hashCode());
        result = prime * result + ((getGoodType() == null) ? 0 : getGoodType().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getGoodStatus() == null) ? 0 : getGoodStatus().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
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
        sb.append(", goodTitle=").append(goodTitle);
        sb.append(", goodTexts=").append(goodTexts);
        sb.append(", goodClass=").append(goodClass);
        sb.append(", goodImage=").append(goodImage);
        sb.append(", relation=").append(relation);
        sb.append(", goodType=").append(goodType);
        sb.append(", time=").append(time);
        sb.append(", goodStatus=").append(goodStatus);
        sb.append(", User=").append(user);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}