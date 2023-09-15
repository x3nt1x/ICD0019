package fp.sales;

import java.time.LocalDate;

public class Entry
{
    private String productId;
    private String category;
    private String state;
    private Double amount;
    private LocalDate date;

    public Entry(String productId, String category, Double amount, String state, LocalDate date)
    {
        this.productId = productId;
        this.category = category;
        this.amount = amount;
        this.state = state;
        this.date = date;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }
}