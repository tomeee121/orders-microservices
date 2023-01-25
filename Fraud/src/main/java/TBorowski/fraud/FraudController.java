package TBorowski.fraud;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService service;
    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        System.out.println("callllleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeddddddddddddddddddddd");
        boolean isFraudulentCustomer = service.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer of id {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
