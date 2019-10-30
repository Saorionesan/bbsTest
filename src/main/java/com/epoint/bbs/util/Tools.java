package com.epoint.bbs.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


/**
 *工具类
 */
public class Tools {

    public static String getFileSuffex(String filename){
        int lastIndexOf = filename.lastIndexOf(".");
        return filename.substring(lastIndexOf + 1);
    }
    //获取session
    public static Session getSession(){
        Subject subject = SecurityUtils.getSubject();
        return subject.getSession();
    }

}
