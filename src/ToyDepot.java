public class ToyDepot extends Warehouse<Product>{
    public ToyDepot() {
    }
    @Override
    public Integer totalProductAmount() {
        int sum = 0;
        for (Product product : getStorage()) {
            sum = sum + product.getVolume();
        }
        return sum;
    }
}
