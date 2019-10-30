package com.epoint.bbs.model.mapper;

import com.epoint.bbs.model.entity.Article;

import java.util.List;

/**
 * 文章映射类
 */
public interface ArticleMapper {
    //增加新帖子
   Integer addArticle(Article article);

   //根据标题名寻找帖子
    Article getArticleByTitle(String title);

    //根据作者id寻找文章
   List<Article> getAllArticleByUserId(Integer userid);

  Article getArticleById(Integer artid);

  Integer updateView(Integer artid);

}