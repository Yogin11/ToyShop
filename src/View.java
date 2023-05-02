import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class View {

    HashMap<String, String> action;
    Scanner sc;
    HashMap<String, String> submenu;

    public View() {
        action = new HashMap<>();
        submenu = new HashMap<>();
        sc = new Scanner(System.in, StandardCharsets.UTF_8);
        fillActionMenu();
    }

    public String confirmMessage() {
        System.out.println("Выполнено");
        return getInput();
    }

    public String unconfirmMessage() {
        System.out.println("Данных не найдено");
        return getInput();
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
    public String select(HashMap<String, String> dict) {

        printMenu();
        boolean isExit = false;
        while (!isExit) {
            try {
                String s = getInput();
                isExit = s.equals("20");
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

    public void fillActionMenu(){

    }

    public void printMenu() {
        System.out.println();
        System.out.println("======== Главное меню ========\n");
        for (Map.Entry<String, String> line : getAction().entrySet()) {
            System.out.println(line.getKey() + "." + line.getValue());
        }
        System.out.println("20." + "Выйти из программы.");
        System.out.println();
    }
    public String getInput() {

        return sc.nextLine();
    }

}

