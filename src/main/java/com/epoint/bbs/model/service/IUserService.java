package com.epoint.bbs.model.service;

import com.epoint.bbs.model.entity.ResponseResult;
import com.epoint.bbs.model.entity.User;

import javax.servlet.http.HttpSession;

public interface IUserService {
    //新增用户
    Integer addUser(User user);
   //登录
    ResponseResult<Void> login(User user);

    //修改
    Integer update(User user);

    //获取用户
    User getUser();

    //修改密码
    ResponseResult<Void> updatePass(String pass,String newPass);

    //增加kiss
    ResponseResult<Void> addKiss(String username);


    //根据文章id获取作者信息

    ResponseResult<User> getAuthor(Integer aid);
}
