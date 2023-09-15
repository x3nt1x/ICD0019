package reflection.tester;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner
{
    private final List<String> results = new ArrayList<>();

    public void runTests(List<String> classNames)
    {
        for (var className : classNames)
        {
            var instance = createInstance(className);
            var methods = instance.getClass().getDeclaredMethods();

            for (var method : methods)
            {
                if (!method.isAnnotationPresent(MyTest.class)) {
                    continue;
                }

                var methodName = method.getName();
                var methodResult = "FAILED";

                var expected = method.getAnnotation(MyTest.class).expected();

                if (expected.isAssignableFrom(methodResult(instance, method))) {
                    methodResult = "OK";
                }

                results.add(String.format("%s() - %s", methodName, methodResult));
            }
        }
    }

    public String getResult()
    {
        return String.join("\n", results);
    }

    private Object createInstance(String className)
    {
        try {
            return Class.forName(className).getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Class<? extends Throwable> methodResult(Object instance, Method method)
    {
        try {
            method.invoke(instance);
        }
        catch (InvocationTargetException e) {
            return e.getTargetException().getClass();
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return MyTest.None.class;
    }
}