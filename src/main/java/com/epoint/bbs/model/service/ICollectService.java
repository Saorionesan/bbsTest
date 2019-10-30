package com.epoint.bbs.model.service;

import com.epoint.bbs.model.entity.Collect;

import java.util.List;

public interface ICollectService {
    //获取当前用户所有收藏的文章
    List<Collect> getAllCollects(Integer coluserid);

    //添加收藏
    Integer addCollect(Collect collect);
}
