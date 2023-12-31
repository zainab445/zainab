 public class OrderController {
    private OrderService orderService;

    public void placeOrder(Order order) {
        orderService.placeOrder(order);
    }

    public void shipOrder( Long id) {
        //orderService.shipOrder(id);
    }

}
