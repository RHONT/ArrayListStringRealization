package run_api;


import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        int[] randomIntsArray = IntStream.generate(() -> new Random().nextInt(1000)).limit(300_000).toArray();

        sortSelection(randomIntsArray);
        sortBubble(randomIntsArray);
        sortInsertion(randomIntsArray);

        quickSortModule(randomIntsArray);
        mergeSortModule(randomIntsArray);
        quickSortOptimize(randomIntsArray);
        System.out.println();


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

    public static void quickSortModule(int[] arr){
        int[] copy = Arrays.copyOf(arr, arr.length);
        long start = System.currentTimeMillis();
        quickSort(copy,0,copy.length-1);
        System.out.println("Сортировка быстрая для " + arr.length + " элементов составила: " + ((System.currentTimeMillis() - start) / 1000d) + " Секунд");
    }

    public static void mergeSortModule(int[] arr){
        int[] copy = Arrays.copyOf(arr, arr.length);
        long start = System.currentTimeMillis();
        mergeSort(copy);
        System.out.println("Сортировка слиянием для " + arr.length + " элементов составила: " + ((System.currentTimeMillis() - start) / 1000d) + " Секунд");
    }

    public static void quickSort(int[] arr, int begin, int end) {

        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }


    private static void swapElements(int[] copy, int i, int minElementIndex) {
        int temp = copy[i];
        copy[i] = copy[minElementIndex];
        copy[minElementIndex] = temp;
    }

    public static void quickSortOptimize(int[] array) {

        int[] copy = Arrays.copyOf(array, array.length);
        long start = System.currentTimeMillis();
        quickSortOptimize(copy, 0, copy.length - 1);
        System.out.println("Сортировка быстрая с оптимизацией для " + copy.length + " элементов составила: " + ((System.currentTimeMillis() - start) / 1000d) + " Секунд");
    }

    public static void quickSortOptimize(int[] array, int lo, int hi) {
        if (hi - lo <= 32) {
            insertionSort(array, lo, hi);
            return;
        }
        int h = breakPartition(array, lo, hi);
        quickSortOptimize(array, lo, h - 1);
        quickSortOptimize(array, h + 1, hi);
    }


    public static int breakPartition(int[] array, int lo, int hi) {
        int i = lo + 1;
        int supportElement = array[lo];
        int j = hi;
        for (;;) {
            for (; i < hi && array[i] < supportElement;) {
                i += 1;
            }
            for (; array[j] > supportElement;) {
                j -= 1;
            }
            if (i >= j) {
                break;
            }
            swapElements(array, i++, j--);
        }
        swapElements(array, lo, j);
        return j;
    }



    public static void insertionSort(int[] array, int begin, int end) {
        for (int i = begin; i <= end; i++) {
            int pasteElement = array[i];
            int j;
            for (j = i; j > begin; j--) {
                if (array[j - 1] <= pasteElement) {
                    break;
                }
                array[j] = array[j - 1];
            }
            array[j] = pasteElement;
        }
    }


}
