import customer.Customer;
import product.Product;
import product.ShippableExpirableProduct;
import product.ShippableProduct;

public class Main {
    public static void main(String[] args) {

        Product cheese = new ShippableExpirableProduct("Cheese", 2, 95.99, 0.45, false);
        Product egg = new ShippableExpirableProduct("Egg", 5, 6.5, 0.1, true);
        Product tv = new ShippableProduct("TV", 1, 1299.99, 2);
        Product ebook = new Product("Ebook", 2, 150);

        Customer c = new Customer(520.99);

        // Case: checkout an empty cart
        c.checkout();

        // Case: checkout an expired item
        c.addToCart(egg, 2);
        c.checkout();

        // Case: add item with quantity more than available stock
        c.addToCart(cheese, 2);
        c.addToCart(tv, 2);
        c.addToCart(ebook, 1);
        c.checkout();

        // Case: checkout with insufficient balance
        c.addToCart(tv, 1);
        c.checkout();

        // Case: a valid checkout
        c.setBalance(1500); // increasing the balance
        System.out.println("balance is increased to 1500");
        c.checkout();
    }
}