package com.epoint.bbs.model.controller;

import com.epoint.bbs.config.BbsConfig;
import com.epoint.bbs.model.entity.ResponseResult;
import com.epoint.bbs.model.entity.User;
import com.epoint.bbs.model.service.IUserService;
import com.epoint.bbs.util.Tools;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    BbsConfig bbsConfig;

    //注册
   @RequestMapping("/register.do")
    public ResponseResult<String> register( @RequestBody User user){
       ResponseResult<String> rr=new ResponseResult<>();
      Integer status=userService.addUser(user);
       if(status==1){
           rr.setMessage("注册成功，即将跳转登录");
           return rr;
       }else {
           rr.setState(300);
           rr.setMessage("该用户名已存在，请重新输入");
           return rr;
       }
   }

   //登录
   @RequestMapping("/login.do")
    public ResponseResult<Void> login(@RequestBody User user){
       return userService.login(user);
   }

   //更新
   @RequestMapping("/update.do")
    public ResponseResult<String> update(@RequestBody User user){
         ResponseResult<String> rr=new ResponseResult<>();
         if(userService.update(user)==1){
             rr.setMessage("修改成功");
             return rr;
         }
         rr.setMessage("修改失败");
         return  rr;
   }
   //头像上传
    @PostMapping("/upload.do")
     public ResponseResult<String> upload(@RequestParam("file")MultipartFile multipartFile,HttpSession session){
       ResponseResult<String> rr=new ResponseResult<>();
       if(!multipartFile.isEmpty()){
          //获取文件名
           String picture= UUID.randomUUID().toString()+"."+ Tools.getFileSuffex(multipartFile.getOriginalFilename());
           //保存文件
           try {
               String savePath=bbsConfig.getFileUploadPath();
               multipartFile.transferTo(new File(savePath+picture));
               //保存地址
               User user=new User();
               user.setImg(savePath+"/"+picture);
               userService.update(user);
               rr.setMessage("上传成功");
               return rr;
           } catch (IOException e) {
               e.printStackTrace();
               rr.setState(500);
               rr.setMessage("上传失败");
               return rr;
           }
       }
       rr.setState(400);
       rr.setMessage("上传文件为空");
       return rr;
    }

    //获取用户信息
    @RequestMapping("/getUser.do")
    public ResponseResult<User> getUser(){
       ResponseResult<User> rr=new ResponseResult<>();
       User user=userService.getUser();
         rr.setData(user);
       return rr;
    }


    //修改密码
    @RequestMapping("/updatePass.do")
    public ResponseResult<Void> updatePass( @RequestParam("nowpass") String nowpass,@RequestParam("newpass") String newpass){
        System.out.println(nowpass+":::"+newpass);
     return userService.updatePass(nowpass,newpass);
    }
    //登出
    @RequestMapping("/loginout.do")
    public ResponseResult<Void> loginout(){
        System.out.println("开始退出");
        ResponseResult<Void> rr=new ResponseResult<>();
        SecurityUtils.getSubject().logout();
        rr.setMessage("用户已退出");
        return rr;
    }
    //签到
    @RequestMapping("/sign.do")
    public ResponseResult<Void> sign(){
        return  userService.addKiss((String) Tools.getSession().getAttribute("username"));
    }
    //获取对应的作者信息
    @RequestMapping("/getAuthor.do")
    public ResponseResult<User> getAuthor(Integer artid){
       return userService.getAuthor(artid);
    }
}
