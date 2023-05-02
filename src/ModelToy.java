public class ModelToy extends Model {
    Warehouse<Product> sklad;

    public ModelToy() {
        this.sklad = new ToyDepot();
        this.file = "toys1.json";
    }


    public boolean addWarehouseData(String filename, mode mod) {
        if (filename.isEmpty()) {
            filename = this.file;
        }
        boolean loadstatus = false;
        if (mod.equals(mode.renew)){
            sklad.clearWarehouse();
        }
        for (Object pr : js.Loading(filename)) {
            if (pr instanceof Product ) {
                sklad.AddProduct((Product) pr);
                loadstatus = true;
            } else {
//                System.out.println("Загрузка не удалась!");
                loadstatus = false;
            }
        }
        if (loadstatus) {
            this.file = filename;
        }
        return loadstatus;
    }

    public void saveJson(String filename, boolean append) {
        if (filename.isEmpty()) {
            filename = this.file;
        }
        js.Saving(filename, sklad, append);
    }

    public void saveCSV(StringBuilder str) {
        csv.saveTxt("prizeitems.csv", str);
    }

    public Integer totalProductAmount() {
        int sum = 0;
        for (Product product : sklad.getStorage()) {
            sum = sum + product.getVolume();
        }
        return sum;
    }


}