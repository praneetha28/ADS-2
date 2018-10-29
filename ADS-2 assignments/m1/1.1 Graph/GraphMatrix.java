import java.util.Iterator;
/**.
 * Interface for graph.
 */
interface GraphM {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    // public boolean hasEdge(int v, int w);
}
/**.
 * List of graphs.
 */
public class GraphMatrix {
    /**.
     * { var_description }
     */
    private final int V;
    /**.
     * { var_description }
     */
    private int E;
    /**.
     * { var_description }
     */
    private int[][] adj;
    /**.
     * { var_description }
     */
    private String[] vertexes;
    /**.
     * { var_description }
     */
    private int size = 0;
    /**.
     * Constructs the object.
     *
     * @param      V     { parameter_description }
     */
    public GraphMatrix(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new int[V][V];
        vertexes = new String[V];
        size = 0;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int V() {
        return V;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int E() {
        return E;
    }
    /**.
     * Adds a vertex.
     *
     * @param      v     { parameter_description }
     */
    public void addVertex(String v) {
        vertexes[size] = v;
        size++;
    }
    /**
     * .
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
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
    /**.
     * { function_description }
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int contains(int v, int w) {
        return adj[v][w];
    }
    /**.
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
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