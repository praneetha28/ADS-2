/**.
 * Interface for graph.
 */
interface Graph {
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    int V();
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    int E();
    /**.
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    void addEdge(int v, int w);
    /**.
     * { function_description }
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    Iterable<Integer> adj(int v);
    // public boolean hasEdge(int v, int w);
}
/**.
 * List of graphs.
 */
class GraphList implements Graph {
    /**.
     * { var_description }
     */
    private int V;
    /**.
     * { var_description }
     */
    private int E;
    /**.
     * { var_description }
     */
    private Bag<Integer>[] adj;
    /**.
     * { var_description }
     */
    private String[] vertexes;
    /**.
     * { var_description }
     */
    private int size = 0;
    /**
     * Initializes an empty graph with V vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  vt number of vertices
     */
    GraphList(final int vt) {
        this.V = vt;
        this.E = 0;
        vertexes = new String[V];
        size = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }
    /**.
     * Adds a vertex.
     *
     * @param      v     { parameter_description }
     */
    public void addVertex(final String v) {
        vertexes[size] = v;
        size++;
    }
    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both
     *  {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(final int v, final int w) {
        if (v == w) {
            System.out.println(V + " vertices, " + E + " edges");
            System.out.println("No edges");
            return;
        }
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }
    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(final int v) {
        return adj[v].size();
    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by
     * the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        String s = "";
        s += V + " vertices, " + E + " edges" + '\n';
        for (int v = 0; v < V; v++) {
            s += vertexes[v] + ": ";
            for (int w : adj[v]) {
                s += vertexes[w] + " ";
            }
            s += '\n';
        }
        return s;
    }

}