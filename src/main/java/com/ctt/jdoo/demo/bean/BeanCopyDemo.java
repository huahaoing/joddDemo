package com.ctt.jdoo.demo.bean;

import com.ctt.jdoo.demo.bean.vo.Group;
import com.ctt.jdoo.demo.bean.vo.User;
import jodd.bean.BeanCopy;
import jodd.bean.BeanUtil;
import jodd.json.JsonSerializer;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLoggerProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BeanCopyDemo {
    private Logger logger= LoggerFactory.getLogger(BeanCopyDemo.class);

    @Before
    public void init(){

    }
    @Test
    public void beansTest(){
        Group group=new Group();
        BeanUtil.pojo.setProperty(group,"id",Integer.valueOf(1));
        BeanUtil.pojo.setProperty(group,"groupName","group1");
        List<User> userList=new ArrayList<User>();
        userList.add(new User(Integer.valueOf(1),"user1"));
        BeanUtil.pojo.setProperty(group,"userList",userList);
        Group destGroup=null;
        BeanCopy.beans(group,destGroup);
        logger.info(JsonSerializer.create().serialize(destGroup));

    }

}
