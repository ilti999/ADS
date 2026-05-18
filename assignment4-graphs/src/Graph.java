import java.util.*;

public class Graph {

    private final Map<Integer, Vertex> vertices;

    private final Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        vertices = new LinkedHashMap<>();
        adjacencyList = new LinkedHashMap<>();
    }

    public void addVertex(Vertex v) {
        if (!vertices.containsKey(v.getId())) {
            vertices.put(v.getId(), v);
            adjacencyList.put(v.getId(), new ArrayList<>());
        }
    }

    public void addEdge(int from, int to) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            throw new IllegalArgumentException(
                    "Both vertices must exist before adding an edge: " + from + ", " + to);
        }
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
    }

    public void printGraph() {
        System.out.println("Graph adjacency list (" + vertices.size() + " vertices):");
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    public List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        return order;
    }

    public List<Integer> dfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);
            order.add(current);

            List<Integer> neighbours = adjacencyList.get(current);
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                if (!visited.contains(neighbours.get(i))) {
                    stack.push(neighbours.get(i));
                }
            }
        }

        return order;
    }

    public int vertexCount() {
        return vertices.size();
    }

    public int edgeCount() {
        int total = 0;
        for (List<Integer> neighbours : adjacencyList.values()) {
            total += neighbours.size();
        }
        return total / 2;
    }

    public Set<Integer> getVertexIds() {
        return vertices.keySet();
    }
}
