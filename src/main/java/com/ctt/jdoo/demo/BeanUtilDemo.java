package com.ctt.jdoo.demo;

import com.ctt.jdoo.demo.vo.Group;
import com.ctt.jdoo.demo.vo.User;
import jodd.bean.BeanCopy;
import jodd.bean.BeanUtil;
import jodd.json.JsonSerializer;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLogger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BeanUtilDemo {
    private Logger logger=LoggerFactory.getLogger(BeanUtilDemo.class);
    static {
        LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER);
    }

    @Test
    public void beansCopyTest(){
        Group group=new Group();
        BeanUtil.declared.setProperty(group,"id",Integer.valueOf(1));
        BeanUtil.declared.setProperty(group,"groupName","group1");

        List<User> userList=new ArrayList<User>(10);
        userList.add(new User(Integer.valueOf(1),"user1"));
        userList.add(new User(Integer.valueOf(2),"user2"));
        //group.setUserList(userList);
        logger.info(""+BeanUtil.declared.hasProperty(group,"userList"));

        BeanUtil.declared.setProperty(group,"userList",userList);
        User user=new User(Integer.valueOf(3),"user3");
        BeanUtil.declaredForced.setProperty(group,"userList[3]",user);

        BeanUtil.declaredForced.setProperty(group,"userList[3].userName","userNew");

        Group destGroup=new Group();
        BeanCopy.beans(group,destGroup).declared(true).copy();

        logger.info(group.toString());
        logger.info(JsonSerializer.create().deep(true).serialize(destGroup));

    }

}
