package fp;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class Persons
{
    private List<Person> persons = List.of(
            new Person(1, "Alice", 22),
            new Person(2, "Bob", 20),
            new Person(3, "Carol", 21));

    @Test
    public void findsPersonById()
    {
        var person = persons.stream().filter(p -> p.getId() == 2).findFirst().orElseThrow();

        System.out.println(person);
    }

    @Test
    public void removePersonById()
    {
        var removed = persons.stream().filter(p -> p.getId() != 2).toList();

        System.out.println(removed);
    }

    @Test
    public void findsPersonNames()
    {
        var names = String.join(", ", persons.stream().map(Person::getName).toList());

        System.out.println(names);
    }

    @Test
    public void reverseSortedByAge()
    {
        var sorted = persons.stream().sorted(Comparator.comparing(Person::getAge).reversed()).toList();

        System.out.println(sorted);
    }
}