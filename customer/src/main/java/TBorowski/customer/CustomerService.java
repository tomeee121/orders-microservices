package TBorowski.customer;

import TBorowski.clients.fraud.FraudCheckResponse;
import TBorowski.clients.fraud.FraudClient;
import TBorowski.clients.notification.FeignNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final FraudClient fraudClient;
    private final FeignNotification feignNotification;


    public void register(CustomerRegistratonRequest request) {

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        repository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        TBorowski.clients.notification.NotificationRequest requestToNotificate = new TBorowski.clients.notification.NotificationRequest(customer.getId(), customer.getEmail(), String.format("Hi, welcome to %s site. Your order has been placed!", "TB"));
        feignNotification.sendNotification(requestToNotificate);
    }
}
