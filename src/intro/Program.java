package intro;

public class Program
{
    public static void main(String[] args)
    {
        var decimal = asDecimal("11001101");
        var string = asString(205);

        System.out.println(decimal); // 205
        System.out.println(string); // 11001101
    }

    public static String asString(int input)
    {
        var result = "";

        for (var i = 8; i >= 0; i--)
        {
            var power = pow(2, i);

            if (power > input)
            {
                if (!result.isEmpty())
                    result += '0';

                continue;
            }

            result += '1';

            input -= power;
        }

        return result;
    }

    public static int asDecimal(String input)
    {
        var result = 0;
        var length = input.length() - 1;

        for (var character : input.toCharArray())
        {
            result += Character.getNumericValue(character) * pow(2, length);

            length--;
        }

        return result;
    }

    private static int pow(int arg, int power)
    {
        if (power == 0)
            return 1;

        var result = arg;

        for (var i = 1; i < power; i++)
            result *= arg;

        return result;
    }
}