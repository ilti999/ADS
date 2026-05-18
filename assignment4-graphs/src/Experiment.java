import java.util.List;

public class Experiment {

    private static final int WARMUP_RUNS = 3;
    private static final int TIMED_RUNS = 5;

    private long[] bfsTimes;
    private long[] dfsTimes;
    private int[]  sizes;

    public void runTraversals(Graph g) {
        final int start = 0;

        long bfsStart = System.nanoTime();
        List<Integer> bfsOrder = g.bfs(start);
        long bfsEnd = System.nanoTime();

        long dfsStart = System.nanoTime();
        List<Integer> dfsOrder = g.dfs(start);
        long dfsEnd = System.nanoTime();

        System.out.println("  BFS order : " + bfsOrder);
        System.out.println("  DFS order : " + dfsOrder);
        System.out.printf("  BFS time  : %,d ns%n", bfsEnd - bfsStart);
        System.out.printf("  DFS time  : %,d ns%n", dfsEnd - dfsStart);
    }

    public void runMultipleTests() {
        int[] graphSizes = {10, 30, 100};
        bfsTimes = new long[graphSizes.length];
        dfsTimes = new long[graphSizes.length];
        sizes    = graphSizes;

        for (int i = 0; i < graphSizes.length; i++) {
            int n = graphSizes[i];
            Graph g = GraphFactory.createDenseGraph(n);

            for (int w = 0; w < WARMUP_RUNS; w++) {
                g.bfs(0);
                g.dfs(0);
            }

            long totalBfs = 0, totalDfs = 0;
            for (int r = 0; r < TIMED_RUNS; r++) {
                long t0 = System.nanoTime();
                g.bfs(0);
                long t1 = System.nanoTime();
                g.dfs(0);
                long t2 = System.nanoTime();

                totalBfs += t1 - t0;
                totalDfs += t2 - t1;
            }

            bfsTimes[i] = totalBfs / TIMED_RUNS;
            dfsTimes[i] = totalDfs / TIMED_RUNS;
        }
    }

    public void printResults() {
        if (bfsTimes == null) {
            System.out.println("No results available. Call runMultipleTests() first.");
            return;
        }

        System.out.println();
        System.out.println("=== Performance Results (averaged over " + TIMED_RUNS + " runs) ===");
        System.out.printf("%-15s %-20s %-20s%n", "Graph Size", "BFS Time (ns)", "DFS Time (ns)");
        System.out.println("-".repeat(55));

        for (int i = 0; i < sizes.length; i++) {
            String bfsFormatted = String.format("%,d", bfsTimes[i]);
            String dfsFormatted = String.format("%,d", dfsTimes[i]);
            System.out.printf("%-15d %-20s %-20s%n",
                    sizes[i], bfsFormatted, dfsFormatted);
        }

        System.out.println();
        System.out.println("Observations:");
        for (int i = 0; i < sizes.length; i++) {
            String faster = bfsTimes[i] <= dfsTimes[i] ? "BFS" : "DFS";
            System.out.printf("  n=%-4d  faster: %s%n", sizes[i], faster);
        }
    }

    static class GraphFactory {

        static Graph createDenseGraph(int n) {
            Graph g = new Graph();
            for (int i = 0; i < n; i++) {
                g.addVertex(new Vertex(i));
            }

            for (int i = 0; i < n; i++) {
                g.addEdge(i, (i + 1) % n);
            }

            for (int i = 0; i < n / 2; i++) {
                int from = i;
                int to   = (i + n / 3 + 1) % n;
                if (from != to) {
                    g.addEdge(from, to);
                }
            }

            return g;
        }
    }
}
