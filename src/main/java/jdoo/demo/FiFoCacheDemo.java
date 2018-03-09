package jdoo.demo;

import jodd.cache.FIFOCache;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLogger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FiFoCacheDemo {
    private final Logger logger= LoggerFactory.getLogger(FiFoCacheDemo.class);
    static {
        LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER);
    }

    /*
    先进先出缓存测试
     */
    @Test
    public void fifoCacheTest(){
        FIFOCache fifoCache=new FIFOCache(10000,1000);
        for(int i=0,n=15000;i<n;i++){
            fifoCache.put(String.format("queue%d",i),Integer.valueOf(i*84552));
        }
        logger.info(fifoCache.get("queue14900").toString());
        fifoCache.remove("queue11");
        logger.info(String.format("cache size:%d",fifoCache.size()));
        logger.info(String.format("cache limit size:%d",fifoCache.limit()));

        try {
            Thread.sleep(2000);
            fifoCache.prune();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(String.format("cache size:%d",fifoCache.size()));

    }

    /*
    验证多线程下，对队列操作，会不会出现死锁
     */
    @Test
    public void ThreadBatchTest(){
        final FIFOCache fifoCache=new FIFOCache(200);
        CachePutThread cachePutThread=null;
        CacheRemoveThread cacheRemoveThread=null;
        List<Runnable> list=new ArrayList<>(200);

        for(int i=0,n=100;i<n;i++){
            cachePutThread=new CachePutThread(fifoCache,Integer.valueOf(i));
            list.add(cachePutThread);
            cacheRemoveThread=new CacheRemoveThread(fifoCache, Integer.valueOf(i));
            list.add(cacheRemoveThread);
        }

        for(int i=0,n=list.size();i<n;i++){
            list.get(i).run();
        }
        logger.info(String.format("cache size:%d",fifoCache.size()));
    }

    class CachePutThread implements Runnable{
        private FIFOCache cache;
        private Integer index;

        public CachePutThread(FIFOCache cache, Integer index) {
            this.cache = cache;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cache.put(String.format("queue%d",index),Integer.valueOf(1));
            logger.info(String.format("thread-%s-cache size:%d",index,cache.size()));
        }
    }
    class CacheRemoveThread implements Runnable{
        private FIFOCache cache;
        private Integer index;

        public CacheRemoveThread(FIFOCache cache, Integer index) {
            this.cache = cache;
            this.index = index;
        }

        @Override
        public void run() {
            cache.remove(String.format("queue%d",index));
            logger.info(String.format("thread-%s-cache size:%d",index,cache.size()));
        }
    }
}
