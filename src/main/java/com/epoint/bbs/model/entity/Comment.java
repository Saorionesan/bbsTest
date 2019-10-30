package com.epoint.bbs.model.entity;

/**
 * 评论表
 */
public class Comment {
    private Integer comid;
    private String comcontent;
    private Integer comartid;
    private Integer comuserid;
    private Integer comtime;

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public String getComcontent() {
        return comcontent;
    }

    public void setComcontent(String comcontent) {
        this.comcontent = comcontent;
    }

    public Integer getComartid() {
        return comartid;
    }

    public void setComartid(Integer comartid) {
        this.comartid = comartid;
    }

    public Integer getComuserid() {
        return comuserid;
    }

    public void setComuserid(Integer comuserid) {
        this.comuserid = comuserid;
    }

    public Integer getComtime() {
        return comtime;
    }

    public void setComtime(Integer comtime) {
        this.comtime = comtime;
    }
}
