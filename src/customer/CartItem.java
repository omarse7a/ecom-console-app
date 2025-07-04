package customer;

import product.Product;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product p, int q) {
        this.product = p;
        this.quantity = q;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    boolean isInStock() {
        return this.product.getStock() >= quantity;
    }

    public double getItemSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return quantity + "x " + product.getName() + "\t" + getItemSubtotal();
    }
}
