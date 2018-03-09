package com.ctt.jdoo.demo;

import jodd.cache.LRUCache;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLogger;
import org.junit.Test;

public class LRUCacheDemo {
    private final Logger logger= LoggerFactory.getLogger(LRUCacheDemo.class);
    static {
        LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER);
    }

    /*
    最近没有访问淘汰测试
     */
    @Test
    public void lruTest(){
        LRUCache lruCache=new LRUCache(10);
        for(int i=0,n=10;i<n;i++){
            lruCache.put(String.format("index-%d",i),Integer.valueOf(i));
        }
        for(int i=5,n=10;i<n;i++){
            lruCache.get(String.format("index-%d",i));
        }
        for(int i=10,n=15;i<n;i++){
            lruCache.put(String.format("index-%d",i),Integer.valueOf(i));
        }
        for(int i=0,n=15;i<n;i++){
            if(lruCache.get(String.format("index-%d",i))!=null){
                logger.info(lruCache.get(String.format("index-%d",i)).toString());
            }
        }

    }
}
