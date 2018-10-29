import java.util.Iterator;
/**.
 * Interface for graph.
 */
interface GraphM {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
/**.
 * List of graphs.
 */
public class GraphMatrix {
    private final int V;
    private int E;
    private int[][] adj;
    /**.
     * { var_description }
     */
    private String[] vertexes;
    /**.
     * { var_description }
     */
    private int size = 0;
    public GraphMatrix(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new int[V][V];
        vertexes = new String[V];
        size = 0;
    }
    public int V() {
        return V;
    }
    public int E() {
        return E;
    }
    public void addVertex(String v) {
        vertexes[size] = v;
        size++;
    }
    public void addEdge(int v, int w) {
        if (v == w) {
            System.out.println(V + " vertices, " + E + " edges");
            System.out.println("No edges");
            return;
        }
        if (adj[v][w] == 0) {
            E++;
            adj[v][w] = 1;
            adj[w][v] = 1;
        }
    }

    public int contains(int v, int w) {
        return adj[v][w];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges" + '\n');
        for (int v = 0; v < V; v++) {
            // s.append(v + ": ");
            for (int w : adj[v]) {
                if (w == 1) {
                    s.append(1 + " ");
                } else {
                    s.append(0 + " ");
                }
            }
            s.append('\n');
        }
        return s.toString();
    }
}