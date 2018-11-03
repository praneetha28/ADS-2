/**.
 * Class for digraph.
 */
public class Digraph {
    /**.
     * { var_description }
     */
    private static final String NEWLINE = System.getProperty("line.separator");
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
     * { var_description }
     */
    private boolean[] marked;
    /**.
     * Constructs the object.
     *
     * @param      vt    { parameter_description }
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
    /**.
     * { function_description }
     * time complexity in average case is 1.
     * @return     { description_of_the_return_value }
     */
    public int vert() {
        return ver;
    }

    /**.
     * Returns the number of edges in this digraph.
     * time complexity in average case is 1.
     * @return the number of edges in this digraph
     */
    public int edge() {
        return edg;
    }
    /**.
     * { function_description }
     * time complexity in average case is 1.
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= ver) {
            throw new IllegalArgumentException("vertex " + v
             + " is not between 0 and " + (ver - 1));
        }
    }

    /**.
     * Adds the directed edge v→w to this digraph.
     * time complexity in average case is 1.
     * @param  v the tail vertex
     * @param  w the head vertex
     * @throws IllegalArgumentException unless both
     * {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        edg++;
    }

    /**.
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     * time complexity in average case is 1.
     * @param  v the vertex
     * @return the vertices adjacent from vertex {@code v} in this
     *  digraph, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**.
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     * time complexity in average case is 1.
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**.
     * Returns the number of directed edges incident to vertex {@code v}.
     *
     * time complexity in average case is 1.
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**.
     * Returns the reverse of the digraph.
     * time complexity is O(N^2).
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

    /**.
     * Returns a string representation of the graph.
     * time complexity is O(N^2).
     * @return the number of vertices <em>V</em>
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
    /**.
     * Determines if it has parallel edges.
     * time complexity is O(N).
     * @param      v     { parameter_description }
     *
     * @return     True if has parallel edges, False otherwise.
     */
    public boolean hasParallelEdges(final int v) {
        marked = new boolean[vert()];
        for (int w : adj(v)) {
            if (marked[w]) {
                return true;
            }
            marked[w] = true;
        }
        return false;
    }
}
