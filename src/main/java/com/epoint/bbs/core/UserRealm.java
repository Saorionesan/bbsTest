package com.epoint.bbs.core;

import com.epoint.bbs.model.entity.User;
import com.epoint.bbs.model.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;
    /**
     *
     * 执行授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     *
     * 执行登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户token
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        //获取数据库用户名
        User user=userMapper.getUserByName(token.getUsername());
        if(user==null){
            //如果用户名不存在那么只要返回一个null即可 其会自动判断
            return null;
        }
        //第二个为数据库中的密码
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
