package types;

import java.util.Arrays;
import java.util.Random;

public class Code
{
    public static void main(String[] args)
    {
        System.out.println(sum(new int[]{ 1, 3, -2, 9 })); // 11
        System.out.println();

        System.out.println(average(new int[]{ 1, 2 })); // 1.5
        System.out.println();

        System.out.println(minimumElement(new int[]{ 1, 2 })); // 1
        System.out.println(minimumElement(new int[]{ })); // null
        System.out.println();

        System.out.println(asString(new int[]{ 1, 3, -2, 9 })); // "1, 3, -2, 9"
        System.out.println();

        System.out.println(mode("abcb")); // 'b'
        System.out.println(mode("abccbc")); // 'c'
        System.out.println(mode("")); // null
        System.out.println(mode("ab")); // 'a'
        System.out.println();

        System.out.println(squareDigits("2")); // "4"
        System.out.println(squareDigits("a22b")); // "a44b"
        System.out.println(squareDigits("a9b2")); // "a81b4"
        System.out.println();

        System.out.println(isolatedSquareCount()); // 2
    }

    public static int sum(int[] numbers)
    {
        var result = 0;

        for (var number : numbers) {
            result += number;
        }

        return result;
    }

    public static double average(int[] numbers)
    {
        return Float.valueOf(sum(numbers)) / numbers.length;
    }

    public static Integer minimumElement(int[] integers)
    {
        if (integers.length == 0) {
            return null;
        }

        Arrays.sort(integers);

        return integers[0];
    }

    public static String asString(int[] elements)
    {
        var elementsAsStrings = new String[elements.length];

        for (var i = 0; i < elements.length; i++) {
            elementsAsStrings[i] = Integer.toString(elements[i]);
        }

        return String.join(", ", elementsAsStrings);
    }

    public static Character mode(String input)
    {
        if (input.isEmpty()) {
            return null;
        }

        var charArray = input.toCharArray();
        Arrays.sort(charArray);

        var result = charArray[0];
        var current = result;
        var count = 0;
        var highestCount = 0;

        for (var c : charArray)
        {
            if (c != current) {
                count = 0;
            }

            count += 1;

            if (count > highestCount)
            {
                result = c;
                highestCount = count;
            }

            current = c;
        }

        return result;
    }

    public static String squareDigits(String s)
    {
        var result = "";

        for (var c : s.toCharArray())
        {
            if (!Character.isDigit(c))
            {
                result += c;

                continue;
            }

            var number = Integer.parseInt(Character.toString(c));

            result += number * number;
        }

        return result;
    }

    public static boolean isIsolated(boolean[][] matrix, int i, int j)
    {
        for (var y = i - 1; y <= i + 1; y++)
        {
            if (y < 0 || y > 9) {
                continue;
            }

            for (var x = j - 1; x <= j + 1; x++)
            {
                if (x < 0 || x > 9) {
                    continue;
                }

                if (y == i && x == j) {
                    continue;
                }

                if (matrix[y][x]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int isolatedSquareCount()
    {
        var matrix = getSampleMatrix();

        printMatrix(matrix);

        var isolatedCount = 0;

        for (var i = 0; i < 10; i++)
        {
            for (var j = 0; j < 10; j++)
            {
                if (isIsolated(matrix, i, j)) {
                    isolatedCount++;
                }
            }
        }

        return isolatedCount;
    }

    private static void printMatrix(boolean[][] matrix)
    {
        for (var row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleMatrix()
    {
        var matrix = new boolean[10][10];

        var random = new Random(5);

        for (var i = 0; i < matrix.length; i++)
        {
            for (var j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = random.nextInt(5) < 2;
            }
        }

        return matrix;
    }
}