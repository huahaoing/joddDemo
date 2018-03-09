package jdoo.demo;

import jodd.http.Cookie;
import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLogger;
import org.junit.Test;

public class HttpClientDemo {
    private Logger logger= LoggerFactory.getLogger(HttpClientDemo.class);
    static {
        LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER);
    }

    @Test
    public void simpleHttpTest(){
        HttpRequest httpRequest = HttpRequest.get("http://www.baidu.com");
        HttpResponse response = httpRequest.accept("utf-8").send();
        logger.info(response.bodyText());
    }

    @Test
    public void builderHttpTest(){
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.host("www.baidu.com")
                .port(80)
                .protocol("http")
                .path("s")
                .method("GET")
                .contentType("text/html;charset=utf-8")
                .form("ie", "utf-8").form("f","8")
                .form("rsv_idx","1")
                .form("tn","baidu")
                .form("wd","fifo");
        HttpResponse response=httpRequest.send();
        logger.info(response.bodyText());
    }

    @Test
    public void HttpBrowserTest(){
        HttpBrowser httpBrowser=new HttpBrowser();
        HttpRequest httpRequest=HttpRequest.get("www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=fifo");
        httpRequest.cookies(new Cookie("userId","10194"));
        httpBrowser.sendRequest(httpRequest);

        logger.info(httpBrowser.getPage());
    }
}
