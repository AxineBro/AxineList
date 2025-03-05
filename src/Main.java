import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AxineLinkedList<Integer> axineList = new AxineLinkedList<>();
        // для нечетного числа элементов
        for(int i = 0; i < 10; i++){
            axineList.add(i);
        }
        System.out.println("Демонстрация реверса нечетного числа элементов:\n" +Arrays.toString(axineList.toArray()));
        axineList.reverseAll();
        System.out.println(Arrays.toString(axineList.toArray()));
        axineList.clear();
        // для четного числа элементов
        for(int i = 0; i < 11; i++){
            axineList.add(i);
        }
        System.out.println("\nДемонстрация реверса четного числа элементов:\n" +Arrays.toString(axineList.toArray()));
        axineList.reverseAll();
        System.out.println(Arrays.toString(axineList.toArray()));
    }
}