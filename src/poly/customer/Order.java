package poly.customer;

import java.time.LocalDate;

public class Order
{
    private final double total;
    private final LocalDate date;

    public Order(double total, LocalDate date)
    {
        this.total = total;
        this.date = date;
    }

    public double getTotal()
    {
        return total;
    }

    public LocalDate getDate()
    {
        return date;
    }
}