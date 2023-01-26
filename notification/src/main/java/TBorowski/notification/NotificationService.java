package TBorowski.notification;

import TBorowski.clients.notification.FeignNotification;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void save(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder().message(notificationRequest.message()).toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail()).sentAt(LocalDateTime.now()).sender("TB").build();
        log.info("Notification issued: {}", notification);
        notificationRepository.save(notification);
    }
}
