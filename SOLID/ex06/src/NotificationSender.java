public interface NotificationSender {
    SendResult send(Notification n, AuditLog audit);
}
