public class Toy extends Product {

    public Toy(String productType, String productName, String brand, Integer lottFrequency, String productCategory, Integer volume) {
        super(productType, productName, brand, lottFrequency, productCategory, volume);
        this.setProductType("Игрушки");
    }

    public Toy(){
    }

}