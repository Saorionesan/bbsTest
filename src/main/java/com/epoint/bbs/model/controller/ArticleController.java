package com.epoint.bbs.model.controller;

import com.epoint.bbs.model.entity.Article;
import com.epoint.bbs.model.entity.ResponseResult;
import com.epoint.bbs.model.service.IArticelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    IArticelService articelService;

    //增加文章
    @RequestMapping("/addArticle.do")
    public ResponseResult<Void> add(@RequestBody Article article){
        return articelService.addArticle(article);
    }
    //根据用户id获取文章
    @RequestMapping("/getAllarticle.do")
    public ResponseResult<List<Article>> getAllArticle(){
        return articelService.getAllArticle();
    }

    @RequestMapping("/getArticle.do")
   public ResponseResult<Article>getArticleById(Integer artid){
        ResponseResult<Article> rr=new ResponseResult<>();
        rr.setData(articelService.getArticleById(artid));
        return rr;
    }

}
