package oo.hide;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class PointSetTests
{
    @Test
    public void hasStringRepresentation()
    {
        var set = new PointSet();

        set.add(new Point(1, 1));
        set.add(new Point(2, 1));
        set.add(new Point(1, 2));

        assertThat(set.toString(), is("(1, 1), (2, 1), (1, 2)"));
    }

    @Test
    public void keepsTrackOfTheSize()
    {
        var set = new PointSet();

        assertThat(set.size(), is(0));

        set.add(new Point(1, 1));

        assertThat(set.size(), is(1));

        set.add(new Point(2, 1));

        assertThat(set.size(), is(2));
    }

    @Test
    public void tellsWhetherSetContainsAPoint()
    {
        var set = new PointSet();

        set.add(new Point(1, 1));
        set.add(new Point(1, 2));

        assertTrue(set.contains(new Point(1, 1)));
        assertTrue(set.contains(new Point(1, 2)));
        assertFalse(set.contains(new Point(1, 3)));
    }

    @Test
    public void doesNotStoreDuplicatePoints()
    {
        var set = new PointSet();

        set.add(new Point(1, 1));

        assertThat(set.toString(), is("(1, 1)"));

        set.add(new Point(1, 1));

        assertThat(set.toString(), is("(1, 1)"));
    }

    @Test
    public void pointSetSupportsEqualityTesting()
    {
        assertThat(getSet(), is(getSet()));
        assertThat(getSet(new Point(1, 1)), is(not(getSet())));
        assertThat(getSet(new Point(1, 1)), is(not(getSet(new Point(1, 2)))));
        assertThat(getSet(new Point(1, 1), new Point(1, 2)), is(getSet(new Point(1, 2), new Point(1, 1))));
    }

    @Test
    public void pointSetSupportsSubtractingAnotherSet()
    {
        var a = getSet(new Point(1, 1), new Point(1, 2));
        var b = getSet(new Point(1, 1), new Point(1, 3));

        var remainder = a.subtract(b);

        assertThat(a, is(getSet(new Point(1, 1), new Point(1, 2))));
        assertThat(remainder, is(getSet(new Point(1, 2))));
    }

    @Test
    public void pointSetSupportsIntersectionOperation()
    {
        var a = getSet(new Point(1, 1), new Point(1, 2));
        var b = getSet(new Point(1, 1), new Point(1, 3));

        var intersection = a.intersect(b);

        assertThat(a, is(getSet(new Point(1, 1), new Point(1, 2))));
        assertThat(intersection, is(getSet(new Point(1, 1))));
    }

    @Test
    public void setGrowsWhenThereIsNoMoreRoom()
    {
        var set = new PointSet(2);

        set.add(new Point(1, 1));
        set.add(new Point(2, 1));

        assertThat(getInternalArray(set).length, is(2));

        set.add(new Point(3, 1));

        assertThat(getInternalArray(set).length, is(4));
    }

    private PointSet getSet(Point... points)
    {
        var set = new PointSet();

        for (Point point : points) {
            set.add(point);
        }

        return set;
    }

    @SuppressWarnings("PMD")
    private Point[] getInternalArray(PointSet set)
    {
        var fields = set.getClass().getDeclaredFields();

        var integerArrayFields = Arrays.stream(fields).filter(field -> field.getType().equals(Point[].class)).toList();

        if (integerArrayFields.isEmpty()) {
            fail("PointSet should have a field of type Point[]");
        }

        if (integerArrayFields.size() > 1) {
            fail("PointSet should have just one field of type Point[]");
        }

        integerArrayFields.get(0).setAccessible(true);

        try {
            return (Point[]) integerArrayFields.get(0).get(set);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}