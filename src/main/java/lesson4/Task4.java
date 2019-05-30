package lesson4;

/***
 * 4. Даны имена 2х человек (тип String).
 * Если имена равны, то вывести сообщение о том, что люди являются тезками.
 */

public class Task4 {
    public static void main(String[] args) {
        String name1 = "Serg";
        String name2 = "Serg";

        if (name1.equals(name2)){
            System.out.println("Это тезки");
        } else {
            System.out.println("Не тезки");
        }
    }
}
