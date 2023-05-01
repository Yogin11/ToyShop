public class SoftToy extends Toy{

    public SoftToy(String productType, String productName, String brand, Integer lottFrequency, String productCategory, Integer volume) {
        super(productType, productName, brand, lottFrequency, productCategory, volume);
        this.setCategory("Мягкие игрушки");
    }

    public SoftToy(){
        super();
    }

}
