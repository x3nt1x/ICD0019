package poly.fruit;

public class Apple implements Weighable
{
    private final Double weightInKiloGrams;

    public Apple(Double weight)
    {
        this.weightInKiloGrams = weight;
    }

    @Override
    public Integer getWeightInGrams()
    {
        return Math.toIntExact(Math.round(weightInKiloGrams * 1000));
    }
}