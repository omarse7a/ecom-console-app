package customer;

import exceptions.EmptyCartException;
import exceptions.ExpiredProductException;
import exceptions.InsufficientBalanceException;
import exceptions.InsufficientStockException;
import product.Product;

public class Customer {
    private double balance;
    private Cart cart;

    public Customer(double balance) {
        this.balance = balance;
        this.cart = new Cart((this));
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void decrementBalance(double amount) {
        this.balance -= amount;
    }

    public void addToCart(Product product, int quantity) {
        try {
            cart.add(new CartItem(product, quantity));
            System.out.println(quantity + "x " + product.getName() + " is added successfully");
        }
        catch (InsufficientStockException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkout() {
        try {
            cart.checkout();
            cart = new Cart(this);
        }
        catch (EmptyCartException |
               InsufficientStockException |
               ExpiredProductException |
               InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}
