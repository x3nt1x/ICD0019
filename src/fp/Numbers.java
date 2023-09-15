package fp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Numbers
{
    private final List<Integer> numbers = Arrays.asList(1, 3, 4, 51, 24, 5);

    @Test
    public void findsOddNumbers()
    {
        var oddNumbers = numbers.stream().filter(e -> e % 2 == 1).toList();

        System.out.println(oddNumbers);
    }

    @Test
    public void findsOddNumbersOver10()
    {
        var oddNumbers = numbers.stream().filter(e -> e > 10).filter(e -> e % 2 == 1).toList();

        System.out.println(oddNumbers);
    }

    @Test
    public void findsSquaredOddNumbers()
    {
        var oddNumbers = numbers.stream().filter(e -> e % 2 == 1).map(e -> (int) Math.pow(e, 2)).toList();

        System.out.println(oddNumbers);
    }

    @Test
    public void findsSumOfOddNumbers()
    {
        var sum = numbers.stream().filter(e -> e % 2 == 1).mapToInt(e -> e).sum();

        System.out.println(sum);
    }
}