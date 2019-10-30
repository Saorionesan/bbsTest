package com.epoint.bbs.model.mapper;

import com.epoint.bbs.model.entity.Admin;
import com.epoint.bbs.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 用户以及管理员mapper
 */
@Mapper
public interface UserMapper {

    @Select("select * from admin where adminname=#{name}")
    Admin getAdminByName(@Param("name") String name);


    List<User> getAllUsers();

    //增加用户
    Integer addUser(User user);

    //根据用户名搜寻用户
    User getUserByName(String username);

    Integer updateUser(User user);

    Integer addKiss(String username);

    //减少kiss
    //@Param 注解的值和xml文件中对应的
    Integer lessKiss(@Param("kiss") Integer kiss,@Param("username") String username);

    //更新签到时间
    Integer signTime(@Param("username") String username,@Param("signtime") Date signtime);

    //获取作者信息
    User getAuthor(@Param("artid") Integer artid);

}
