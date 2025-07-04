package shipping;

import product.Shippable;

import java.util.ArrayList;
import java.util.List;

public class ShippingService {
    private List<Shippable> shippingList;
    private List<Integer> quantityArray;
    private double totalWeight;

    public ShippingService() {
        shippingList = new ArrayList<>();
        quantityArray = new ArrayList<>();
    }

    public void add(Shippable item, int quantity) {
        shippingList.add(item);
        quantityArray.add(quantity);
        totalWeight += item.getWeight() * quantity;
    }

    // simplified shipping fees calculations
    public double getFees() {
        if(totalWeight < 1)
            return 50;
        return 100;
    }
    public String getShippingDetails() {
        StringBuilder s = new StringBuilder();
        int n = shippingList.size();
        for (int i = 0; i < n; i++) {
            s.append(quantityArray.get(i) + "x " + shippingList.get(i).getName() + "\t" + shippingList.get(i).getWeight() + "kg\n");
        }
        return s.toString();
    }
}
