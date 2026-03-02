public class PaymentGateway implements Payment {
    @Override
    public String charge(String studentId, double amount) {
        return "TXN-9001";
    }
}
