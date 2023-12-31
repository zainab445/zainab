public class CustomerService {
    public void createAccount(Customer customer) {
        DataStorage.customers.add(customer);
    }
}
