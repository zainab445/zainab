public class OrderService {
    public void placeOrder(Order order) {
        DataStorage.orders.add(order);
    }
}
