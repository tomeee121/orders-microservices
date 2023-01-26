package TBorowski.fraud;

import TBorowski.notification.NotificationRequest;
import TBorowski.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FraudCheckService {
    private final FraudCheckHistoryRepository repository;
    public boolean isFraudulentCustomer(Integer customerId) {
        repository.save(FraudCheckHistory.builder()
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .customerId(customerId)
                .build());
        //hardcoded for now;without fetching from external api

        return false;
    }
}
