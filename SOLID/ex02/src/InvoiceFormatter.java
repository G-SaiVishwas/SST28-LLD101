import java.util.*;

public class InvoiceFormatter {
    public static String format(String invoiceId, List<OrderLine> lines,
                                Map<String, MenuItem> menu, double subtotal,
                                double taxPct, double tax, double discount, double total) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(invoiceId).append("\n");
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            sb.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }
        sb.append(String.format("Subtotal: %.2f\n", subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        sb.append(String.format("Discount: -%.2f\n", discount));
        sb.append(String.format("TOTAL: %.2f\n", total));
        return sb.toString();
    }
}
