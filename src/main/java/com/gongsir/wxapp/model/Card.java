package com.gongsir.wxapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 龚涛
 */
public class Card implements Serializable,Comparable<Card> {
    private Integer id;

    /**
     * 发布人信息
     */
    private String openid;

    /**
     * 证件类型
     */
    private String cardType;

    /**
     * 卡号
     */
    private String cardNum;

    /**
     * 姓名
     */
    private String cardName;

    /**
     * 联系方式
     */
    private String relation;

    /**
     * 发布时间
     */
    private Date cardTime;

    /**
     * 状态:ok/no
     */
    private String cardStatus;

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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Date getCardTime() {
        return cardTime;
    }

    public void setCardTime(Date cardTime) {
        this.cardTime = cardTime;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", cardName='" + cardName + '\'' +
                ", relation='" + relation + '\'' +
                ", cardTime=" + cardTime +
                ", cardStatus='" + cardStatus + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public int compareTo(Card o) {
        return id.compareTo(o.getId());
    }
}