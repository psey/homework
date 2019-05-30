package lesson4;



/***
 * 3. Даны 5 чисел (тип int).
 * Вывести вначале наименьшее, а затем наибольшее из данных чисел.
 */

public class Task3 {
    public static void main(String[] args) {
        int num [] = {1, -6, 346, 36, -7 };
        int min = num[0];
        int max = num[0];

        for (int i = 0; i < num.length; i++){
            if (num[i] > max){
                max = num [i];
            }
            if (num[i] < min){
                min = num[i];
            }
        }
        System.out.println(min);
        System.out.println(max);
    }

}
