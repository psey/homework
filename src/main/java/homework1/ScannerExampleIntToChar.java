package homework1;

import java.util.Scanner;

public class ScannerExampleIntToChar {
    public static void main(String[] args) {
        Scanner varFromConsole = new Scanner(System.in);
        System.out.println("Enter int value that will be converted to the character");

        int xInt = varFromConsole.nextInt();
        System.out.println("Your character is: " + (char) xInt);

    }
}
