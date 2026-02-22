public class EmailSender implements NotificationSender {
    @Override
    public SendResult send(Notification n, AuditLog audit) {
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
        return SendResult.ok();
    }
}
