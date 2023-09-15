package generics.pair;

public class Pair<TFirst, TSecond>
{
    private final TFirst first;
    private final TSecond second;

    public Pair(TFirst first, TSecond second)
    {
        this.first = first;
        this.second = second;
    }

    public TFirst getFirst()
    {
        return first;
    }

    public TSecond getSecond()
    {
        return second;
    }
}