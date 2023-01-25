package TBorowski.fraud;

import TBorowski.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService service;
    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraudulentCustomer = service.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer of id {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
