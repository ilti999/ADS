/**
 * Main.java – Entry point for the Sorting and Searching Algorithm Analysis System.
 *
 * Algorithms chosen:
 *   Category A (Basic Sort):    Insertion Sort  – O(n²)
 *   Category B (Advanced Sort): Merge Sort      – O(n log n)
 *   Category C (Search):        Binary Search   – O(log n)
 */
package Assignment3;

public class Main {

    public static void main(String[] args) {

        Sorter     sorter     = new Sorter();
        Searcher   searcher   = new Searcher();
        Experiment experiment = new Experiment();

        // PART 1 – Demonstrate algorithms on a small array
        System.out.println("=".repeat(60));
        System.out.println("  PART 1: Algorithm Demonstrations");
        System.out.println("=".repeat(60));

        // Insertion Sort demo
        int[] smallRandom = sorter.generateRandomArray(10);
        System.out.println("\n[Insertion Sort] Original array:");
        sorter.printArray(smallRandom);

        int[] copyForBasic = sorter.copyArray(smallRandom);
        sorter.basicSort(copyForBasic);
        System.out.println("[Insertion Sort] Sorted array:");
        sorter.printArray(copyForBasic);

        // Merge Sort demo
        int[] mediumRandom = sorter.generateRandomArray(10);
        System.out.println("\n[Merge Sort] Original array:");
        sorter.printArray(mediumRandom);

        int[] copyForAdvanced = sorter.copyArray(mediumRandom);
        sorter.advancedSort(copyForAdvanced);
        System.out.println("[Merge Sort] Sorted array:");
        sorter.printArray(copyForAdvanced);

        // Binary Search demo
        // Binary Search needs a sorted array
        int[] sortedForSearch = sorter.generateSortedArray(10);
        int   target          = sortedForSearch[6];   // pick element at index 6

        System.out.println("\n[Binary Search] Searching in sorted array:");
        sorter.printArray(sortedForSearch);
        int index = searcher.search(sortedForSearch, target);
        System.out.println("[Binary Search] Target = " + target
                + " → Found at index: " + index);

        // Search for a value that does not exist
        int missing = 9999;
        int notFound = searcher.search(sortedForSearch, missing);
        System.out.println("[Binary Search] Target = " + missing
                + " → Result: " + (notFound == -1 ? "Not found (-1)" : notFound));

        // PART 2 – Full performance experiments
        System.out.println("\n");
        experiment.runAllExperiments();

        // PART 3 – Edge cases
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  PART 3: Edge Case Handling");
        System.out.println("=".repeat(60));

        // Empty array
        int[] empty = {};
        sorter.basicSort(empty);
        sorter.advancedSort(empty);
        int emptySearch = searcher.search(empty, 5);
        System.out.println("\nEmpty array sort: OK");
        System.out.println("Empty array search result: " + emptySearch + " (expected -1)");

        // Single element
        int[] single = {42};
        sorter.basicSort(single);
        sorter.advancedSort(single);
        int singleSearch = searcher.search(single, 42);
        System.out.println("Single-element array sort: OK");
        System.out.println("Single-element search for 42 at index: " + singleSearch + " (expected 0)");

        // Already sorted — best case for Insertion Sort
        int[] alreadySorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("\nAlready-sorted array (best case for Insertion Sort):");
        sorter.printArray(alreadySorted);
        int[] copyAlready = sorter.copyArray(alreadySorted);
        sorter.basicSort(copyAlready);
        System.out.print("After basicSort: ");
        sorter.printArray(copyAlready);

        System.out.println("\nAll tests completed successfully.");
    }
}
