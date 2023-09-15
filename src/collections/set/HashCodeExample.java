package collections.set;

import oo.hide.Point;
import org.junit.Test;

import java.util.HashSet;

public class HashCodeExample
{
    @Test
    public void runExample()
    {
        var set = new HashSet<Point>();

        set.add(new Point(1, 1));
        set.add(new Point(1, 1));

        System.out.println(set.size());
        set.forEach(point -> System.out.println(point.hashCode()));
    }
}