package oo.hide;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PointSetExtraTests
{
    @Test
    public void nullIsAValidElement()
    {
        var set = new PointSet();

        set.add(new Point(1, 1));
        set.add(null);
        set.add(new Point(1, 2));

        assertThat(set.toString(), is("(1, 1), null, (1, 2)"));
    }

    @Test
    public void canRemoveElements()
    {
        var set = new PointSet();

        set.add(new Point(1, 1));
        set.add(new Point(1, 2));
        set.add(new Point(1, 3));

        set.remove(new Point(1, 4));
        set.remove(new Point(1, 2));

        assertThat(set.toString(), is("(1, 1), (1, 3)"));
    }
}