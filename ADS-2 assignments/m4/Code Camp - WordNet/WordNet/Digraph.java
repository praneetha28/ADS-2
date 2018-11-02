/**.
 * Class for digraph.
 */
public class Digraph {
    /**.
     * { var_description }
     */
    private static final String NEWLINE = System.
    getProperty("line.separator");
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
     * { var_description }
     */
    private int[] indegree;
    /**.
     * Constructs the object.
     *
     * @param      vt     { parameter_description }
     */
    public Digraph(final int vt) {
        this.ver = vt;
        this.edg = 0;
        indegree = new int[ver];
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // /**
    //  * Initializes a digraph from the specified input stream.
    //  * The format is the number of vertices <em>V</em>,
    //  * followed by the number of edges <em>E</em>,
    //  * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
    //  *
    //  * @param  in the input stream
    //  * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
    //  * @throws IllegalArgumentException if the number of vertices or edges is negative
    //  * @throws IllegalArgumentException if the input stream is in the wrong format
    //  */
    // public Digraph(In in) {
    //     try {
    //         this.V = in.readInt();
    //         if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
    //         indegree = new int[V];
    //         adj = (Bag<Integer>[]) new Bag[V];
    //         for (int v = 0; v < V; v++) {
    //             adj[v] = new Bag<Integer>();
    //         }
    //         int E = in.readInt();
    //         if (E < 0) throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
    //         for (int i = 0; i < E; i++) {
    //             int v = in.readInt();
    //             int w = in.readInt();
    //             addEdge(v, w);
    //         }
    //     }
    //     catch (NoSuchElementException e) {
    //         throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
    //     }
    // }

    /**
     * Initializes a new digraph that is a deep copy of the specified digraph.
     *
     * @param  g the digraph to copy
     */
    public Digraph(final Digraph g) {
        this(g.vert());
        this.edg = g.edge();
        for (int v = 0; v < ver; v++)
            this.indegree[v] = g.indegree(v);
        for (int v = 0; v < g.vert(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : g.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int vert() {
        return ver;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int edge() {
        return edg;
    }
    /**.
     * { function_description }
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= ver)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (v - 1));
    }

    /**.
     * Adds the directed edge v→w to this digraph.
     *
     * @param  v the tail vertex
     * @param  w the head vertex
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        edg++;
    }

    /**
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     *
     * @param  v the vertex
     * @return the vertices adjacent from vertex {@code v} in this digraph, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(ver);
        for (int v = 0; v < ver; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(ver + " vertices, " + edg + " edges " + NEWLINE);
        for (int v = 0; v < ver; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
