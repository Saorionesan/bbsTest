package com.epoint.bbs.model.service.impl;

import com.epoint.bbs.model.entity.Article;
import com.epoint.bbs.model.entity.ResponseResult;
import com.epoint.bbs.model.entity.User;
import com.epoint.bbs.model.mapper.ArticleMapper;
import com.epoint.bbs.model.mapper.UserMapper;
import com.epoint.bbs.model.service.IArticelService;
import com.epoint.bbs.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class IArticleServiceImpl implements IArticelService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public ResponseResult<Void> addArticle(Article article) {
        ResponseResult<Void> rr=new ResponseResult<>();
        User user=userMapper.getUserByName((String) Tools.getSession().getAttribute("username"));
        if(user.getKiss()<article.getArtkiss()){
            rr.setMessage("悬赏不足 无法发帖");
            rr.setState(300);
            return  rr;
        }else {
            Article result=articleMapper.getArticleByTitle(article.getArttitle());
            if(result!=null){
                rr.setState(400);
                rr.setMessage("该标题已有，请重新编写");
                return rr;
            }else{
                //增加文章
                articleMapper.addArticle(addMess(article));
                //减少悬赏
                userMapper.lessKiss(article.getArtkiss(),user.getUserName());
                rr.setMessage("发帖成功");
                return rr;
            }
        }
    }

    //获得当前用户下所有的文章
    public ResponseResult<List<Article>> getAllArticle(){
        ResponseResult<List<Article>> rr=new ResponseResult<>();
      List<Article> list= articleMapper.getAllArticleByUserId(userMapper.getUserByName((String) Tools.getSession().getAttribute("username")).getUserId());

      rr.setData(list);
     return rr;
    }

    @Override
    public Article getArticleById(Integer artid) {
        //将查看人数加1
        articleMapper.updateView(artid);
        return articleMapper.getArticleById(artid);
    }


    //增加文章信息
    private Article addMess(Article article){
        User user=userMapper.getUserByName((String) Tools.getSession().getAttribute("username"));
        article.setArtuserid(user.getUserId());
        article.setArtcretime(getNowDate());
        article.setArtcomnum(0);
        article.setArtview(0);
        return  article;
    }
     private  String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
