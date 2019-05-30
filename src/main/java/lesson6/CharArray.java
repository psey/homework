package lesson6;

import java.util.Arrays;

public class CharArray {
    public static void main(String[] args) {

        char[] arr = new char[255];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) i;
        }
        System.out.println("char array " + Arrays.toString(arr));
    }
}
