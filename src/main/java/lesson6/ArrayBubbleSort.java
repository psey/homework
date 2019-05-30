package lesson6;

// Если отсортировано, то чтобы программа не выполняла циклы
public class ArrayBubbleSort {
    public static void main(String[] args) {
        int ar[] = {2, 3, -1, 6, 16, 6, 7, 8, 9, 0, 1111};
       int z;

        for(int i = 0; i < ar.length ; i++){
            for(int j = 0; j < ar.length - i - 1; j++){
                if(ar[j] > ar[j + 1]){
                    z = ar[j];
                    ar[j] = ar[j + 1];
                    ar[j + 1] = z;
                }
            }
        }
 /*

        int temp;
        boolean swapFlag = true;

        while(swapFlag) {
            swapFlag = false;
            for (int i = 0; i < ar.length - 1; i++) {
                if (ar[i] > ar[i + 1]) {
                    temp = ar[i];
                    ar[i] = ar[i + 1];
                    ar[i + 1] = temp;
                    swapFlag = true;
                }
            }
        }
        */
        for(int i: ar){
            System.out.println(i);
        }
    }
}
