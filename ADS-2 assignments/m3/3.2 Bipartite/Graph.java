/**.
 * Class for graph.
 */
public class Graph {
    /**.
     * { var_description }
     */
    private final int ver;
    /**.
     * { var_description }
     */
    private int edg;
    /**.
     * { var_description }
     */
    private Bag<Integer>[] adj;
    /**.
     * Constructs the object.
     *
     * @param      vt     { parameter_description }
     */
    public Graph(final int vt) {
        this.ver = vt;
        this.edg = 0;
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
   /**
     * Create a digraph from input stream.
     */
/*    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }*/

   /**
     * Copy constructor.
     */
/*    public Graph(Graph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }*/
    /**.
     * { function_description }
     * time complexity in average case is 1.
     * @return     { description_of_the_return_value }
     */
    public int vert() {
        return ver;
    }
    /**.
     * { function_description }
     * time complexity in average case is 1.
     * @return     { description_of_the_return_value }
     */
    public int edge() {
        return edg;
    }
    /**.
     * Adds an edge.
     * time complexity in average case is 1.
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        edg++;
        adj[v].add(w);
        adj[w].add(v);
    }
    /**.
     * { function_description }
     * time complexity in average case is 1.
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    // /**.
    //  * Returns a string representation of the object.
    //  *
    //  * @return     String representation of the object.
    //  */
    // public String toString() {
    //     StringBuilder s = new StringBuilder();
    //     String NEWLINE = System.getProperty("line.separator");
    //     s.append(V + " vertices, " + E + " edges " + NEWLINE);
    //     for (int v = 0; v < V; v++) {
    //         s.append(v + ": ");
    //         for (int w : adj[v]) {
    //             s.append(w + " ");
    //         }
    //         s.append(NEWLINE);
    //     }
    //     return s.toString();
    // }

}


