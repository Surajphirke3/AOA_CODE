import java.util.*;

public class DijkstraAlgo {
    static final int INF = Integer.MAX_VALUE;
    private static int[][] graph;
    private static int V;
    private static char[] vertices; // Store vertex names

    public static void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V]; // Shortest distances
        boolean[] visited = new boolean[V]; // Track visited nodes
        Arrays.fill(dist, INF);
        dist[src] = 0;

        System.out.println("\nFinding shortest paths from " + vertices[src] + "...\n");

        for (int i = 0; i < V - 1; i++) {
            int u = findMinDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist, src);
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

    static void printSolution(int[] dist, int src) {
        System.out.println("Shortest distances from " + vertices[src] + ":");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("To " + vertices[i] + " → " + (dist[i] == INF ? "No Path" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get number of nodes
        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();
        graph = new int[V][V];
        vertices = new char[V];

        // Get vertex names
        System.out.println("Enter node vertices (e.g., A B C D):");
        for (int i = 0; i < V; i++) {
            vertices[i] = sc.next().charAt(0);
        }

        // Get number of edges
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        // Get edges
        System.out.println("\nEnter connections (From To Cost, e.g., A B 5):");
        for (int i = 0; i < E; i++) {
            char from = sc.next().charAt(0);
            char to = sc.next().charAt(0);
            int cost = sc.nextInt();
            int fromIndex = getIndex(from);
            int toIndex = getIndex(to);
            graph[fromIndex][toIndex] = cost;
            graph[toIndex][fromIndex] = cost; // Undirected graph
        }

        // Get source node
        System.out.print("\nEnter start node: ");
        char source = sc.next().charAt(0);
        int sourceIndex = getIndex(source);

        // Run Dijkstra
        dijkstra(graph, sourceIndex);

        sc.close();
    }

    static int getIndex(char c) {
        for (int i = 0; i < V; i++) {
            if (vertices[i] == c) return i;
        }
        return -1; // Should never happen
    }
}
