package product;

public class ShippableExpirableProduct extends Product implements Shippable, Expirable{
    private double weight;
    private boolean expired;

    public ShippableExpirableProduct(String name, int quantity, double price, double weight, boolean expired) {
        super(name, quantity, price);
        this.weight = weight;
        this.expired = expired;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }
    public void setExpired(boolean exp) {
        this.expired = exp;
    }
}
