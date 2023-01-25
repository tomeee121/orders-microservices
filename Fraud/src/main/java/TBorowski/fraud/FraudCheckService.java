package TBorowski.fraud;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
