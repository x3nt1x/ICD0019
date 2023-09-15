package poly.customer;

import java.time.LocalDate;
import java.time.Period;

public final class RegularCustomer extends AbstractCustomer
{
    private final LocalDate lastOrderDate;

    public RegularCustomer(String id, String name, int bonusPoints, LocalDate lastOrderDate)
    {
        super(id, name, bonusPoints);

        this.lastOrderDate = lastOrderDate;
    }

    @Override
    public void collectBonusPointsFrom(Order order)
    {
        if (order.getTotal() < 100) {
            return;
        }

        if (Period.between(this.lastOrderDate, order.getDate()).getMonths() < 1) {
            this.bonusPoints += order.getTotal() * 1.5;
        }
        else {
            this.bonusPoints += order.getTotal();
        }
    }

    @Override
    public String asString()
    {
        return String.format("REGULAR;%s;%s;%s;%s", this.id, this.name, this.bonusPoints, this.lastOrderDate);
    }
}