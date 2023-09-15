package exceptions.bad;

public class BadTryCatch
{
    public boolean containsSingleLetters(String input)
    {
        if (input == null) {
            return false;
        }

        for (var i = 0; i < input.length() - 1; i++)
        {
            if (input.charAt(i) == input.charAt(i + 1)) {
                return false;
            }
        }

        return true;
    }
}