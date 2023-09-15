package collections.set;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

public class Birthday
{
    @Test
    public void runCode()
    {
        var average = IntStream.range(0, 1000).map(i -> simulate()).sum() / 1000;

        System.out.println("The average number of people needed to find a matching birthday is: " + average);
    }

    private int simulate()
    {
        var random = new Random();
        var set = new HashSet<Integer>();

        // pick random day in a loop
        // find how many iterations till first collision (got the same number)
        for (var i = 0; i < 365; i++)
        {
            var randomDayOfYear = random.nextInt(365);

            if (set.contains(randomDayOfYear)) {
                return i;
            }

            set.add(randomDayOfYear);
        }

        throw new RuntimeException("Shouldn't happen");
    }
}