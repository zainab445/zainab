public class CustomerController {
    private CustomerService customerService;
    public void createAccount(Customer customer) {
        customerService.createAccount(customer);
    }

}
