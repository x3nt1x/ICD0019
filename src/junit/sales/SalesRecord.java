package junit.sales;

public class SalesRecord
{
    private String productId;
    private int productPrice;
    private int itemsSold;

    public SalesRecord(String productId, int productPrice, int itemsSold)
    {
        this.productId = productId;
        this.productPrice = productPrice;
        this.itemsSold = itemsSold;
    }

    public String getProductId()
    {
        return productId;
    }

    public int getProductPrice()
    {
        return productPrice;
    }

    public int getItemsSold()
    {
        return itemsSold;
    }

    @Override
    public String toString()
    {
        return String.format("SalesRecord { productId = %s, productPrice = %d, itemsSold = %d }", productId, productPrice, itemsSold);
    }
}