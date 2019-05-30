package lesson4;

import java.util.Scanner;

public class WeekDay {
    public static void main(String[] args) {
        Scanner day = new Scanner(System.in);
        int d = day.nextInt();
        switch (d){
            case 1:
                System.out.println("Monday"); break;

            case 2:
                System.out.println("Tuesday"); break;

            case 3:
                System.out.println("Wendsday"); break;
            default:
                System.out.println("мне лень!");

        }
    }
}
