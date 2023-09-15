package poly.undo;

import java.util.Stack;
import java.util.function.Function;

public class Calculator
{
    private double value;
    private final Stack<Function<Double, Double>> history = new Stack<>();

    public void input(double value)
    {
        history.push(input -> input - value);

        this.value = value;
    }

    public void add(double addend)
    {
        history.push(input -> input - addend);

        value += addend;
    }

    public void multiply(double multiplier)
    {
        history.push(input -> input / multiplier);

        value *= multiplier;
    }

    public double getResult()
    {
        return value;
    }

    public void undo()
    {
        this.value = history.pop().apply(this.value);
    }
}