package junit;

public class Code
{
    public static boolean isSpecial(int candidate)
    {
        return candidate % 11 <= 3;
    }

    public static int longestStreak(String inputString)
    {
        var count = 0;
        var highestCount = 0;
        var previousChar = ' ';

        for (var currentChar : inputString.toCharArray())
        {
            if (previousChar != currentChar)
            {
                previousChar = currentChar;
                count = 0;
            }

            count++;

            if (highestCount < count) {
                highestCount = count;
            }
        }

        return highestCount;
    }

    public static Character mode(String inputString)
    {
        if (inputString == null) {
            return null;
        }

        var highestCount = 0;
        Character result = null;

        for (var currentChar : inputString.toCharArray())
        {
            var count = getCharacterCount(inputString, currentChar);

            if (highestCount < count)
            {
                highestCount = count;
                result = currentChar;
            }
        }

        return result;
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter)
    {
        var count = 0;

        for (var c : allCharacters.toCharArray())
        {
            if (targetCharacter == c) {
                count++;
            }
        }

        return count;
    }

    public static boolean arrayContains(int[] array, int value)
    {
        for (var number : array)
        {
            if (number == value) {
                return true;
            }
        }

        return false;
    }

    public static int[] arrayAppend(int[] array, int value)
    {
        var newArray = new int[array.length + 1];

        for (var i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        newArray[array.length] = value;

        return newArray;
    }

    public static int[] removeDuplicates(int[] integers)
    {
        var resultArray = new int[] { };

        for (var number : integers)
        {
            if (!arrayContains(resultArray, number)) {
                resultArray = arrayAppend(resultArray, number);
            }
        }

        return resultArray;
    }

    public static int sumIgnoringDuplicates(int[] integers)
    {
        var result = 0;

        for (var number : removeDuplicates(integers)) {
            result += number;
        }

        return result;
    }
}