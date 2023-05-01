import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JsonOps extends FilesFormat{
    public JsonOps() {
    }

    @Override
    public <T> ArrayList<T> Loading(String filename) {
//    public  ArrayList<Product> Loading(String filename) {
        Gson gson = new Gson();
            try (JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(filename ), StandardCharsets.UTF_8))){
            ArrayList<T> productsArr = new ArrayList<>();
            reader.beginArray();
            while (reader.hasNext()) {
               T product = gson.fromJson(reader,Product.class );
                productsArr.add(product);
            }
            return (ArrayList<T>) productsArr ;
        } catch (IOException e) {
            System.out.println("Ошибка ввода файла ");
            // System.out.println("Parsing error");
        }
        return null;

    }

    @Override
    public void Saving(String filename, Warehouse<Product> storage) {
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        String json = GSON.toJson(storage.getStorage());

//        String json = GSON.toJson(storage.getStock());

        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream(filename, false), StandardCharsets.UTF_8))) {
            out.write(json);
            out.flush();
            System.out.println("Записано ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
