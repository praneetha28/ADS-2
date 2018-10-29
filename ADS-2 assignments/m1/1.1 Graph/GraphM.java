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
            System.out.println(v + " vertices, " + w + " edges");
            System.out.println("No edges");
            return;
        }
        if (adj[v][w] == 1) E++;
        adj[v][w] = 1;
        adj[w][v] = 1;
    }

    public int contains(int v, int w) {
        return adj[v][w];
    }

    public Iterable<Integer> adj(int v) {
        return new AdjIterator(v);
    }
    private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
        private int v;
        private int w = 0;

        AdjIterator(int v) {
            this.v = v;
        }

        public Iterator<Integer> iterator() {
            return this;
        }

        public boolean hasNext() {
            while (w < V) {
                if (adj[v][w] == 1) return true;
                w++;
            }
            return false;
        }

        public Integer next() {
            return w++;
        }
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + '\n');
        for (int v = 0; v < V; v++) {
            s.append(vertexes[v] + ": ");
            for (int w : adj(v)) {
                s.append(vertexes[w] + " ");
            }
            s.append('\n');
        }
        return s.toString();
    }
}