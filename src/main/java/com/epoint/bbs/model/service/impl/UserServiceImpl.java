package com.epoint.bbs.model.service.impl;

import com.epoint.bbs.model.entity.ResponseResult;
import com.epoint.bbs.model.entity.User;
import com.epoint.bbs.model.mapper.UserMapper;
import com.epoint.bbs.model.service.IUserService;
import com.epoint.bbs.util.Tools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public Integer addUser(User user) {
        /**
         * 判断用户名是否存在
         */
        User result=userMapper.getUserByName(user.getUserName());
        if(result!=null){
            return  0;
        }else {
            return userMapper.addUser(newuser(user));
        }
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public ResponseResult<Void> login(User user) {
        ResponseResult<Void> rr = new ResponseResult<>();
        /**
         * shiro 执行认证
         */
        //获取subject
        Subject currentUser = SecurityUtils.getSubject();
        //封装用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        //执行登录
        try {
            //登录成功
            currentUser.login(token);
            Tools.getSession().setAttribute("username",user.getUserName());
            rr.setMessage("登录成功");
            return  rr;
        } catch (UnknownAccountException e) {
            //用户名不存在
            rr.setState(500);
            rr.setMessage("用户名不存在,请先注册");
            return rr;
        } catch (IncorrectCredentialsException e) {
            rr.setState(500);
            rr.setMessage("密码错误");
            return rr;
        }
    }

    @Override
    public Integer update(User user) {
        user.setUserName((String)Tools.getSession().getAttribute("username"));
        return userMapper.updateUser(user);
    }

    @Override
    public User getUser() {
        //根据session中的用户名得到用户数据
        return userMapper.getUserByName((String) Tools.getSession().getAttribute("username"));
    }

    @Override
    public ResponseResult<Void> updatePass(String pass, String newPass) {
        ResponseResult<Void> rr=new ResponseResult<>();
        //判断密码是否正确 正确即修改
        User user=userMapper.getUserByName((String)Tools.getSession().getAttribute("username"));
        if(!pass.equals(user.getPassword())){
            rr.setMessage("原密码不正确，无法修改");
            return rr;
        }
        user.setPassword(newPass);
        userMapper.updateUser(user);
        rr.setMessage("修改成功，下次请用新密码登录");
        return rr;
    }

    @Override
    public ResponseResult<Void> addKiss(String username) {
        ResponseResult<Void> rr=new ResponseResult<>();
        rr.setMessage("签到成功");
        /**
         *先判断是否第一次签到 否则比较其签到时间 如果小于24小时则不能签到 否则可以签到
         */
        User result=userMapper.getUserByName(username);
        Date old=result.getSigntime();
        Date now=new Date();
        if(old==null) {
             //直接更新时间
            userMapper.signTime(username,now);
            return rr;
        }else {
            long between = now.getTime() - old.getTime();
            //判断是否大于24小时
            if (between > (24 * 3600000)) {
                //可以签到
                userMapper.signTime(username, now);
                userMapper.addKiss(username);
                return rr;
            }
            rr.setMessage("签到间隔不大于24小时 无法签到");
            return rr;
        }
    }

    @Override
    public ResponseResult<User> getAuthor(Integer aid) {
        ResponseResult<User> rr=new ResponseResult<>();
        rr.setData(userMapper.getAuthor(aid));
        return rr;
    }


    //增加用户属性
    private User newuser(User user){
        user.setBlog("");
        user.setConcern(0);
        user.setFans(0);
        user.setImg("");
        user.setPhone("");
        user.setSex(0);
        // 1代表启用 0代表禁用
        user.setStatus(1);
        user.setIntroduction("");
        user.setKiss(10);
        return user;
    }
}
