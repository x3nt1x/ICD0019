package generics.cart;

public class CartEntry<T extends CartItem>
{
    private final T item;
    private int quantity;

    public CartEntry(T item)
    {
        this.item = item;
        this.quantity = 1;
    }

    public T getItem()
    {
        return this.item;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public void increaseQuantity()
    {
        this.quantity++;
    }

    @Override
    public String toString()
    {
        return String.format("(%s, %s, %s)", item.getId(), item.getPrice(), this.quantity);
    }
}