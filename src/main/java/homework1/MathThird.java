package homework1;

/***
 * В переменной n хранится вещественное число с ненулевой дробной частью.
 * Создайте программу, округляющую число n до ближайшего целого и выводящую результат на экран.
 */

public class MathThird {
    public static void main(String[] args) {
        double n = 13.5;

        if ((n % 1) < 0.5){
            System.out.println((int)(n / 1));
        } else {
        System.out.println((int) (0.5 + n));

    }
    }
}
