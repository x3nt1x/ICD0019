package collections.streaks;

import java.util.*;

public class Code
{
    public static List<List<String>> getStreakList(String input)
    {
        var result = new LinkedList<List<String>>();

        for (var character : input.toCharArray())
        {
            var symbol = Character.toString(character);

            if (result.isEmpty() || !result.getLast().contains(symbol)) {
                result.add(new ArrayList<>(List.of(symbol)));
            }
            else {
                result.getLast().add(symbol);
            }
        }

        return result;
    }
}