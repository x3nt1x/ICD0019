package oo.hide;

public class Counter
{
    private final int start;
    private final int step;
    private Integer current;

    public Counter(int start, int step)
    {
        this.start = start;
        this.step = step;
        this.current = null;
    }

    public int nextValue()
    {
        if (current == null) {
            return current = start;
        }

        return current += step;
    }
}