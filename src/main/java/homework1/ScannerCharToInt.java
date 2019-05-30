package homework1;

import java.util.Scanner;

public class ScannerCharToInt {
    public static void main(String[] args) {
        int xInt;
        Scanner varFromConsole = new Scanner(System.in);
        System.out.println("Enter a character that will be converted to the int value");
        char xChar = varFromConsole.next().charAt(0);
        xInt = xChar;

        System.out.println("Converted int value is: " + xInt);


    }
}
