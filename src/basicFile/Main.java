package basicFile;
import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args[0].toLowerCase().equals("demo")) {
            demo();
        } else if (args[0].toLowerCase().equals("input")) {
            if(args.length == 2){
                inputOneFile(args[1]);
            } else if (args.length == 3){
                inputTwoFiles(args[1], args[2]);
            }
        } else if (args[0].toLowerCase().equals("window")) {
            Frame frame = new Frame();
            frame.setVisible(true);
        } else {
            showHelpMessage();
        }
    }

    private static void demo(){
        AxineLinkedList<Integer> axineList = new AxineLinkedList<>();
        // для нечетного числа элементов
        for (int i = 0; i < 10; i++) {
            axineList.add(i);
        }
        System.out.println("Демонстрация реверса нечетного числа элементов:\n" + Arrays.toString(axineList.toArray()));
        axineList.reverseAll();
        System.out.println(Arrays.toString(axineList.toArray()));
        axineList.clear();
        // для четного числа элементов
        for (int i = 0; i < 11; i++) {
            axineList.add(i);
        }
        System.out.println("\nДемонстрация реверса четного числа элементов:\n" + Arrays.toString(axineList.toArray()));
        axineList.reverseAll();
        System.out.println(Arrays.toString(axineList.toArray()));
    }

    private static void inputOneFile(String path){
        AxineLinkedList<Object> axine = new AxineLinkedList<>();
        System.out.println("Прямой: " + Arrays.toString(new utils.Files<Integer>().readFile(new File(path))));
        for (Object obj : new utils.Files<Integer>().readFile(new File(path))){
            axine.add(obj);
        }
        axine.reverseAll();
        System.out.println("Обратный: " + Arrays.toString(axine.toArray()));
    }

    private static void inputTwoFiles(String path1, String path2){
        AxineLinkedList<Object> axine = new AxineLinkedList<>();
        for (Object obj : new utils.Files<Integer>().readFile(new File(path1))){
            axine.add(obj);
        }
        axine.reverseAll();
        new utils.Files<Integer>().writeFile(new File(path2), axine.toArray());
    }
    private static void showHelpMessage(){
        System.out.print("Для работы с программой, пожалуйста, введите аргумент:\n " +
                "\thelp - выводит данное сообщение\n" +
                "\tdemo - выводит демонстрацию реверса элементов List\n" +
                "\tinput file - выводит в консоль элементы из file.txt в прямом и обратном порядке\n" +
                "\tinput file1 file2 - сохраняет в file2.txt элементы из file1.txt в обратном порядке\n" +
                "\twindow - открывает оконное приложение");
    }
}