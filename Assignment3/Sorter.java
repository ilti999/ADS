package Assignment3;

import java.util.Random;

/**
 * Sorter class handles sorting operations.
 * Implements Insertion Sort (basic) and Merge Sort (advanced).
 */
public class Sorter {

    // Category A: Basic Sort – Insertion Sort  O(n²)

    /**
     * Insertion Sort: builds a sorted sub-array one element at a time.
     * Each iteration takes the next element and inserts it into its
     * correct position among the already-sorted elements on the left.
     *
     * Time Complexity: O(n²) average/worst, O(n) best (already sorted)
     * Space Complexity: O(1) – in-place
     */
    public void basicSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];   // element to be placed in its correct position
            int j = i - 1;

            // Shift elements greater than key one position to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert key into its correct position
            arr[j + 1] = key;
        }
    }

    // Category B: Advanced Sort – Merge Sort  O(n log n)

    /**
     * Merge Sort: divide-and-conquer algorithm.
     * Recursively splits the array in half, sorts each half,
     * then merges the two sorted halves back together.
     *
     * Time Complexity: O(n log n) in all cases
     * Space Complexity: O(n) – requires auxiliary arrays during merge
     */
    public void advancedSort(int[] arr) {
        if (arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }

    /** Recursive helper: splits and sorts the sub-array arr[left..right]. */
    private void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;          // base case: single element

        int mid = left + (right - left) / 2;    // avoids integer overflow

        mergeSort(arr, left, mid);          // sort left half
        mergeSort(arr, mid + 1, right);     // sort right half
        merge(arr, left, mid, right);       // merge the two sorted halves
    }

    /** Merges two sorted sub-arrays: arr[left..mid] and arr[mid+1..right]. */
    private void merge(int[] arr, int left, int mid, int right) {
        int leftSize  = mid - left + 1;
        int rightSize = right - mid;

        // Temporary arrays
        int[] leftArr  = new int[leftSize];
        int[] rightArr = new int[rightSize];

        // Copy data into temporary arrays
        for (int i = 0; i < leftSize;  i++) leftArr[i]  = arr[left + i];
        for (int j = 0; j < rightSize; j++) rightArr[j] = arr[mid + 1 + j];

        // Merge the temporary arrays back into arr[left..right]
        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // Copy remaining elements (only one of these loops will execute)
        while (i < leftSize)  arr[k++] = leftArr[i++];
        while (j < rightSize) arr[k++] = rightArr[j++];
    }

    // Utility Methods

    /** Prints the contents of an array in a readable format. */
    public void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    /** Generates an array of the given size filled with random integers (0–9999). */
    public int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }

    /** Generates an already-sorted array (ascending) of the given size. */
    public int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i * 2;     // 0, 2, 4, 6, …
        }
        return arr;
    }

    /** Returns a copy of an array (so the original is not mutated during tests). */
    public int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        return copy;
    }
}
