package product;

public class ExpirableProduct extends Product implements Expirable{
    private boolean expired;

    public ExpirableProduct(String name, int quantity, double price, boolean expired) {
        super(name, quantity, price);
        this.expired = expired;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }
    public void setExpired(boolean exp) {
        this.expired = exp;
    }
}
