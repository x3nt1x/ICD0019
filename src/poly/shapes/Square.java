package poly.shapes;

public class Square implements Shape
{
    private final int side;

    public Square(int side)
    {
        this.side = side;
    }

    public int getSide()
    {
        return side;
    }

    @Override
    public double getArea()
    {
        return Math.pow(this.getSide(), 2);
    }
}