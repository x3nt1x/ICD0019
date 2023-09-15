package poly.range;

import java.util.Iterator;

public class MyIterator implements Iterator<Integer>
{
    private int start;
    private final int end;

    public MyIterator(int start, int end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean hasNext()
    {
        return this.start < this.end;
    }

    @Override
    public Integer next()
    {
        return this.start++;
    }
}