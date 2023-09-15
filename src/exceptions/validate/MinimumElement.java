package exceptions.validate;

public class MinimumElement
{
    public static Integer minimumElement(int[] integers)
    {
        if (integers == null || integers.length == 0) {
            throw new IllegalArgumentException();
        }

        var minimumElement = integers[0];

        for (var current : integers)
        {
            if (current < minimumElement) {
                minimumElement = current;
            }
        }

        return minimumElement;
    }
}