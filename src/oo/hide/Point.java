package oo.hide;

import java.util.Objects;

public class Point
{
    private final int x;
    private final int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Point other)) {
            return false;
        }

        return Objects.equals(x, other.x) && Objects.equals(y, other.y);
    }
}