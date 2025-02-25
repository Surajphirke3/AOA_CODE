import java.util.*;

public class Dijkstra{
    static final int INF = Integer.MAX_VALUE;
    private static int[][] graph;
    private static int V;

    public static void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V]; // Shortest distances
        boolean[] visited = new boolean[V]; // Track visited nodes
        Arrays.fill(dist, INF);
        dist[src] = 0;

        System.out.println("\nProcessing Dijkstra's Algorithm...\n");
        System.out.printf("%-10s %-10s %-10s\n", "Step", "Vertex", "Distance");

        for (int i = 0; i < V - 1; i++) {
            int u = findMinDistance(dist, visited);
            visited[u] = true;

            System.out.printf("%-10d %-10d %-10d\n", i + 1, u, dist[u]);

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist);
    }

    static int findMinDistance(int[] dist, boolean[] visited) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    static void printSolution(int[] dist) {
        System.out.println("\nFinal Shortest Distances:");
        System.out.printf("%-10s %-10s\n", "Vertex", "Distance");
        for (int i = 0; i < dist.length; i++) {
            System.out.printf("%-10d %-10d\n", i, dist[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get the number of vertices
        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();
        graph = new int[V][V];

        // Step 2: Get the number of edges
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        // Step 3: Get the edges with weights
        System.out.println("\nEnter edges in format (source destination weight):");
        for (int i = 0; i < E; i++) {
            System.out.print("Edge " + (i + 1) + ": ");
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            graph[src][dest] = weight;
            graph[dest][src] = weight; // For undirected graph
        }

        // Display adjacency matrix
        System.out.println("\nAdjacency Matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph[i][j] == 0 && i != j)
                    System.out.print("INF\t");
                else
                    System.out.print(graph[i][j] + "\t");
            }
            System.out.println();
        }

        // Step 4: Get source vertex
        System.out.print("\nEnter source vertex: ");
        int source = sc.nextInt();

        // Step 5: Run Dijkstra's Algorithm
        dijkstra(graph, source);

        sc.close();
    }
}
