import java.util.ArrayList;

public abstract class Warehouse<Product> {
    private final ArrayList<Product> storage;

    public Warehouse() {
        storage = new ArrayList<>();
    }

    public void AddProduct(Product product) {
        storage.add(product);
    }
    public void clearWarehouse() {
        storage.clear();
    }
    public ArrayList<Product> getStorage() {
        return storage;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Product product : getStorage()) {
            str.append(product).append("\n");
        }
        return str.toString();
    }
}

