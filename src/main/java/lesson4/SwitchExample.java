package lesson4;
// File > Product Structure > Modules > lang up to 8
// IDEA > preferences > Java compiler > Target by code version 8
public class SwitchExample {
    public static void main(String[] args) {
        String name = "Jack";
        switch (name){
            case "Jack":
                System.out.println("Daniels"); //break;

            case "NotJack":
                System.out.println("not Daniels"); break;

            case "Lox":
                System.out.println("Pidor!"); break;
            default:
                System.out.println("YXODI!");

        }
    }
}
