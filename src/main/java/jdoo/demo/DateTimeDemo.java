package jdoo.demo;

import jodd.datetime.JDateTime;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLogger;
import org.junit.Test;

public class DateTimeDemo {
    private final Logger logger= LoggerFactory.getLogger(DateTimeDemo.class);
    static {
        LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER);
    }

    @Test
    public void newDatetimeTest(){
        JDateTime jdt = new JDateTime(); // current date and time
        logger.info(jdt.getFormat());
        logger.info(jdt.toString());
        logger.info(jdt.toString("MM/DD/YYYY hh:mm:ss"));

        jdt = new JDateTime(2018, 2, 30);// 21st December 2012, midnight
        logger.info(jdt.toString());
        // 22st December 2018, 22:54:22.124
        jdt.set(2018, 3, 21, 22, 54, 22, 124);
        logger.info(jdt.toString());

        jdt.set(2012, 12, 21);                   // 21st December 2012, midnight
        jdt.setDate(2012, 12, 21);               // change just date to 21st Dec. 2012
        jdt.setCurrentTime();                    // set current date and time
        jdt.setYear(1973);                       // change the year
        jdt.setHour(22);                         // change the hour
        jdt.setTime(18, 0,12, 853);

        jdt = new JDateTime(System.currentTimeMillis());// current date and time

        jdt = new JDateTime("2018-05-21 11:54:22.124");
        jdt = new JDateTime("12/21/2012", "MM/DD/YYYY");
        logger.info(jdt.toString());

        jdt.setCurrentTime();
        logger.info(String.format("%d",jdt.getFirstDayOfWeek()));
    }

    @Test
    public void addDatetimeTest(){
        JDateTime jdt = new JDateTime();
        jdt.add(3,53,100);
        logger.info(jdt.toString());

        jdt.add(0,0,100);
        logger.info(jdt.toString());

        jdt.setCurrentTime();
        jdt.addTime(1,1,1);
        logger.info(jdt.toString());

    }

    @Test
    public void subDatetimeTest(){
        JDateTime jdt = new JDateTime();
        jdt.sub(3,53,100);
        logger.info(jdt.toString());

        jdt.sub(0,0,100);
        logger.info(jdt.toString());

        jdt.setCurrentTime();
        jdt.subTime(1,1,1);
        logger.info(jdt.toString());

    }

    @Test
    public void betwenDatetimeTest(){
        JDateTime jdt1 = new JDateTime();
        jdt1.add(3,53,100);
        logger.info(jdt1.toString());

        JDateTime jdt2 = new JDateTime();
        jdt2.sub(0,0,20);
        logger.info(jdt2.toString());

        logger.info(String.format("相差天数:%d",jdt1.daysBetween(jdt2)));

        logger.info(String.format("是否在给定的日期之前:%s",jdt1.isBefore(jdt2)));
        logger.info(String.format("是否在给定的日期之后:%s",jdt1.isAfter(jdt2)));
        logger.info(String.format("是否在给定的日期之前:%s",jdt1.isBeforeDate(jdt2)));
        logger.info(String.format("是否在给定的日期之后:%s",jdt1.isAfterDate(jdt2)));

        logger.info(String.format("是否是闰年:%s",jdt1.isLeapYear()));
        logger.info(String.format("是否是闰年:%s",jdt2.isLeapYear()));

    }

}
