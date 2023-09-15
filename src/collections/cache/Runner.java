package collections.cache;

import oo.hide.Timer;
import org.junit.Test;

public class Runner
{
    @Test
    public void calculatesFibonacciOfN()
    {
        var fib = new Fibonacci();
        var timer = new Timer();

        fib.fib(40);

        System.out.println(timer.getPassedTime());
    }
}