package oo.hide;

import org.junit.Test;

public class Runner
{
    @Test
    public void timerExample()
    {
        var timer = new Timer();

        // error when submitting with this
        // for (int i = 0; i < 1E8; i++) { }

        System.out.println(timer.getPassedTime());
    }
}