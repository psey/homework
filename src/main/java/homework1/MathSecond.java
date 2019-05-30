package homework1;

/****
 * В переменной n хранится натуральное двузначное число.
 * Создайте программу, вычисляющую и выводящую на экран сумму цифр числа n.
 */

public class MathSecond {
    public static void main(String[] args) {
        int n = 21;
        int firstNumber = n / 10;
        int secondNumber = n % 10;

        int sumN = firstNumber + secondNumber;

        System.out.println("Cуммa цифр числа n = " + sumN);

    }
}
