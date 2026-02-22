import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store) {
        this.store = store;
    }

    public void addToMenu(MenuItem item) {
        menu.put(item.id, item);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = 0.0;
        for (OrderLine l : lines) {
            subtotal += menu.get(l.itemId).price * l.qty;
        }

        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = DiscountRules.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        String invoice = InvoiceFormatter.format(invId, lines, menu, subtotal, taxPct, tax, discount, total);
        System.out.print(invoice);

        store.save(invId, invoice);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
