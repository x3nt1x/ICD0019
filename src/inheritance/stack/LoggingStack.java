package inheritance.stack;

import java.util.Stack;

public class LoggingStack extends Stack<Integer>
{
    @Override
    public Integer push(Integer element)
    {
        System.out.println("Adding element: " + element);

        super.push(element);

        return element;
    }

    @Override
    public Integer pop()
    {
        var element = super.pop();

        System.out.println("Removing element: " + element);

        return element;
    }

    public void pushAll(Integer... numbers)
    {
        for (var number : numbers) {
            push(number);
        }
    }
}