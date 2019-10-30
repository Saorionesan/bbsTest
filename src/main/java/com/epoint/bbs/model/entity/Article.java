package com.epoint.bbs.model.entity;

import java.util.Date;

public class Article {
    //文章id
    private Integer artid;
    //用户id
    private Integer artuserid;
    //标题
    private String arttitle;
    //文章类型名
    private String arttypename;
    //文章内容
    private String artcontent;
    //文章创建时间
    private String artcretime;
    //查看次数
    private Integer artview;
    //评论次数
    private Integer artcomnum;
    //悬赏
    private Integer artkiss;

    public Integer getArtid() {
        return artid;
    }

    public void setArtid(Integer artid) {
        this.artid = artid;
    }

    public Integer getArtuserid() {
        return artuserid;
    }

    public void setArtuserid(Integer artuserid) {
        this.artuserid = artuserid;
    }

    public String getArttitle() {
        return arttitle;
    }

    public void setArttitle(String arttitle) {
        this.arttitle = arttitle;
    }

    public String getArttypename() {
        return arttypename;
    }

    public void setArttypename(String arttypename) {
        this.arttypename = arttypename;
    }

    public String getArtcontent() {
        return artcontent;
    }

    public void setArtcontent(String artcontent) {
        this.artcontent = artcontent;
    }

    public String getArtcretime() {
        return artcretime;
    }

    public void setArtcretime(String artcretime) {
        this.artcretime = artcretime;
    }

    public Integer getArtview() {
        return artview;
    }

    public void setArtview(Integer artview) {
        this.artview = artview;
    }

    public Integer getArtcomnum() {
        return artcomnum;
    }

    public void setArtcomnum(Integer artcomnum) {
        this.artcomnum = artcomnum;
    }

    public Integer getArtkiss() {
        return artkiss;
    }

    public void setArtkiss(Integer artkiss) {
        this.artkiss = artkiss;
    }

    @Override
    public String toString() {
        return "Article{" +
                "artid=" + artid +
                ", artuserid=" + artuserid +
                ", arttitle='" + arttitle + '\'' +
                ", arttypename='" + arttypename + '\'' +
                ", artcontent='" + artcontent + '\'' +
                ", artcretime=" + artcretime +
                ", artview=" + artview +
                ", artcomnum=" + artcomnum +
                ", artkiss=" + artkiss +
                '}';
    }
}
