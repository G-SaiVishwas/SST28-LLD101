import java.util.*;

public class AddOnPricing {
    private final Map<AddOn, Double> prices = new HashMap<>();

    public AddOnPricing() {
        prices.put(AddOn.MESS, 1000.0);
        prices.put(AddOn.LAUNDRY, 500.0);
        prices.put(AddOn.GYM, 300.0);
    }

    public double getPrice(AddOn addOn) {
        return prices.getOrDefault(addOn, 0.0);
    }

    public void register(AddOn addOn, double price) {
        prices.put(addOn, price);
    }
}
