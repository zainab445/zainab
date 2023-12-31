
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public List<Product> listAllProducts() {
        return DataStorage.products;
    }
}