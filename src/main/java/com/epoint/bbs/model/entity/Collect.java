package com.epoint.bbs.model.entity;

/**
 * 收藏文章id
 */
public class Collect {
    //收藏id
    private Integer colid;
    //收藏文章的id
    private Integer colartid;
    //收藏用户id
    private Integer coluserid;
    //文章标题
    private String  colarttitle;
    //收藏时间
    private String coltime;

    public Integer getColid() {
        return colid;
    }

    public void setColid(Integer colid) {
        this.colid = colid;
    }

    public Integer getColartid() {
        return colartid;
    }

    public void setColartid(Integer colartid) {
        this.colartid = colartid;
    }

    public Integer getColuserid() {
        return coluserid;
    }

    public void setColuserid(Integer coluserid) {
        this.coluserid = coluserid;
    }

    public String getColarttitle() {
        return colarttitle;
    }
    public void setColarttitle(String colarttitle) {
        this.colarttitle = colarttitle;
    }
    public String getColtime() {
        return coltime;
    }
    public void setColtime(String coltime) {
        this.coltime = coltime;
    }
}
