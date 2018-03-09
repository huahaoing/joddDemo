package com.ctt.jdoo.demo;

import jodd.cache.LFUCache;
import jodd.cache.LRUCache;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLogger;
import org.junit.Test;

public class LFUCacheDemo {
    private final Logger logger= LoggerFactory.getLogger(LFUCacheDemo.class);
    static {
        LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER);
    }

    /*
    访问最少淘汰测试
     */
    @Test
    public void lfuTest(){
        LFUCache lfuCache=new LFUCache(10);
        for(int i=0,n=10;i<n;i++){
            lfuCache.put(String.format("index-%d",i),Integer.valueOf(i));
        }
        for(int i=0,n=5;i<n;i++){
            lfuCache.get(String.format("index-%d",i));
            lfuCache.get(String.format("index-%d",i));
            lfuCache.get(String.format("index-%d",i));
        }
        for(int i=5,n=10;i<n;i++){
            lfuCache.get(String.format("index-%d",i));
            lfuCache.get(String.format("index-%d",i));
        }

        for(int i=10,n=15;i<n;i++){
            lfuCache.put(String.format("index-%d",i),Integer.valueOf(i));
        }
        for(int i=0,n=15;i<n;i++){
            if(lfuCache.get(String.format("index-%d",i))!=null){
                logger.info(lfuCache.get(String.format("index-%d",i)).toString());
            }
        }

    }
}
