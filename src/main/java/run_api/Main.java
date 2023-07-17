package run_api;


import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        int[] randomIntsArray = IntStream.generate(() -> new Random().nextInt(100)).limit(100_000).toArray();

        sortSelection(randomIntsArray);
        sortBubble(randomIntsArray);
        sortInsertion(randomIntsArray);


    }

    public static void sortSelection(int[] arr) {

        int[] copy = Arrays.copyOf(arr, arr.length);

        long start = System.currentTimeMillis();

        for (int i = 0; i < copy.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < copy.length; j++) {
                if (copy[j] < copy[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(copy, i, minElementIndex);
        }
        System.out.println("Сортировка выбором для " + arr.length + " элементов составила: " + ((System.currentTimeMillis() - start) / 1000d) + " Секунд");

    }

    public static void sortBubble(int[] arr) {

        int[] copy = Arrays.copyOf(arr, arr.length);

        long start = System.currentTimeMillis();

        for (int i = 0; i < copy.length - 1; i++) {
            for (int j = 0; j < copy.length - 1 - i; j++) {
                if (copy[j] > copy[j + 1]) {
                    swapElements(copy, j, j + 1);
                }
            }
        }
        System.out.println("Сортировка пузырьком для " + arr.length + " элементов составила: " + ((System.currentTimeMillis() - start) / 1000d) + " Секунд");
    }

    public static void sortInsertion(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);

        long start = System.currentTimeMillis();

        for (int i = 1; i < copy.length; i++) {
            int temp = copy[i];
            int j = i;
            while (j > 0 && copy[j - 1] >= temp) {
                copy[j] = copy[j - 1];
                j--;
            }
            copy[j] = temp;
        }

        System.out.println("Сортировка вставкой для " + arr.length + " элементов составила: " + ((System.currentTimeMillis() - start) / 1000d) + " Секунд");
    }


    private static void swapElements(int[] copy, int i, int minElementIndex) {
        int temp = copy[i];
        copy[i] = copy[minElementIndex];
        copy[minElementIndex] = temp;
    }


}
