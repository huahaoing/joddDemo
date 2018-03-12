package jdoo.demo;

import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLogger;
import jodd.util.buffer.FastBuffer;
import org.junit.Test;

public class FastbufferDemo {
    private Logger logger= LoggerFactory.getLogger(FastbufferDemo.class);
    static {
        LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER);
    }

    /*
    超过一万次，fastBuffer明显快不少
     */
    @Test
    public void fastBufferTest(){
        long start=System.currentTimeMillis();
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=0,n=10000000;i<n;i++){
            stringBuffer.append("werwegdfgdfg34tgd");
        }
        logger.info(String.format("StringBuffer cost time:%d",System.currentTimeMillis()-start));

        start=System.currentTimeMillis();
        FastBuffer<String> fastBuffer=new FastBuffer();
        for(int i=0,n=10000000;i<n;i++){
            fastBuffer.append("oiuoiu45oi2j43lkjlrew");
        }
        logger.info(String.format("fastBuffer cost time:%d",System.currentTimeMillis()-start));

    }
}
