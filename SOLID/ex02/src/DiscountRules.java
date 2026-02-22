public class DiscountRules {
    public static double discountAmount(String customerType, double subtotal, int lineCount) {
        if ("student".equalsIgnoreCase(customerType)) {
            return subtotal >= 180.0 ? 10.0 : 0.0;
        }
        if ("staff".equalsIgnoreCase(customerType)) {
            return lineCount >= 3 ? 15.0 : 5.0;
        }
        return 0.0;
    }
}
