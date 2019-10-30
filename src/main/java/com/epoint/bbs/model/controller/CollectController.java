package com.epoint.bbs.model.controller;

import com.epoint.bbs.model.entity.Collect;
import com.epoint.bbs.model.entity.ResponseResult;
import com.epoint.bbs.model.service.ICollectService;
import com.epoint.bbs.model.service.IUserService;
import com.epoint.bbs.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    ICollectService collectService;
    @Autowired
    IUserService userService;

    @RequestMapping("/getCollects.do")
    public ResponseResult<List<Collect>> getAllCollect(){
       ResponseResult<List<Collect>> rr=new ResponseResult<>();
       List<Collect>list=collectService.getAllCollects(userService.getUser().getUserId());
       rr.setData(list);
        return rr;
    }

    @RequestMapping("/addCollect.do")
    public ResponseResult<Void> addCollect(@RequestBody Collect collect){
        ResponseResult<Void> rr=new ResponseResult<>();
        if(collectService.addCollect(collect)!=1){
            rr.setState(300);
            rr.setMessage("收藏失败");
            return rr;
        }
        rr.setMessage("收藏成功");
        return rr;
    }
}
