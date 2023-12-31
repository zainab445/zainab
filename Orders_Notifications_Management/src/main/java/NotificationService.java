public class NotificationService {
    public void sendNotification(Notification notification) {
        DataStorage.notifications.add(notification);
        System.out.println("Notification sent to " + notification.getEmail());
    }
}
