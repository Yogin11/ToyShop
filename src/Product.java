public class Product extends ProdClass {
    private String productType;
    private String productName;
    private String brand;
    private Integer lottFrequency;
    private String productCategory;
    private Integer volume;

    public Product(String productType, String productName, String brand, Integer lottFrequency, String productCategory, Integer volume) {
        this.productType = productType;
        this.productName = productName;
        this.brand = brand;
        this.lottFrequency = lottFrequency;
        this.productCategory = productCategory;
        this.volume = volume;
    }

    public Product() {
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getLottFrequency() {
        return lottFrequency;
    }

    public void setLottFrequency(Integer lottFrequency) {
        this.lottFrequency = lottFrequency;
    }

    public String getCategory() {
        return productCategory;
    }

    public void setCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
//        txt.append(" Тип товара: ").append(getProductType());
//        txt.append(", Категория: ").append(getCategory());
        txt.append("  Название: ").append(getProductName());
        txt.append(", Частота выпадения: ").append(getLottFrequency()).append(" %");
        txt.append(", Количество: ").append(getVolume()).append(" шт.");


        return txt.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product p = (Product) obj;
            return this.getProductName().equals(p.getProductName());
        } else
            return false;
    }
}


