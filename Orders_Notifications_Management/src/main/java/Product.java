

public class Product {

    private String serialNumber;
    private String name;
    private String vendor;
    private String category;
    private double price;

    public Product(String serialNumber, String name, String vendor, String category, double price) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
    }
    public String getSerialNumber() {
        return serialNumber;
    }

    // Setter for serialNumber
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for vendor
    public String getVendor() {
        return vendor;
    }

    // Setter for vendor
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    // Setter for category
    public void setCategory(String category) {
        this.category = category;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(double price) {
        this.price = price;
    }
}
