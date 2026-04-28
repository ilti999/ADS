/**
 * Searcher class handles searching operations.
 * Implements Binary Search (Category C).
 */
package Assignment3;

public class Searcher {

    // Category C: Searching – Binary Search  O(log n)

    /**
     * Binary Search: efficient search on a SORTED array.
     * Repeatedly halves the search range by comparing the target to the
     * middle element — if the target is smaller, search the left half;
     * if larger, search the right half; if equal, the target is found.
     *
     * IMPORTANT: The input array must be sorted in ascending order.
     *
     * Time Complexity: O(log n) – halves the search space each iteration
     * Space Complexity: O(1) – iterative implementation, no extra memory
     *
     * @param arr    A sorted integer array to search within
     * @param target The value to search for
     * @return       The index of the target, or -1 if not found
     */
    public int search(int[] arr, int target) {
        int left  = 0;
        int right = arr.length - 1;

        while (left <= right) {
            // Use (left + (right - left) / 2) to avoid integer overflow
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;             // target found at index mid
            } else if (arr[mid] < target) {
                left = mid + 1;         // target is in the right half
            } else {
                right = mid - 1;        // target is in the left half
            }
        }

        return -1;  // target not found in the array
    }
}
