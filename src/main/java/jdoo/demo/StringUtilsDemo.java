package jdoo.demo;

import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLogger;
import jodd.util.CharArraySequence;
import jodd.util.StringUtil;
import org.junit.Test;

public class StringUtilsDemo {
    private Logger logger= LoggerFactory.getLogger(StringUtilsDemo.class);
    private static final String sequence="abcdef    ghijk\blmnopqrs\\ntuvwxyz12345   6789!@#$%^&*()";
    static {
        LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER);
    }
    @Test
    public void stringUtilTest(){
        logger.info(StringUtil.reverse(sequence));
        logger.info(StringUtil.remove(sequence,"123"));
        //首字母大写
        logger.info(StringUtil.capitalize(sequence));
        //字符集转换
        logger.info(StringUtil.convertCharset("Java1.5提供了一个非常高效实用的多线程包","utf-8","GBK"));

        logger.info(StringUtil.convertTabsToSpaces(sequence,2));

        logger.info(StringUtil.escapeJava(sequence));

        logger.info(StringUtil.title(sequence));
    }
}
