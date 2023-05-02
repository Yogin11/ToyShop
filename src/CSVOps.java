import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVOps extends FilesFormat{
    public CSVOps() {
    }

    @Override
    public void Saving(String filename, Warehouse<Product> depot, boolean append) {
    }

    @Override
    public <T> ArrayList<T> Loading(String filename) {
        return null;
    }

   public void saveTxt(String filename, StringBuilder text) {
        try (FileWriter writer = new FileWriter(filename, true)) {
                writer.write(text + "\n");
            writer.flush();
            System.out.println("Сохранено в файл " + filename);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
