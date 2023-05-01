//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;

import java.util.ArrayList;

public class MenuToy extends View {

    public MenuToy() {
    }

    @Override
    public void fillActionMenu() {
        action.put("1", "Показать все игрушки на складе");
        action.put("2", "Добавить игрушку на склад");
        action.put("3", "Изменить частоту выпадения игрушки в лоттерее");
        action.put("4", "Составить список призовых игрушек.");
        action.put("5", "Показать список призовых игрушек.");
        action.put("6", "Получить призовую игрушку и записать в список (в файл)");
        action.put("7", "Очистить список призовых игрушек.");
        action.put("8", "Загрузить данные со склада");
        action.put("9", "Очистить данные со склада");
        action.put("10", "Выйти из программы");
    }

    public String newToyEntryDialog(String choice) {
        if (choice.equals("1")) {
            setSubmenu("1", "Введите название игрушки");
            setSubmenu("2", "Введите количество");
            setSubmenu("3", "Введите частоту выпадения игрушки в %");
        }
        System.out.println(getSubmenu().get(choice));
        return getInput();
    }

    public String frequencyChgDialog(String choice) {
        if (choice.equals("1")) {
            System.out.println("Введите название игрушки");
            return getInput().toLowerCase();
        } else if (choice.equals("2")) {
            System.out.println("Введите новую частоту выпадения в % ");
            return getInput();
        }
        return "";
    }

    public String prizeListGenerateDialog(String choice) {
        if (choice.equals("1")) {
            System.out.println("Введите какое количество игрушек должно быть призовых");
            return getInput().toLowerCase();
        } else if (choice.equals("2")) {
            System.out.println("Введите новую частоту выпадения в % ");
            return getInput();
        }
        return "";
    }
    public void showLists(ArrayList<Product> data){
        System.out.format("%-25s%-35s%-18s\n",   "Название", "Частота выпадения,% ","  Количество, шт." );
        System.out.println("-----------------------------------------------------------------------------");
        for (Product item: data){
            System.out.format("%-35s%-35s%-15s\n",   item.getProductName(),item.getLottFrequency(), item.getVolume() );
        }

    }

}