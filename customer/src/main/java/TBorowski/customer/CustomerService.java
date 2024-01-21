package TBorowski.customer;

import TBorowski.amqp.RabbitMQMessageProducer;
import TBorowski.clients.fraud.FraudCheckResponse;
import TBorowski.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
//    private final FeignNotification feignNotification;



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
//        feignNotification.sendNotification(requestToNotificate);
        rabbitMQMessageProducer.publish(
                requestToNotificate,
                "internal.exchange",
                "internal.notification.routing-key");
    }
}
