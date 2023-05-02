//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;

import java.util.ArrayList;

public class MenuToy extends View {

    public MenuToy() {
    }

    @Override
    public void fillActionMenu() {
        action.put("1", "Показать все игрушки на складе.");
        action.put("2", "Добавить игрушку на склад.");
        action.put("3", "Изменить частоту выпадения игрушки в лотерее.");
        action.put("4", "Составить список призовых игрушек.");
        action.put("5", "Показать список призовых игрушек.");
        action.put("6", "Получить призовую игрушку и записать в список (в файл).");
        action.put("7", "Очистить список призовых игрушек.");
        action.put("8", "Загрузить данные со склада (из файла).");
        action.put("9", "Очистить данные со склада.");
        action.put("10", "Сохранить все изменения в файл.");

    }

    public String newToyEntryDialog(String choice) {
        if (choice.equals("1")) {
            setSubmenu("1", "Введите название игрушки");
            setSubmenu("2", "Введите количество");
            setSubmenu("3", "Введите частоту выпадения игрушки в %");
            setSubmenu("4", "Такие игрушки уже есть на складе, добавляем игрушки к существующим. \n" +
                    "Частоту выпадения меняем на вновь введенную");
        }
        System.out.println(getSubmenu().get(choice));
        return getInput();
    }

    public String frequencyChgDialog(String choice) {
        if (choice.equals("1")) {
            setSubmenu("1", "Введите название игрушки");
            setSubmenu("2", "Введите новую частоту выпадения в % ");
        }
        System.out.println(getSubmenu().get(choice));
        return getInput();
    }

    public String prizeListGenerateDialog(String choice) {
        if (choice.equals("1")) {
            setSubmenu("1", "Всего на складе игрушек: ");
            setSubmenu("2", "Введите какое количество игрушек должно быть призовых");
            setSubmenu("3", "Нет такого количества товаров на складе.");
        }
        System.out.println(getSubmenu().get(choice));
        if (choice.equals("1")) {
            return "";
        } else {
            return getInput();
        }
    }

    public void prizeListShow(String choice) {
        setSubmenu("1", "Cписок призовых игрушек: ");
        setSubmenu("2", "Список призов пуст ");
        setSubmenu("3", "Выбираем и сохраняем в файл: prizeitems.csv ");
        System.out.println(getSubmenu().get(choice));
    }

    public void showLists(ArrayList<Product> data) {
        System.out.format("%-25s%-35s%-18s\n", "Название", "Частота выпадения,% ", "  Количество, шт.");
        System.out.println("-----------------------------------------------------------------------------");
        for (Product item : data) {
            System.out.format("%-35s%-35s%-15s\n", item.getProductName(), item.getLottFrequency(), item.getVolume());
        }

    }

    public String loadDataDialog(String choice) {
        if (choice.equals("1")) {
            setSubmenu("1", "Введите название Json файла. По умолчанию данные загрузятся из текущего файла. ");
            setSubmenu("2", "Выберите вариант - 1-добавить к загруженным данным, 2-очистить текущие и загрузить заново ");
        }
        System.out.println(getSubmenu().get(choice));
        return getInput();
    }
    public String saveDataDialog(String choice) {
        if (choice.equals("1")) {
            setSubmenu("1", "Введите название Json файла. По умолчанию данные сохранятся в текущий файл ");
            setSubmenu("2", "Выберите вариант - 1-добавить к существующим в файле данным, 2- переписать файл данными ");
        }
        System.out.println(getSubmenu().get(choice));
        return getInput();
    }
}