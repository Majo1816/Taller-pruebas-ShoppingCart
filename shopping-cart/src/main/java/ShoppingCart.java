import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<String, Item> items = new HashMap<>();

    public void addItem(String name, int quantity, double pricePerUnit) {
        if (quantity <= 0 || pricePerUnit < 0) {
            throw new IllegalArgumentException("Invalid quantity or price");
        }
        items.put(name, new Item(name, quantity, pricePerUnit));
    }

    public void removeItem(String name) {
        if (!items.containsKey(name)) {
            throw new IllegalArgumentException("Item not found");
        }
        items.remove(name);
    }

    public double getTotal() {
        return items.values().stream()
            .mapToDouble(item -> item.quantity * item.pricePerUnit)
            .sum();
    }

     public double applyDiscount(double percentage) {
     if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("Invalid discount percentage");
     }
        return 0; 
}

    public int getItemCount() {
        return items.size();
    }

    private static class Item {
        String name;
        int quantity;
        double pricePerUnit;

        Item(String name, int quantity, double pricePerUnit) {
            this.name = name;
            this.quantity = quantity;
            this.pricePerUnit = pricePerUnit;
        }
    }
}
