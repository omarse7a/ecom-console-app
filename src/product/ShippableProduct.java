package product;

public class ShippableProduct extends Product implements Shippable{
    private double weight;

    public ShippableProduct(String name, int quantity, double price, double weight) {
        super(name, quantity, price);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    public void setWeight(double w) {
        this.weight = w;
    }
}
