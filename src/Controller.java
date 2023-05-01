import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Controller {
    MenuToy menu;
    Model model;
    ArrayList<Product> prizelist;

    public Controller() {
        menu = new MenuToy();
        model = new Model();
        model.addWarehouseData();
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

        }
        selectMenu("0");

    }

    public void showAll() {
        menu.showLists(model.sklad.getStorage());
//        System.out.println(model.sklad);
    }

    public void loadWarehouseData() {
        model.addWarehouseData();
    }


    public void AddProductCtrl() {
        String name = menu.newToyEntryDialog("1").toLowerCase();
        int num = Integer.parseInt(menu.newToyEntryDialog("2"));
        int frequency = Integer.parseInt(menu.newToyEntryDialog("3"));
        boolean found = false;
        for (Product item : model.sklad.getStorage()) {
            if (item.getProductName().toLowerCase().equals(name)) {
                found = true;
                System.out.println("Такие игрушки уже есть на складе, добавляем игрушки к существующим");
                System.out.println("Частоту выпадения меняем на вновь введенную");
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
            if (item.getProductName().toLowerCase().equals(findname)) {
                found = true;
                Integer newFreq = Integer.parseInt(menu.frequencyChgDialog("2"));
                item.setLottFrequency(newFreq);
                menu.confirmMessage();
            }
        }
        if (!found) menu.unconfirmMessage();
    }

    public void prizeListGenerate() {
        int number = Integer.parseInt(menu.prizeListGenerateDialog("1"));
        if (number > model.sklad.totalProductAmount()) {
            System.out.println("Нет такого количества товаров всего.");
        } else {
            HashMap<Product, Double> map = new HashMap<>();
            double sumVolWeight = 0.0;
            double volumeWeight = 0.0;
            for (Product product : model.sklad.getStorage()) {
                volumeWeight = (double) (product.getVolume() * product.getLottFrequency()) / 100; // умножаем кол-во на частоту выпадения
                map.put(product, volumeWeight);
                sumVolWeight = sumVolWeight + volumeWeight;
            }
            for (Map.Entry<Product, Double> line : map.entrySet()) {
                map.put(line.getKey(), line.getValue() / sumVolWeight);
            }
            for (Product product : model.sklad.getStorage()) {
                int eachProductVolume = (int) Math.round(number * map.get(product));
                int count = 0;
                while (count < eachProductVolume && count<product.getVolume()) {
                    prizelist.add(product);
                    count++;
                }
            }
            System.out.println();
            Collections.shuffle(prizelist);
            System.out.println("Призовые игрушки: ");
            for (Product pr : prizelist) {
                System.out.println(" - " + pr.getProductName());
            }
        }
    }

    public void showPrizeList() {
        if (!prizelist.isEmpty()) {
            System.out.println(" Cписок призовых игрушек: ");
            for (Product pr : prizelist) {
                System.out.println(pr.getProductName());
            }
        } else {
            System.out.println("Список призов пуст ");
        }
    }

    public void getPrizeProduct() {
        if (!prizelist.isEmpty()) {
            Product item = prizelist.get(0);
            StringBuilder str = new StringBuilder();
            prizelist.remove(0);
            for (Product pr : model.sklad.getStorage()) {
                if (pr.equals(item)){
                    pr.setVolume(pr.getVolume()-1);             // Уменьшаем количество на складе
                    break;
                }
            }
            str.append(item.toString()).append("\n");
            System.out.println("Выбираем и сохраняем в список в файл: ");
            System.out.println(str);
            model.saveCSV(str);
        } else {
            System.out.println("Список призов пуст ");
        }
    }

    public void emptyPrizeList() {
        prizelist.clear();
        menu.confirmMessage();
    }

    public void emptyWarehouseData() {
        model.sklad.getStorage().clear();
        menu.confirmMessage();

    }
}
