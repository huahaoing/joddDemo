package jdoo.demo;

import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLogger;
import jodd.mail.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class EmailDemo {
    private final Logger logger= LoggerFactory.getLogger(EmailDemo.class);
    private static final String Content="这是一个Jodd各种功能演示的项目，非常多的常用方法都不需要再浪费时间去写了，帮助同学们使用好这把瑞士军刀！避免大家重复进坑！";
    static {
        LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER);
    }

    @Test
    public void sendSimpleMailTest(){
        Email email = Email.create()
                .from("huahaoing@wo.cn")
                .to("huahaoing@qq.com")
                .subject("Jodd使用教程!")
                .textMessage(Content);

        sendMail(email);
    }

    @Test
    public void sendPictureMailTest(){
        File img1=new File("D:"+File.separator+"文档"+File.separator+"工作资料"+
                File.separator+"图片"+File.separator+"id_card_img.jpg");
        File img2=new File("D:"+File.separator+"文档"+File.separator+"工作资料"+
                File.separator+"图片"+File.separator+"db4b.jpg");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<html><META http-equiv=Content-Type content='text/html;charset=utf-8'>");
        stringBuffer.append("<p><h1>Jodd使用教程</h1><h3>");
        stringBuffer.append(Content);
        stringBuffer.append("</h3></p><p><img src='cid:id_card_img.jpg'/></p>");
        stringBuffer.append("</body></html>");
        logger.info(stringBuffer.toString());

        Email email = Email.create()
                .from("huahaoing@wo.cn")
                .to("huahaoing@qq.com")
                .subject("Jodd使用教程!")
                .textMessage(Content)
                .htmlMessage(stringBuffer.toString(),"utf-8")
                .embeddedAttachment(EmailAttachment.with().content(img1))
                /*.attachment(EmailAttachment.with().content(img2))*/;

        sendMail(email);
    }

    @Test
    public void sendHtmlMailTest(){
        Email email = Email.create()
                .from("huahaoing@wo.cn")
                .to("huahaoing@qq.com")
                .subject("Jodd使用教程!")
                .htmlMessage("<p><h1>Jodd使用教程</h1><h3></h3></p>","utf-8");


        sendMail(email);
    }

    private void sendMail(Email email){
        SmtpServer smtpServer = MailServer.create()
                .host("smtp.wo.cn")
                .port(25)
                .auth("****","*****")
                .buildSmtpMailServer();
        SendMailSession session = smtpServer.createSession();
        session.open();
        Assert.assertNotNull(session.sendMail(email));
        session.close();
        logger.info("邮件发送成功...");
    }
}
