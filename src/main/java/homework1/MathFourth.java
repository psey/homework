package homework1;

/**
 * 4. В переменной n хранится натуральное трёхзначное число.
 * Создайте программу, вычисляющую и выводящую на экран сумму цифр числа n.
 */

public class MathFourth {
    public static void main(String[] args) {
        int n = 155;
        int sum = 0;
        int temp;

        while (n != 0) {
            sum += n % 10;
            temp = n % 10;
            n = n / 10;
            System.out.println("temp is " + temp);
            System.out.println("n is " + n);
        }
        System.out.println("Count of numbers is " + sum);

    }
}
