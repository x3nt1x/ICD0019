package fp;

import org.junit.Test;

public class Timer
{
    @Test
    public void measuresExecutionTime()
    {
        executeAndTime(this::methodToBeTimed);
    }

    private void executeAndTime(Runnable code)
    {
        long start = System.currentTimeMillis();

        code.run();

        double passedMills = System.currentTimeMillis() - start;
        String result = String.format("%s sek", passedMills / 1000);
        System.out.println(result);
    }

    private void methodToBeTimed()
    {
        for (int i = 0; i < 1e9; i++) { }
    }
}