import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ControllerToy extends Controller {
    MenuToy menu;
    ArrayList<Product> prizelist;
    ModelToy model;

    public ControllerToy() {
        menu = new MenuToy();
        model = new ModelToy();
        model.addWarehouseData("", Model.mode.renew);
        prizelist = new ArrayList<>();
    }

    public void selectMenu(String choice) {

        choice = menu.select(menu.getAction());

        switch (choice) {
            case "1":
                showAll();
                break;
            case "2":
                AddProductCtrl();
                break;
            case "3":
                changeFrequency();
                break;
            case "4":
                prizelist.clear();
                prizeListGenerate();
                break;
            case "5":
                showPrizeList();
                break;
            case "6":
                getPrizeProduct();
                break;
            case "7":
                emptyPrizeList();
                break;
            case "8":
                loadWarehouseData();
                break;
            case "9":
                emptyWarehouseData();
                break;
            case "10":
                model.saveJson();
                menu.confirmMessage();
                break;

        }
        selectMenu("0");
    }

    public void showAll() {
        if (!model.sklad.getStorage().isEmpty()) {
            menu.showLists(model.sklad.getStorage());
        } else {
            menu.unconfirmMessage();
        }
    }

    public void loadWarehouseData() {
        boolean status = false;
        String filename = menu.loadDataDialog("1");
        File f = new File(filename);
        if (f.exists() && !filename.isEmpty()) {
            String updateselection = menu.loadDataDialog("2");
            if (updateselection.equals("1")) {
                status = model.addWarehouseData(filename, ModelToy.mode.add);
            } else if (updateselection.equals("2")) {
                status = model.addWarehouseData(filename, ModelToy.mode.renew);
            }
        } else if (filename.isEmpty()) {
            status = model.addWarehouseData(filename, ModelToy.mode.renew);
        }
        if (!status) {
            menu.unconfirmMessage();
        } else
            menu.confirmMessage();
    }

    public void AddProductCtrl() {
        String name = menu.newToyEntryDialog("1").toLowerCase();
        int num = Integer.parseInt(menu.newToyEntryDialog("2"));
        int frequency = Integer.parseInt(menu.newToyEntryDialog("3"));
        boolean found = false;
        for (Product item : model.sklad.getStorage()) {
            if (item.getProductName().toLowerCase().equals(name)) {
                found = true;
                menu.newToyEntryDialog("4");
                item.setVolume(item.getVolume() + num);
                item.setLottFrequency(frequency);
            }
        }
        if (!found) {
            model.sklad.AddProduct(new Toy("", name, "", frequency, "", num));
        }
        model.saveJson();
    }

    public void changeFrequency() {
        String findname = menu.frequencyChgDialog("1");
        boolean found = false;
        for (Product item : model.sklad.getStorage()) {
            if (item.getProductName().equalsIgnoreCase(findname)) {
                found = true;
                Integer newFreq = Integer.parseInt(menu.frequencyChgDialog("2"));
                item.setLottFrequency(newFreq);
                menu.confirmMessage();
            }
        }
        if (!found) menu.unconfirmMessage();
    }

    public void prizeListGenerate() {
        ArrayList<Product> arr = new ArrayList<>();
        menu.prizeListGenerateDialog("1");
        System.out.println(model.totalProductAmount());
        try {
            int number = Integer.parseInt(menu.prizeListGenerateDialog("2"));
            if (number > model.totalProductAmount()) {
                menu.prizeListGenerateDialog("3");
            } else {
                defineToy(number);
            }
        }
        catch (NumberFormatException e){
            menu.unconfirmMessage();
        }

    }


    public void defineToy(int number) {
        HashMap<Product, Double> map = new HashMap<>();
        double sumVolWeight = 0.0;
        double volumeWeight;
        for (Product product : model.sklad.getStorage()) {
            volumeWeight = (double) (product.getVolume() * product.getLottFrequency()) / 100; // умножаем кол-во каждой игрушки на частоту выпадения
            map.put(product, volumeWeight);
            sumVolWeight = sumVolWeight + volumeWeight;
        }
        for (Map.Entry<Product, Double> prodVolWeight : map.entrySet()) {
            double eachProductProbability = prodVolWeight.getValue() / sumVolWeight;
            // Добавляем в map связки "Product - вероятность выпадения"(с учетом веса и количества игрушек на складе)
            map.put(prodVolWeight.getKey(), eachProductProbability);
        }
        double max = 0.0;
        Product probableproduct = new Product();
        for (Product product : model.sklad.getStorage()) {
            int eachProductVolume = (int) Math.round(number * map.get(product));
            double nonZeroProduct = number * map.get(product);
            if (nonZeroProduct < 0.5 && max < nonZeroProduct) {
                max = number * map.get(product);
                probableproduct = product;
            }
            int count = 0;
            while (count < eachProductVolume && product.getVolume() > 0 && prizelist.size() < number) {
                prizelist.add(product);
                product.setVolume(product.getVolume() - 1);
                count++;
            }
        }
        if (prizelist.size() < number) {
            if (!prizelist.contains(probableproduct)) {
                prizelist.add(probableproduct);
            }
            probableproduct.setVolume(probableproduct.getVolume() - 1);
            defineToy(number);
        } else {
            System.out.println();
            Collections.shuffle(prizelist);
            if (!model.sklad.getStorage().isEmpty())
                model.addWarehouseData("", ModelToy.mode.renew);
            showPrizeList();
        }
    }

    public void showPrizeList() {
        if (!prizelist.isEmpty()) {
            menu.prizeListShow("1");
            for (Product pr : prizelist) {
                System.out.println(pr.getProductName());
            }
        } else {
            menu.prizeListShow("2");
        }
    }

    public void getPrizeProduct() {
        if (!prizelist.isEmpty()) {
            Product item = prizelist.get(0);
            StringBuilder str = new StringBuilder();
            prizelist.remove(0);
            for (Product pr : model.sklad.getStorage()) {
                if (pr.equals(item)) {
                    pr.setVolume(pr.getVolume() - 1); // Уменьшаем количество на складе
                    break;
                }
            }
            str.append(item.toString()).append("\n");
            menu.prizeListShow("3");
            System.out.println(str);
            model.saveCSV(str);
        } else {
            menu.prizeListShow("2");
        }
    }

    public void emptyPrizeList() {
        prizelist.clear();
        menu.confirmMessage();
    }

    public void emptyWarehouseData() {
        model.sklad.clearWarehouse();
        menu.confirmMessage();
    }
}
