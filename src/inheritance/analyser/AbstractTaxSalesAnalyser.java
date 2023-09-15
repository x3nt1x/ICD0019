package inheritance.analyser;

import java.util.ArrayList;
import java.util.List;

public sealed abstract class AbstractTaxSalesAnalyser permits FlatTaxSalesAnalyser, DifferentiatedTaxSalesAnalyser, TaxFreeSalesAnalyser
{
    private final List<SalesRecord> records;

    public AbstractTaxSalesAnalyser(List<SalesRecord> records)
    {
        this.records = records;
    }

    abstract double getRegularTax();

    abstract double getReducedTax();

    public Double getTotalSales()
    {
        return getSales(null);
    }

    public Double getTotalSalesByProductId(String id)
    {
        return getSales(id);
    }

    public String getIdOfMostPopularItem()
    {
        return getIdOfItem(false);
    }

    public String getIdOfItemWithLargestTotalSales()
    {
        return getIdOfItem(true);
    }

    private Double getSales(String id)
    {
        var sales = 0.0;

        for (var record : records)
        {
            if (id != null && !record.getProductId().equals(id)) {
                continue;
            }

            var total = record.getProductPrice() * record.getItemsSold();

            sales += total / (record.hasReducedRate() ? getReducedTax() : getRegularTax());
        }

        return sales;
    }

    private int getTotalItemsSold(String id)
    {
        var sold = 0;

        for (var record : records)
        {
            if (record.getProductId().equals(id)) {
                sold += record.getItemsSold();
            }
        }

        return sold;
    }

    private String getIdOfItem(boolean bySales)
    {
        var id = "";
        var highestCount = 0.0;
        var counted = new ArrayList<String>();

        for (var record : records)
        {
            var productId = record.getProductId();

            // don't count same items twice
            if (counted.contains(productId)) {
                continue;
            }

            var count = bySales ? getSales(productId) : getTotalItemsSold(productId);

            if (highestCount < count)
            {
                highestCount = count;
                id = productId;
            }

            counted.add(productId);
        }

        return id;
    }
}