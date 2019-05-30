package lesson6;

public class ArrauMinMax {
    public static void main(String[] args) {
        int ar[] = {2,3,-1,6,6,6,7,8,9,0,1111};
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i: ar){
            if (i > max)
                max = i;
            if (i < min)
                min = i;
        }
  /*      for (int i = 0; i < ar.length; i++){
            if (ar[i] <= min){
                min = ar[i];
            }
            if (ar[i] >= max){
                max = ar[i];
            }

        }
  */
        System.out.println(min);
        System.out.println(max);
    }
}
