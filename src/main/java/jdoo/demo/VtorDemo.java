package jdoo.demo;

import jdoo.demo.vo.User;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLogger;
import jodd.madvoc.meta.In;
import jodd.vtor.Check;
import jodd.vtor.ValidationContext;
import jodd.vtor.Violation;
import jodd.vtor.Vtor;
import jodd.vtor.constraint.MaxConstraint;
import jodd.vtor.constraint.MinLengthConstraint;
import jodd.vtor.constraint.RangeConstraint;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class VtorDemo {
    private Logger logger= LoggerFactory.getLogger(VtorDemo.class);
    static {
        LoggerFactory.setLoggerProvider(SimpleLogger.PROVIDER);
    }

    @Test
    public void progameTest(){
        ValidationContext vctx=new ValidationContext();
        vctx.add(new Check("userName",new MinLengthConstraint(2)));
        Check maxCheck=new Check("id", new MaxConstraint(10));
        maxCheck.setMessage("超过最大规定数");
        Check rangeCheck=new Check("id", new RangeConstraint(5,10));
        rangeCheck.setMessage("不在正确的范围内");
        vctx.add(rangeCheck);
        vctx.add(maxCheck);

        Vtor vtor = new Vtor();
        User user=new User(Integer.valueOf(11),"q");
        List<Violation> vlist =vtor.validate(vctx,user);
        Assert.assertNull(vlist);

    }

    @Test
    public void test(){
        Vtor vtor = new Vtor();
        User user=new User(Integer.valueOf(11),"q黑科技会kj2h4kj2h34kj2hj43h2kj3h4kj");
        List<Violation> vlist =vtor.validate(user);
        Assert.assertNull(vlist);
    }
}
