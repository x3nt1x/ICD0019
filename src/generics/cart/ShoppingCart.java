package generics.cart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart<T extends CartItem>
{
    private final List<CartEntry<T>> cart = new ArrayList<>();
    private final List<Double> discounts = new ArrayList<>();

    public void add(T item)
    {
        if (!increaseQuantity(item.getId())) {
            cart.add(new CartEntry<>(item));
        }
    }

    public void removeById(String id)
    {
        cart.removeIf(item -> item.getItem().getId().equals(id));
    }

    public Double getTotal()
    {
        var total = cart.stream().mapToDouble(e -> e.getItem().getPrice() * e.getQuantity()).sum();

        for (var discount : discounts) {
            total -= discount / 100.0 * total;
        }

        return total;
    }

    public boolean increaseQuantity(String id)
    {
        var existing = cart.stream().filter(e -> e.getItem().getId().equals(id)).findFirst();
        if (existing.isEmpty()) {
            return false;
        }

        existing.get().increaseQuantity();
        return true;
    }

    public void applyDiscountPercentage(Double discount)
    {
        discounts.add(discount);
    }

    public void removeLastDiscount()
    {
        discounts.remove(discounts.size() - 1);
    }

    public void addAll(List<? extends T> items)
    {
        items.forEach(this::add);
    }

    @Override
    public String toString()
    {
        return String.join(", ", cart.stream().map(CartEntry::toString).toList());
    }
}