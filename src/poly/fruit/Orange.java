package poly.fruit;

public class Orange implements Weighable
{
    private final int weightInGrams;

    public Orange(int weight)
    {
        weightInGrams = weight;
    }

    @Override
    public Integer getWeightInGrams()
    {
        return weightInGrams;
    }
}