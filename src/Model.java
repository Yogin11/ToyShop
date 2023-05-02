public abstract class Model {
    String file;
    JsonOps js;
    CSVOps csv;
    public Model() {
        this.js = new JsonOps();
        this.csv = new CSVOps();

    }
    enum mode {
        add,
        renew
    }
    public abstract boolean addWarehouseData(String filename, mode mod);
    public abstract void saveJson(String filename, boolean append);
    public abstract void saveCSV(StringBuilder str);
}
