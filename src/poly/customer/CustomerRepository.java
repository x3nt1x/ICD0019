package poly.customer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository
{
    private final static String FILE_PATH = "src/poly/customer/data.txt";
    private final List<AbstractCustomer> customers = new ArrayList<>();

    public CustomerRepository()
    {
        try
        {
            for (var line : Files.readAllLines(Paths.get(FILE_PATH))) {
                this.customers.add(createCustomer(line.split(";")));
            }
        }
        catch (IOException exception)
        {
            throw new RuntimeException("Error during reading from the file");
        }
    }

    private AbstractCustomer createCustomer(String[] data)
    {
        var id = data[1];
        var type = data[0];
        var name = data[2];
        var bonus = Integer.parseInt(data[3]);

        if (type.equals("GOLD")) {
            return new GoldCustomer(id, name, bonus);
        }

        var lastOrderDate = LocalDate.parse(data[4], DateTimeFormatter.ofPattern("yyy-MM-dd"));

        return new RegularCustomer(id, name, bonus, lastOrderDate);
    }

    public Optional<AbstractCustomer> getCustomerById(String id)
    {
        return this.customers.stream().filter(customer -> customer.id.equals(id)).findFirst();
    }

    public void remove(String id)
    {
        customers.remove(getCustomerById(id).orElseThrow(() -> new RuntimeException("Error removing the customer")));

        overwriteFile();
    }

    public void save(AbstractCustomer customer)
    {
        var existingCustomer = getCustomerById(customer.getId());

        if (existingCustomer.isPresent()) {
            this.customers.set(this.customers.indexOf(existingCustomer.get()), customer);
        }
        else {
            this.customers.add(customer);
        }

        overwriteFile();
    }

    public int getCustomerCount()
    {
        return this.customers.size();
    }

    private void clearFile()
    {
        try {
            new FileWriter(FILE_PATH).close();
        }
        catch (IOException e) {
            throw new RuntimeException("Error during emptying the file");
        }
    }

    private void overwriteFile()
    {
        clearFile();

        try (var file = new FileWriter(FILE_PATH, true))
        {
            for (var customer : customers) {
                file.write(customer.toString() + '\n');
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error during overwriting the file");
        }
    }
}