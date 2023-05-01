public class Model {
    Warehouse<Product> sklad;
    JsonOps js;
    CSVOps csv;

    public Model() {
        this.sklad = new ToyDepot();
        this.js = new JsonOps();
        this.csv = new CSVOps();
    }

    public void addProductModel() {
        for (Object pr : js.Loading("try2.json")) {
            System.out.println();
            if (pr instanceof Product) {
                sklad.AddProduct((Product) pr);
            } else
                System.out.println("Загрузка не удалась!");
        }
    }

    public void saveJson() {
        js.Saving("try2.json", sklad);
    }
    public void saveCSV(StringBuilder str) {
       csv.saveTxt("prizeitems.csv", str );
    }


}