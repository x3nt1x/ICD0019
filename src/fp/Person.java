package fp;

public class Person
{
    private final Integer id;
    private final String name;
    private final Integer age;

    public Person(Integer id, String name, Integer age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Integer getAge()
    {
        return age;
    }

    @Override
    public String toString()
    {
        return String.format("Person {id=%s, name='%s', age=%s}", id, name, age);
    }
}