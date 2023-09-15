package reflection.serializer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Serializer
{
    public String serialize(Object instance)
    {
        var pairs = new ArrayList<String>();
        var fields = instance.getClass().getDeclaredFields();

        for (var field : fields)
        {
            var serialized = serializeField(instance, field);

            if (serialized != null) {
                pairs.add(serialized);
            }
        }

        return String.join("|", pairs);
    }

    public <T> T deserialize(String inputString, Class<T> clazz)
    {
        T instance;

        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        var fields = instance.getClass().getDeclaredFields();

        for (var field : fields) {
            deserializeField(inputString, instance, field);
        }

        return instance;
    }

    private String serializeField(Object instance, Field field)
    {
        field.setAccessible(true);

        var annotation = field.getAnnotation(Stored.class);
        if (annotation == null) {
            return null;
        }

        try
        {
            var fieldName = annotation.value().isEmpty() ? field.getName() : annotation.value();
            var fieldValue = serializeSymbols(field.get(instance).toString());

            return String.format("%s:%s", fieldName, fieldValue);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void deserializeField(String inputString, Object instance, Field field)
    {
        field.setAccessible(true);

        var annotation = field.getAnnotation(Stored.class);
        if (annotation == null) {
            return;
        }

        var fieldName = annotation.value().isEmpty() ? field.getName() : annotation.value();
        var fieldValue = deserializeSymbols(getFieldValue(inputString, fieldName));

        try
        {
            if (field.getType() == int.class) {
                field.set(instance, Integer.parseInt(fieldValue));
            }
            else {
                field.set(instance, fieldValue);
            }
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String getFieldValue(String inputString, String fieldName)
    {
        var keyValuePairs = inputString.split("\\|");

        for (var keyValuePair : keyValuePairs)
        {
            var split = keyValuePair.split(":");

            var key = split[0];
            var value = split[1];

            if (fieldName.equals(key)) {
                return value;
            }
        }

        throw new RuntimeException("Couldn't find a value for the field");
    }

    private String serializeSymbols(String text)
    {
        return text.replaceAll("%", "%25")
                   .replaceAll(":", "%3a")
                   .replaceAll("\\|", "%7c");
    }

    private String deserializeSymbols(String text)
    {
        return text.replaceAll("%25", "%")
                   .replaceAll("%3a", ":")
                   .replaceAll("%7c", "\\|");
    }
}