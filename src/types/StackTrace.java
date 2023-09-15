package types;

public class StackTrace
{
    public static void main(String[] args)
    {
        calculatePrice();
    }

    public static Double calculatePrice()
    {
        var basePrice = calculateBasePrice();

        return basePrice * (1 + 0.2);
    }

    public static Double calculateBasePrice()
    {
        var profitConstant = readProfitConstant();
        if (profitConstant == null) {
            return 0.0;
        }

        // some complex calculation that produces 100 as netCost
        var netCost = 100D;

        return netCost + (0.1 * profitConstant * netCost);
    }

    public static Integer readProfitConstant()
    {
        // some code that produces null
        Integer result = null;

        return result;
    }
}