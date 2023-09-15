package reflection.serializer;

public class Customer
{
    @Stored
    private String firstName;

    @Stored
    private String lastName;

    public Customer() { }

    public Customer(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    @Override
    public String toString()
    {
        return String.format("Customer{firstName='%s', lastName='%s'}", firstName, lastName);
    }
}