package lesson6;

public class ArraysExample {
    public static void main(String[] args) {
        int a[] = {1,2,3};
        a[0] = 1;
        a[1] = 2;
        a[2] = 3;
        char b[] = {'a','b','1'};
        boolean bool[] = {true,true,false};
        for (int elem:a){
            System.out.println(elem);
        }

        char c[] = {'a', 'b', 'c'};
        for (int elem : c){
            System.out.println(elem);
        }
    }

}
