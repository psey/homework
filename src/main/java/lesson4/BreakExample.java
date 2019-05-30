package lesson4;

public class BreakExample {
    public static void main(String[] args) {
        for (int a=1; a<=10; a++) {
            if(a == 5) {
                break;
            }
            System.out.print(a + " ");
        }
        System.out.print("Конец");

    }
}
