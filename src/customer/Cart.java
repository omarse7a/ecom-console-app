package customer;

import exceptions.EmptyCartException;
import exceptions.ExpiredProductException;
import exceptions.InsufficientBalanceException;
import exceptions.InsufficientStockException;
import product.Expirable;
import product.Product;
import product.Shippable;
import shipping.ShippingService;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    private ShippingService shippingService;

    // maintaining a bidirectional relationship between customer and cart
    // to allow checking the customer balance at checkout
    private Customer customer;

    public Cart(Customer c) {
        this.items = new ArrayList<>();
        this.customer = c;
    }
    public void add(CartItem item) {
        Product p = item.getProduct();
        if(!item.isInStock())
            throw new InsufficientStockException("Insufficient stock for product: " + p.getName());
        items.add(item);
    }
    public void checkout() {
        if(items.isEmpty())
            throw new EmptyCartException("Abort checkout: The cart is empty");
        // instantiate a new shipping service every checkout instead of clearing its members
        this.shippingService = new ShippingService();
        double subtotal = 0;
        for(CartItem item : items) {
            if(!item.isInStock()) {
                items.remove(item);
                throw new InsufficientStockException("Abort checkout: Insufficient stock for product " + item.getProduct().getName());
            }
            Product p = item.getProduct();
            if(p instanceof Expirable) {
                Expirable pExp = (Expirable)p;
                if(pExp.isExpired()) {
                    items.remove(item);
                    throw new ExpiredProductException("Abort checkout: Product " + item.getProduct().getName() + " is expired");
                }
            }
            if(p instanceof Shippable) {
                Shippable pShip = (Shippable)p;
                shippingService.add(pShip, item.getQuantity());
            }
            subtotal += item.getItemSubtotal();
        }
        double shippingFees = shippingService.getFees();
        double total = subtotal + shippingFees;
        if(total > customer.getBalance())
            throw new InsufficientBalanceException("Abort checkout: Insufficient customer balance of " + customer.getBalance());
        customer.decrementBalance(total);
        printDetails(subtotal, shippingFees);
    }
    void printDetails(double subtotal, double shippingFees) {
        // printing checkout details
        System.out.println("//////////////////////");
        System.out.println("** Shipping Details **");
        System.out.println(shippingService.getShippingDetails());
        System.out.println("** Checkout Receipt **");
        for(CartItem item : items) {
            System.out.println(item.toString());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t" + subtotal);
        System.out.println("Shipping\t" + shippingFees);
        System.out.println("Total Amount\t" + (subtotal + shippingFees));
        System.out.println("Balance Left\t" + String.format("%.2f", customer.getBalance()));
        System.out.println("//////////////////////");
    }
}
