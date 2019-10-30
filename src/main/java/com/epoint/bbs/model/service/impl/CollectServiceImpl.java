package com.epoint.bbs.model.service.impl;

import com.epoint.bbs.model.entity.Collect;
import com.epoint.bbs.model.mapper.CollectMapper;
import com.epoint.bbs.model.mapper.UserMapper;
import com.epoint.bbs.model.service.ICollectService;
import com.epoint.bbs.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class CollectServiceImpl implements ICollectService {

      @Autowired
    CollectMapper collectMapper;
      @Autowired
    UserMapper userMapper;
    @Override
    public List<Collect> getAllCollects(Integer coluserid) {
        return collectMapper.getAllCollects(coluserid);
    }

    @Override
    public Integer addCollect(Collect collect) {
        //新增用户信息
       Integer result= collectMapper.addCollect(addMess(collect));
        return result;
    }

    private Collect addMess(Collect collect){
        //完善信息
        collect.setColuserid(userMapper.getUserByName((String) Tools.getSession().getAttribute("username")).getUserId());
        collect.setColtime(getNowDate());
        return collect;
    }
    private  String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


}
