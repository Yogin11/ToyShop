import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private final HashMap<String, String> action;
    Scanner sc;
    private HashMap<String, String> submenu;

    public Menu() {
        action = new HashMap<>();
        submenu = new HashMap<>();
        action.put("1", "Показать все игрушки на складе");
        action.put("2", "Добавить игрушку");
        action.put("3", "Изменить частоту выпадения игрушки в лоттерее");
        action.put("4", "Составить список призовых игрушек.");
        action.put("5", "Показать список призовых игрушек.");
        action.put("6", "Получить призовую игрушку и записать в список (в файл)");
        action.put("7", "Очистить список призовых игрушек.");
        action.put("10", "Выйти из программы");
        sc = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    public HashMap<String, String> getAction() {
        return action;
    }

    public HashMap<String, String> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(String key, String value) {
        this.submenu.put(key, value);
    }

    public void printMenu() {

        System.out.println("\n======== Главное меню ========\n");
        for (Map.Entry<String, String> line : getAction().entrySet()) {
            System.out.println(line.getKey() + "." + line.getValue()) ;
        }
    }

    public String select(HashMap<String, String> dict) {
        printMenu();
        boolean isExit = false;
        while (!isExit) {
            try {
                String s = getInput();
                isExit = s.equals("10");
                if (isExit) {
                    sc.close();
                    System.exit(0);
                    break;
                }
                if (!dict.containsKey(s)) {
                    System.out.println("Неправильный ввод, попробуйте еще раз");
                    continue;
                }
                System.out.println();
                return s;
            } catch (NumberFormatException e) {
                System.out.println("Неправильный ввод, попробуйте еще раз");
            }
        }
        return "0";

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
    public String frequencyChgDialog(String choice){
        if (choice.equals("1")) {
            System.out.println("Введите название игрушки");
            return getInput().toLowerCase();
        }
        else if (choice.equals("2")) {
            System.out.println("Введите новую частоту выпадения в % ");
            return getInput();
        }
        return "";
    }
    public String prizeListGenerateDialog(String choice){
        if (choice.equals("1")) {
            System.out.println("Введите какое количество игрушек должно быть призовых");
            return getInput().toLowerCase();
        }
        else if (choice.equals("2")) {
            System.out.println("Введите новую частоту выпадения в % ");
            return getInput();
        }
        return "";
    }
    public String getInput() {

        return sc.nextLine();
    }
    public void confirmMessage(){
        System.out.println("Выполнено");
    }
    public void unconfirmMessage(){
        System.out.println("Не найдено");
    }
}