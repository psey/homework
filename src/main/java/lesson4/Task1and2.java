package lesson4;

/***
 * 1. Даны 4 числа типа int. Сравнить их и вывести наименьшее на консоль.
 * 2. Вывести на консоль количество максимальных чисел среди этих четырех
 */
public class Task1and2 {
    public static void main(String[] args) {
        int a = 5;
        int b = 0;
        int c = -1;
        int d = 12;
        System.out.println("Min value is" + min(min(a,b),min(c,d)));
        System.out.println("Max value is" + max(max(a,b),max(c,d)));
    }
    public static int min(int x, int z){
        int min;
        if (x <= z){
            min = x;
        } else {
            min = z;
        }
        return min;
    }
    public static int max(int x, int z){
        int max;
        if (x >= z){
            max = x;
        } else {
            max = z;
        }
        return max;

    }
}
