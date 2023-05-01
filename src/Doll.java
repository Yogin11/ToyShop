public class Doll extends Toy{
    public Doll(String productType, String productName, String brand, Integer lottFrequency, String productCategory, Integer volume) {
        super(productType, productName, brand, lottFrequency, productCategory, volume);
        this.setCategory("Куклы");
    }
}
