public class SmsSender implements NotificationSender {
    @Override
    public SendResult send(Notification n, AuditLog audit) {
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
        return SendResult.ok();
    }
}
