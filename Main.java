// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

class User {
    private String username;
    private String password;
    private String mobileNumber;
    private boolean isBankUser;
    private double balance;

    public User(String username, String password, String mobileNumber, boolean isBankUser) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.isBankUser = isBankUser;
        this.balance = 1000.0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public boolean isBankUser() {
        return isBankUser;
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        balance -= amount;
    }
}


class BillPayment {

    public static void payBill(User user, double amount, String billType) {

        if (user.getBalance() >= amount) {

            user.deductBalance(amount);
            System.out.println("Bill payment successful. Remaining balance: $" + user.getBalance());
        } else {
            System.out.println("Insufficient funds. Bill payment failed.");
        }
    }
}

class UserManager {

    private static Map<String, String> mobileNumberOTPMap = new HashMap<>();
    public static Map<String, User> users = new HashMap<>();

    public static void registerUser(String username, String password, String mobileNumber, boolean isBankUser) {

        String otp = generateOTP();
        mobileNumberOTPMap.put(mobileNumber, otp);
        sendOTP(mobileNumber, otp);


        User user = new User(username, password, mobileNumber, isBankUser);
        users.put(username, user);
        System.out.println("User registered successfully!");
    }

    public static void loginUser(String username, String password) {
        Scanner scanner = new Scanner(System.in);
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome, " + user.getUsername() + "!");
            System.out.print("\n1- Transfer to Wallet using the mobile number ");
            System.out.print("\n2- Transfer to Another instapay account ");
            System.out.print("\n3- Inquire about his balance ");
            System.out.print("\n4- Pay bills ");
            if(user != null && user.isBankUser()==true)
            {
                System.out.print("\n5- Transferring to bank account ");
            }
            System.out.print("\n0- logout\n");
            int option_1 = scanner.nextInt();
            switch(option_1)
            {
                case 0:
                    break;
                case 1:
                    System.out.print("Enter receiver's mobile number: ");
                    String receiverMobileNumber = scanner.next();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    TransactionManager.transferToWallet(user, receiverMobileNumber, transferAmount);
                    break;
                case 2:
                    System.out.print("Enter receiver's username: ");
                    String receiverUsername = scanner.next();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmountInstapay = scanner.nextDouble();
                    TransactionManager.transferToInstapay(user, receiverUsername, transferAmountInstapay);
                    break;

                case 3:
                    TransactionManager.inquireBalance(user);
                    break;
                case 4:
                    System.out.print("Enter bill amount: ");
                    double billAmount = scanner.nextDouble();
                    System.out.print("Enter bill type (Gas/Electricity/Water): ");
                    String billType = scanner.next();
                    TransactionManager.payBill(user, billAmount, billType);
                    break;
                case 5:
                    break;
            }

        } else {
            System.out.println("Invalid credentials. Please try again.");

            //return 0;
        }
    }

    private static String generateOTP() {

        return String.format("%04d", new Random().nextInt(10000));
    }

    private static void sendOTP(String mobileNumber, String otp) {

        System.out.println("OTP sent to " + mobileNumber + ": " + otp);
    }

    public static void payBill(User user, double amount, String billType) {
        BillPayment.payBill(user, amount, billType);
    }

    public static User getUserByUsername(String username) {
        return users.get(username);
    }

    public static Map<String, User> getUsers() {
        return users;
    }



}


class TransactionManager {

    public static void transferToWallet(User sender, String receiverMobileNumber, double amount) {
        User receiver = UserManager.getUsers().values()
                .stream()
                .filter(user -> user.getMobileNumber().equals(receiverMobileNumber))
                .findFirst()
                .orElse(null);

        if (receiver != null) {
            if (sender.getBalance() >= amount) {
                sender.deductBalance(amount);
                receiver.deductBalance(-amount);  // Add amount to receiver's balance
                System.out.println("Transfer to wallet successful. Remaining balance: $" + sender.getBalance());
            } else {
                System.out.println("Insufficient funds. Transfer to wallet failed.");
            }
        } else {
            System.out.println("Receiver not found. Transfer to wallet failed.");
        }
    }

    public static void transferToInstapay(User sender, String receiverUsername, double amount) {
        User receiver = UserManager.getUserByUsername(receiverUsername);

        if (receiver != null) {
            if (sender.getBalance() >= amount) {
                sender.deductBalance(amount);
                receiver.deductBalance(-amount);  // Add amount to receiver's balance
                System.out.println("Transfer to Instapay account successful. Remaining balance: $" + sender.getBalance());
            } else {
                System.out.println("Insufficient funds. Transfer to Instapay account failed.");
            }
        } else {
            System.out.println("Receiver not found. Transfer to Instapay account failed.");
        }
    }

    public static void inquireBalance(User user) {
        System.out.println("Your current balance: $" + user.getBalance());
    }

    public static void payBill(User user, double amount, String billType) {
        UserManager.payBill(user, amount, billType);
    }
}


public class Main {
    public static void main(String[] args) {

        UserManager.registerUser("suad","147", "111", true);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Instapay!");
        int option=0;
        while(option !=3)
        {

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. End ");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            switch(option)
            {
                case 1:
                {
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    System.out.print("Enter mobile number: ");
                    String mobileNumber = scanner.next();
                    System.out.print("Is it a bank user? (true/false): ");
                    boolean isBankUser = scanner.nextBoolean();
                    UserManager.registerUser(username, password, mobileNumber, isBankUser);
                }
                break;
                case 2:
                {
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    UserManager.loginUser(username, password);

                }
                break;
                case 3:
                    break;
            }

        }

        scanner.close();
    }
}

