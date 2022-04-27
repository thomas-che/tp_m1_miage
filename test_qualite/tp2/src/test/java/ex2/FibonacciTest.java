package ex2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FibonacciTest {
    private Fibonacci fibonacci;

    @Before
    public void setUp(){
        this.fibonacci = new Fibonacci();
    }

    @Test
    public void suiteTest(){
        int res = fibonacci.suite(8);
        Assert.assertEquals("Suite Fib(8)", 13, res);
    }

    @Test
    public void suiteTest0(){
        int res = fibonacci.suite(0);
        Assert.assertEquals("Suite Fib(0)", 0, res);
    }

    @Test
    public void suiteTest1(){
        int res = fibonacci.suite(1);
        Assert.assertEquals("Suite Fib(8)", 1, res);
    }
}