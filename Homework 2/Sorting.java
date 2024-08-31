import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {
        int[] array = {4, 77, 98, 30, 20, 50, 77, 22, 49, 2};
        System.out.println("Original array: " + Arrays.toString(array));

        int[] bubbleSortedArray = bubbleSort(Arrays.copyOf(array, array.length));
        System.out.println("Bubble Sorted: " + Arrays.toString(bubbleSortedArray));

        int[] recursiveBubbleSortedArray = Arrays.copyOf(array, array.length);
        recursiveBubble(recursiveBubbleSortedArray, recursiveBubbleSortedArray.length);
        System.out.println("Recursive Bubble Sorted: " + Arrays.toString(recursiveBubbleSortedArray));

        int[] selectionSortedArray = selectionSort(Arrays.copyOf(array, array.length));
        System.out.println("Selection Sorted: " + Arrays.toString(selectionSortedArray));

        int[] insertionSortedArray = insertionSort(Arrays.copyOf(array, array.length));
        System.out.println("Insertion Sorted: " + Arrays.toString(insertionSortedArray));

        int[] mergeSortedArray = mergeSort(Arrays.copyOf(array, array.length), 0, array.length - 1);
        System.out.println("Merge Sorted: " + Arrays.toString(mergeSortedArray));

        quickSort(array, 0, array.length - 1); // Quick sort changes the original array
        System.out.println("Quick Sorted: " + Arrays.toString(array));
    }

    public static int[] bubbleSort(int[] a) {
        int n = a.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (a[i - 1] > a[i]) {
                    swap(a, i - 1, i);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
        return a;
    }

    public static void recursiveBubble(int[] a, int n) {
        if (n == 1) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            if (a[i] > a[i + 1]) {
                swap(a, i, i + 1);
            }
        }
        recursiveBubble(a, n - 1);
    }

    public static int[] selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            swap(a, i, minIndex);
        }
        return a;
    }

    public static int[] insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
        return a;
    }

    public static int[] mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(a, left, middle);
            mergeSort(a, middle + 1, right);
            merge(a, left, middle, right);
        }
        return a;
    }

    private static void merge(int[] a, int left, int middle, int right) {
        int[] leftTemp = Arrays.copyOfRange(a, left, middle + 1);
        int[] rightTemp = Arrays.copyOfRange(a, middle + 1, right + 1);

        int i = 0, j = 0;
        int k = left;
        while (i < leftTemp.length && j < rightTemp.length) {
            if (leftTemp[i] <= rightTemp[j]) {
                a[k] = leftTemp[i];
                i++;
            } else {
                a[k] = rightTemp[j];
                j++;
            }
            k++;
        }

        while (i < leftTemp.length) {
            a[k] = leftTemp[i];
            i++;
            k++;
        }

        while (j < rightTemp.length) {
            a[k] = rightTemp[j];
            j++;
            k++;
        }
    }

    public static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(a, low, high);
            quickSort(a, low, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (a[j] < pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}