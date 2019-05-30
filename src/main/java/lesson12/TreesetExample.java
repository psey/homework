package lesson12;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class TreesetExample {
    public static void main(String[] args) {
        Random random = new Random(30);
        Set<Integer> numberSet = new TreeSet<>();
        for (int i = 0; i < 100; i++){
            numberSet.add(random.nextInt(10));

        }
        System.out.println(numberSet);
    }

}
