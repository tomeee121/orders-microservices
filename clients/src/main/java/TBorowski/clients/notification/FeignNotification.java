package TBorowski.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification",
            url = "${clients.notification.url}")
public interface FeignNotification {
    @PostMapping(path = "api/v1/notification")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);
}
