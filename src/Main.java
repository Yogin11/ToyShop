public class Main {
    public static void main(String[] args) {

        System.out.println("По умолчанию загружаются данные со склада (из файла toys1.json) ");
        ControllerToy ctrl = new ControllerToy();
        ctrl.selectMenu("0");

    }
}