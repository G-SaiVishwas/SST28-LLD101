import java.util.*;

public class Demo06 {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        List<NotificationSender> senders = List.of(
                new EmailSender(),
                new SmsSender(),
                new WhatsAppSender()
        );
        String[] names = {"EMAIL", "SMS", "WA"};

        for (int i = 0; i < senders.size(); i++) {
            SendResult result = senders.get(i).send(n, audit);
            if (!result.success) {
                System.out.println(names[i] + " ERROR: " + result.error);
            }
        }
        System.out.println("AUDIT entries=" + audit.all().size());
    }
}
