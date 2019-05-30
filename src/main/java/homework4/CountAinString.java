package homework4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CountAinString {
    public static char[] toCharArray;
    public static char symbol;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите строку");
        String str = reader.readLine();
        toCharArray = str.toCharArray();

        System.out.println("Введите искомый символ");
        symbol = reader.readLine().charAt(0);


        System.out.println("Кол-во символов " + symbol + " " + charCount() + " индексы символа "+symbol +" в строке " + charPosition());
    }

    public static int charCount() {
        int counter = 0;
        for (int i = 0; i < toCharArray.length; i++) {
            if (toCharArray[i] == symbol) counter++;
        }
        return counter;
    }

    public static ArrayList charPosition() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < toCharArray.length; i++) {
            if (toCharArray[i] == symbol) list.add(i);
        }
        return list;
    }


}
