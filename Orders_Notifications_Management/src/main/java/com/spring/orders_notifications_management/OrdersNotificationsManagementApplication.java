package com.spring.orders_notifications_management;
import com.spring.ProductService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdersNotificationsManagementApplication {

    public static void main(String[] args) {

        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        NotificationService notificationService = new NotificationService();

        // Create and add a product
        Product product = new Product(/* initialize fields */);
        DataStorage.products.add(product);

        // Create and add a customer
        Customer customer = new Customer(/* initialize fields */);
        customerService.createAccount(customer);

        // Place an order
        Order order = new Order(customer, List.of(product));
        orderService.placeOrder(order);

        // Send a notification
        Notification notification = new Notification("Your order is placed!", customer);
        notificationService.sendNotification(notification);
        SpringApplication.run(OrdersNotificationsManagementApplication.class, args);
    }

}
