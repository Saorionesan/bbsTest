package com.epoint.bbs.model.service;

import com.epoint.bbs.model.entity.Article;
import com.epoint.bbs.model.entity.ResponseResult;

import java.util.List;

public interface IArticelService {
    ResponseResult<Void> addArticle(Article article);
    ResponseResult<List<Article>> getAllArticle();
    Article getArticleById(Integer artid);
}
