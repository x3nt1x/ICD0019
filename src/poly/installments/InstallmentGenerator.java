package poly.installments;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class InstallmentGenerator
{
    public List<Installment> generateRowsFor(Integer amount, LocalDate periodStart, LocalDate periodEnd)
    {
        if (amount < 3) {
            return List.of(new Installment(amount, periodStart));
        }

        var installments = new ArrayList<Installment>();
        var period = Period.between(periodStart.minusMonths(1), periodEnd).getMonths();

        for (var months = period; months > 0; months--)
        {
            if (periodStart.getMonth().equals(periodEnd.getMonth()))
            {
                installments.add(new Installment(amount, periodStart));
                break;
            }

            var payment = amount / months;
            if (payment < 3) {
                continue;
            }

            amount -= payment;

            installments.add(new Installment(payment, periodStart));

            periodStart = periodStart.plusMonths(1).withDayOfMonth(1);
        }

        return installments;
    }
}