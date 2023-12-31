import java.util.List;
public class ProductController {
    private ProductService productService;
    public List<Product> listAll() {
        return productService.listAllProducts();
    }
}



public class OrderController {
    private OrderService orderService;

    public void placeOrder(Order order) {
        orderService.placeOrder(order);
    }

    public void shipOrder( Long id) {
        orderService.shipOrder(id);
    }
}

// NotificationController.java
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody Notification notification) {
        notificationService.sendNotification(notification);
    }
}
