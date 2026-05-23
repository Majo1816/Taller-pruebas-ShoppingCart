import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    public void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    public void testAddItemAndGetTotal() {
        cart.addItem("Laptop", 1, 1500.0);
        cart.addItem("Mouse", 2, 25.0);

        double expectedTotal = 1500.0 + (2 * 25.0);
        assertEquals(expectedTotal, cart.getTotal(), 0.001);
    }

    @Test
    public void testItemCount() {
        cart.addItem("Keyboard", 1, 45.0);
        cart.addItem("Monitor", 1, 200.0);

        assertEquals(2, cart.getItemCount());
    }

    @Test
    public void testApplyValidDiscount() {
        cart.addItem("Desk", 1, 300.0);
        double discountedTotal = cart.applyDiscount(10); // 10% discount
        assertEquals(270.0, discountedTotal, 0.001);
    }

    @Test
    public void testAddItemWithInvalidQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            cart.addItem("Chair", 0, 50.0);
        });
    }

    @Test
    public void testAddItemWithNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            cart.addItem("Lamp", 1, -10.0);
        });
    }

    @Test
    public void testRemoveItemSuccessfully() {
        cart.addItem("Phone", 1, 800.0);
        cart.removeItem("Phone");

        assertEquals(0, cart.getItemCount());
    }

    @Test
    public void testRemoveNonexistentItem() {
        assertThrows(IllegalArgumentException.class, () -> {
            cart.removeItem("Tablet");
        });
    }

    @Test
    public void testApplyInvalidDiscount() {
        cart.addItem("Book", 2, 15.0);
        assertThrows(IllegalArgumentException.class, () -> {
            cart.applyDiscount(-5.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cart.applyDiscount(150.0);
        });
    }
}
