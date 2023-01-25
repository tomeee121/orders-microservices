package TBorowski.customer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final RestTemplate restTemplate;
    private final CustomerRepository repository;

    public void register(CustomerRegistratonRequest request) {
//        Customer customer = Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email()).build();
//        repository.saveAndFlush(customer);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        final HttpEntity httpEntity = new HttpEntity(httpHeaders);
//        try {
//            ResponseEntity<FraudCheckResponse> offersDTOResponse = restTemplate.exchange("http://FRAUD/api/v1/fraud-check/1",
//                    HttpMethod.GET, httpEntity,
//                    FraudCheckResponse.class);
//            FraudCheckResponse fraudCheck = offersDTOResponse.getBody();
//            if (fraudCheck.isFraudster()) {
//                throw new IllegalStateException("Fraudster detected!");
//            }
//        } catch (ResourceAccessException e) {
//            throw new RuntimeException("cant get resourceeeeeeeeee"+e.getMessage());
//        }



        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        repository.saveAndFlush(customer);
        // todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }
}
