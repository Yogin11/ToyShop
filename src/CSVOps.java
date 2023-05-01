import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVOps extends FilesFormat{
    public CSVOps() {
    }

    @Override
    public void Saving(String filename, Warehouse<Product> depot) {
    }

    @Override
    public <T> ArrayList<T> Loading(String filename) {
        return null;
    }

   public boolean saveTxt(String filename, StringBuilder text) {

        try (FileWriter writer = new FileWriter(filename, true)) {
//            for (Note data : book.getAllnotes()) {
//                String line = String.join(";", text);
                writer.write(text + "\n");
//            }

            writer.flush();
            System.out.println("Сохранено в CSV файл");
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
