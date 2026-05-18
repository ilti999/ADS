public class Main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("  SMALL GRAPH — 10 vertices");
        System.out.println("========================================");

        Graph small = buildGraph(10);
        small.printGraph();

        System.out.println();
        System.out.println("--- Traversals (start = 0) ---");
        Experiment exp = new Experiment();
        exp.runTraversals(small);

        System.out.println();
        System.out.println("========================================");
        System.out.println("  MEDIUM GRAPH — 30 vertices");
        System.out.println("========================================");

        Graph medium = buildGraph(30);
        System.out.println("Vertices: " + medium.vertexCount()
                + "  |  Edges: " + medium.edgeCount());

        long bfsStart = System.nanoTime();
        medium.bfs(0);
        long bfsEnd = System.nanoTime();

        long dfsStart = System.nanoTime();
        medium.dfs(0);
        long dfsEnd = System.nanoTime();

        System.out.printf("  BFS time : %,d ns%n", bfsEnd - bfsStart);
        System.out.printf("  DFS time : %,d ns%n", dfsEnd - dfsStart);

        System.out.println();
        System.out.println("========================================");
        System.out.println("  LARGE GRAPH — 100 vertices");
        System.out.println("========================================");

        Graph large = buildGraph(100);
        System.out.println("Vertices: " + large.vertexCount()
                + "  |  Edges: " + large.edgeCount());

        bfsStart = System.nanoTime();
        large.bfs(0);
        bfsEnd = System.nanoTime();

        dfsStart = System.nanoTime();
        large.dfs(0);
        dfsEnd = System.nanoTime();

        System.out.printf("  BFS time : %,d ns%n", bfsEnd - bfsStart);
        System.out.printf("  DFS time : %,d ns%n", dfsEnd - dfsStart);

        System.out.println();
        System.out.println("========================================");
        System.out.println("  PERFORMANCE EXPERIMENT");
        System.out.println("========================================");

        exp.runMultipleTests();
        exp.printResults();
    }

    private static Graph buildGraph(int n) {
        Graph g = new Graph();

        for (int i = 0; i < n; i++) {
            g.addVertex(new Vertex(i));
        }

        for (int i = 0; i < n; i++) {
            g.addEdge(i, (i + 1) % n);
        }

        for (int i = 0; i < n / 2; i++) {
            int to = (i + n / 3 + 1) % n;
            if (i != to) {
                g.addEdge(i, to);
            }
        }

        return g;
    }
}
