/**
 * Experiment class is responsible for running experiments
 * and measuring algorithm performance using System.nanoTime().
 */
package Assignment3;

public class Experiment {

    private final Sorter   sorter   = new Sorter();
    private final Searcher searcher = new Searcher();

    // Performance Measurement Methods

    /**
     * Measures the execution time of a sorting algorithm on a copy of arr.
     *
     * @param arr  The array to sort (a copy is made so the original is unchanged)
     * @param type "basic" for Insertion Sort, "advanced" for Merge Sort
     * @return     Elapsed time in nanoseconds
     */
    public long measureSortTime(int[] arr, String type) {
        int[] copy = sorter.copyArray(arr);     // sort a copy – don't mutate the original

        long start = System.nanoTime();

        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(copy);
        } else if (type.equalsIgnoreCase("advanced")) {
            sorter.advancedSort(copy);
        } else {
            System.out.println("Unknown sort type: " + type);
        }

        long end = System.nanoTime();
        return end - start;
    }

    /**
     * Measures the execution time of Binary Search on a sorted array.
     * The array is sorted first (using Merge Sort) before searching.
     *
     * @param arr    The array to search (sorted internally before searching)
     * @param target The value to search for
     * @return       Elapsed time in nanoseconds
     */
    public long measureSearchTime(int[] arr, int target) {
        // Binary Search requires a sorted array
        int[] sorted = sorter.copyArray(arr);
        sorter.advancedSort(sorted);

        long start = System.nanoTime();
        searcher.search(sorted, target);
        long end = System.nanoTime();

        return end - start;
    }

    // Main Experiment Runner

    /**
     * Runs all experiments across three dataset sizes and two input types
     * (random and sorted), then prints a clear comparison table.
     */
    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000};
        String[] labels = {"Small (10)", "Medium (100)", "Large (1000)"};

        System.out.println("=".repeat(70));
        System.out.println("         ALGORITHM PERFORMANCE EXPERIMENT RESULTS");
        System.out.println("=".repeat(70));

        for (int s = 0; s < sizes.length; s++) {
            int size = sizes[s];
            System.out.println("\n--- " + labels[s] + " elements ---");

            // Random arrays
            int[] randomArr = sorter.generateRandomArray(size);
            int   target    = randomArr[size / 2];   // pick a value that exists

            long insertionRandom = measureSortTime(randomArr, "basic");
            long mergeRandom     = measureSortTime(randomArr, "advanced");
            long searchRandom    = measureSearchTime(randomArr, target);

            // Sorted arrays
            int[] sortedArr    = sorter.generateSortedArray(size);
            int   sortedTarget = sortedArr[size / 2];

            long insertionSorted = measureSortTime(sortedArr, "basic");
            long mergeSorted     = measureSortTime(sortedArr, "advanced");
            long searchSorted    = measureSearchTime(sortedArr, sortedTarget);

            // Print results
            System.out.printf("%-30s %15s %15s%n", "Algorithm", "Random (ns)", "Sorted (ns)");
            System.out.println("-".repeat(62));
            System.out.printf("%-30s %15d %15d%n", "Insertion Sort (basic)",  insertionRandom, insertionSorted);
            System.out.printf("%-30s %15d %15d%n", "Merge Sort (advanced)",   mergeRandom,     mergeSorted);
            System.out.printf("%-30s %15d %15d%n", "Binary Search",           searchRandom,    searchSorted);
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("ANALYSIS SUMMARY");
        System.out.println("=".repeat(70));
        System.out.println("- Insertion Sort: O(n²) — fast on small/sorted arrays, slow on large random.");
        System.out.println("- Merge Sort:     O(n log n) — consistently fast regardless of input type.");
        System.out.println("- Binary Search:  O(log n)  — extremely fast; requires a sorted array.");
        System.out.println("- As array size grows, Merge Sort's advantage over Insertion Sort increases.");
        System.out.println("- Sorted input greatly benefits Insertion Sort (best case O(n)).");
    }
}
