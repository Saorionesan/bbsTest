package com.epoint.bbs.model.mapper;

import com.epoint.bbs.model.entity.Collect;

import java.util.List;

public interface CollectMapper {

    //获取用户收藏的文章
    List<Collect> getAllCollects(Integer coluserid);
    //新增收藏
    Integer addCollect(Collect collect);


}
