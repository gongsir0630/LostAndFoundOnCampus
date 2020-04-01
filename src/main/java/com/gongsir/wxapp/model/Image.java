package com.gongsir.wxapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 龚涛
 */
@ApiModel
public class Image implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "id",example = "1")
    private Integer id;

    /**
     * 更信人
     */
    @ApiModelProperty(value = "不需要，后台自动生成")
    private String username;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "不需要，后台自动生成")
    private Date updatetime;

    /**
     * 图片描述
     */
    @ApiModelProperty(value = "需要")
    private String imgTitle;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "需要,调用upload接口获得")
    private String imgLink;

    /**
     * 显示状态
     */
    @ApiModelProperty(value = "需要，显示（ok）、不显示（no）")
    private String imgStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(String imgStatus) {
        this.imgStatus = imgStatus;
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
        Image other = (Image) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getImgTitle() == null ? other.getImgTitle() == null : this.getImgTitle().equals(other.getImgTitle()))
            && (this.getImgLink() == null ? other.getImgLink() == null : this.getImgLink().equals(other.getImgLink()))
            && (this.getImgStatus() == null ? other.getImgStatus() == null : this.getImgStatus().equals(other.getImgStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getImgTitle() == null) ? 0 : getImgTitle().hashCode());
        result = prime * result + ((getImgLink() == null) ? 0 : getImgLink().hashCode());
        result = prime * result + ((getImgStatus() == null) ? 0 : getImgStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", imgTitle=").append(imgTitle);
        sb.append(", imgLink=").append(imgLink);
        sb.append(", imgStatus=").append(imgStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}