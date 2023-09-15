package poly.shapes;

public class Circle implements Shape
{
    private final int radius;

    public Circle(int radius)
    {
        this.radius = radius;
    }

    public int getRadius()
    {
        return radius;
    }

    @Override
    public double getArea()
    {
        return Math.PI * Math.pow(this.getRadius(), 2);
    }
}