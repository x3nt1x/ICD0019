package exceptions.numbers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class NumberConverter
{
    private final Properties properties;

    public NumberConverter(String language)
    {
        properties = new Properties();

        FileInputStream file = null;

        try
        {
            var filePath = String.format("src/exceptions/numbers/numbers_%s.properties", language);

            file = new FileInputStream(filePath);

            var reader = new InputStreamReader(file, StandardCharsets.UTF_8);

            properties.load(reader);
        }
        catch (FileNotFoundException e) {
            throw new MissingLanguageFileException(language, e);
        }
        catch (IllegalArgumentException e) {
            throw new BrokenLanguageFileException(language, e);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            close(file);
        }

        if (properties.isEmpty()) {
            throw new MissingTranslationException(language);
        }
    }

    public String numberInWords(Integer number)
    {
        if (properties.containsKey(String.valueOf(number))) {
            return properties.getProperty(String.valueOf(number));
        }

        var result = "";

        var hundreds = number / 100;
        var tens = (number / 10) % 10;
        var ones = number % 10;

        if (hundreds > 0) {
            result += convertHundreds(hundreds, tens > 0 || ones > 0);
        }

        if (tens >= 2) {
            result += convertTens(tens);
        }

        if (tens == 1) {
            result += convertTeens(ones);
        }
        else if (ones >= 1) {
            result += convertOnes(ones, tens > 0);
        }

        return result;
    }

    private String convertOnes(Integer ones, boolean addDelimiter)
    {
        // 0 - 10

        var value = properties.getProperty(String.valueOf(ones));
        var afterDelimiter = addDelimiter ? properties.getProperty("tens-after-delimiter") : "";

        return String.format("%s%s", afterDelimiter, value);
    }

    private String convertTeens(Integer teens)
    {
        // 11 - 19

        var formattedTeens = String.format("1%s", teens);

        if (properties.containsKey(formattedTeens)) {
            return properties.getProperty(formattedTeens);
        }

        var value = properties.getProperty(String.valueOf(teens));
        var teen = properties.getProperty("teen");

        return String.format("%s%s", value, teen);
    }

    private String convertTens(Integer tens)
    {
        // 2x - 9x

        var formattedTens = String.format("%s0", tens);

        if (properties.containsKey(formattedTens)) {
            return properties.getProperty(formattedTens);
        }

        var value = properties.getProperty(String.valueOf(tens));
        var tensSuffix = properties.getProperty("tens-suffix");

        return String.format("%s%s", value, tensSuffix);
    }

    private String convertHundreds(Integer hundreds, boolean addDelimiter)
    {
        // 1xx - 9xx

        var value = properties.getProperty(String.valueOf(hundreds));
        var hundred = properties.getProperty("hundred");
        var beforeDelimiter = properties.getProperty("hundreds-before-delimiter");
        var afterDelimiter = addDelimiter ? properties.getProperty("hundreds-after-delimiter") : "";

        return String.format("%s%s%s%s", value, beforeDelimiter, hundred, afterDelimiter);
    }

    private void close(FileInputStream file)
    {
        if (file == null) {
            return;
        }

        try {
            file.close();
        } catch (IOException ignore) { }
    }
}