package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Files<E> {


    /*public static int[] readFile(File path){
        Scanner sc;
        ArrayList<Integer> tableArray = new ArrayList<>();
        try {
            sc = new Scanner(path);
            while(sc.hasNext()) {
                tableArray.add(Integer.parseInt(sc.next()));
            }
        }catch(Exception e) {
            System.err.print(true);
        }

        int[] table = new int[tableArray.size()];
        for(int i = 0; i < table.length; i++) {
            table[i] = tableArray.get(i);
        }
        return table;
    }
    */
    public void writeFile(File path, Object[] inputTabel) {
        Formatter f = null;
        try {
            f = new Formatter(path);
            for(int i = 0; i < inputTabel.length; i++) {
                f.format(inputTabel[i] + " ");
            }
            f.close();
        }catch(Exception e) {}
    }

    public Object[] readFile(File path){
        Scanner sc;
        ArrayList<Object> tableArray = new ArrayList<>();
        try {
            sc = new Scanner(path);
            while(sc.hasNext()) {
                if(sc.hasNextInt()) {
                    tableArray.add(sc.nextInt());
                } else if(sc.hasNext()){
                    tableArray.add(sc.next());
                }
            }
        }catch(Exception e) {
            System.err.print("Ошибка чтения");
            e.printStackTrace();
        }

        Object[] table = new Object[tableArray.size()];
        for(int i = 0; i < table.length; i++) {
            table[i] = tableArray.get(i);
        }
        return table;
    }
}