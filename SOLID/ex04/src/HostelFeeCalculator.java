public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomPricing roomPricing;
    private final AddOnPricing addOnPricing;

    public HostelFeeCalculator(FakeBookingRepo repo, RoomPricing roomPricing, AddOnPricing addOnPricing) {
        this.repo = repo;
        this.roomPricing = roomPricing;
        this.addOnPricing = addOnPricing;
    }

    public void process(BookingRequest req) {
        double base = roomPricing.getPrice(req.roomType);
        double extras = 0.0;
        for (AddOn a : req.addOns) {
            extras += addOnPricing.getPrice(a);
        }

        Money monthly = new Money(base + extras);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new java.util.Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}
