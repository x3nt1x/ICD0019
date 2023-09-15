package oo.hide;

public class PointSet
{
    private Point[] points;
    private int nextPosition;

    public PointSet(int capacity)
    {
        this.points = new Point[capacity];
        this.nextPosition = 0;
    }

    public PointSet()
    {
        this(10);
    }

    public Point[] increaseSize()
    {
        var newArray = new Point[points.length * 2];

        for (var i = 0; i < size(); i++) {
            newArray[i] = points[i];
        }

        return newArray;
    }

    public void add(Point point)
    {
        if (contains(point)) {
            return;
        }

        if (size() >= points.length) {
            points = increaseSize();
        }

        points[nextPosition] = point;

        nextPosition++;
    }

    public int size()
    {
        return nextPosition;
    }

    public boolean contains(Point point)
    {
        for (var i = 0; i < size(); i++)
        {
            if (points[i] == null)
            {
                if (point == null) {
                    return true;
                }
                else {
                    continue;
                }
            }

            if (points[i].equals(point)) {
                return true;
            }
        }

        return false;
    }

    public PointSet subtract(PointSet other)
    {
        var pointSet = new PointSet();

        for (var i = 0; i < size(); i++)
        {
            if (!other.contains(points[i])) {
                pointSet.add(points[i]);
            }
        }

        return pointSet;
    }

    public PointSet intersect(PointSet other)
    {
        var pointSet = new PointSet();

        for (int i = 0; i < size(); i++)
        {
            if (other.contains(points[i])) {
                pointSet.add(points[i]);
            }
        }

        return pointSet;
    }

    public void remove(Point point)
    {
        var pointSet = new PointSet();

        for (var i = 0; i < size(); i++)
        {
            if (points[i] != null && points[i].equals(point)) {
                continue;
            }

            pointSet.add(points[i]);
        }

        points = pointSet.points;
        nextPosition = pointSet.nextPosition;
    }

    @Override
    public String toString()
    {
        var pointsAsStrings = new String[size()];

        for (var i = 0; i < size(); i++) {
            pointsAsStrings[i] = points[i] == null ? "null" : points[i].toString();
        }

        return String.join(", ", pointsAsStrings);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof PointSet set)) {
            return false;
        }

        for (var i = 0; i < size(); i++)
        {
            if (!set.contains(points[i])) {
                return false;
            }
        }

        return true;
    }
}