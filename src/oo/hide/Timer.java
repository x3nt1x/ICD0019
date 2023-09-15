package oo.hide;

public class Timer
{
    private final double start = System.currentTimeMillis();

    public String getPassedTime()
    {
        return String.format("%fs", (System.currentTimeMillis() - start) / 1000.0);
    }
}