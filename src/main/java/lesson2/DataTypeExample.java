package lesson2;

public class DataTypeExample {
    public static void main(String[] args) {
        int xInt = 400;
        char xChar;
        xChar = (char) xInt;

        float xFloat = 400;
        char secondChar;
        secondChar = (char) xFloat;

        int thirdInt;
        thirdInt = (int) secondChar;


        System.out.println(xChar);
        System.out.println(secondChar);
        System.out.println(thirdInt);


    }
}
