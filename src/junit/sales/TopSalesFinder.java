package junit.sales;

public class TopSalesFinder
{
    SalesRecord[] sales = { };

    public void registerSale(SalesRecord record)
    {
        sales = appendArray(sales, record);
    }

    public String[] findItemsSoldOver(int amount)
    {
        var items = new SalesRecord[] { };

        for (var sale : sales)
        {
            var item = sale.getProductId();

            if (arrayContains(items, item)) {
                continue;
            }

            if (isItemSoldOver(item, amount)) {
                items = appendArray(items, sale);
            }
        }

        return getItemsAsStringArray(items);
    }

    public boolean isItemSoldOver(String item, int amount)
    {
        var total = 0;

        for (var sale : sales)
        {
            if (sale.getProductId().equals(item)) {
                total += sale.getProductPrice() * sale.getItemsSold();
            }
        }

        return total > amount;
    }

    public boolean arrayContains(SalesRecord[] array, String item)
    {
        for (var element : array)
        {
            if (element.getProductId().equals(item)) {
                return true;
            }
        }

        return false;
    }

    public SalesRecord[] appendArray(SalesRecord[] array, SalesRecord item)
    {
        var newArray = new SalesRecord[array.length + 1];

        for (var i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        newArray[array.length] = item;

        return newArray;
    }

    public String[] getItemsAsStringArray(SalesRecord[] sales)
    {
        var items = new String[sales.length];

        for (var i = 0; i < sales.length; i++) {
            items[i] = sales[i].getProductId();
        }

        return items;
    }
}