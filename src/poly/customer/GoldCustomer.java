package poly.customer;

public final class GoldCustomer extends AbstractCustomer
{
    public GoldCustomer(String id, String name, int bonusPoints)
    {
        super(id, name, bonusPoints);
    }

    @Override
    public void collectBonusPointsFrom(Order order)
    {
        if (order.getTotal() >= 100) {
            this.bonusPoints += order.getTotal() * 1.5;
        }
    }

    @Override
    public String asString()
    {
        return String.format("GOLD;%s;%s;%s", this.id, this.name, this.bonusPoints);
    }
}