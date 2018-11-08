/**.
 * Class for edge weighted graph.
 */
class EdgeWeightedDiGraph {
    /**.
     * { var_description }
     */
    private int ver;
    /**.
     * { var_description }
     */
    private int edg;
    /**.
     * { var_description }
     */
    private double weight;
    /**.
     * { var_description }
     */
    private Bag<DirectedEdge>[] adj;
    /**.
     * { var_description }
     */
    private int[] indegree;
    /**.
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     */
    EdgeWeightedDiGraph(final int v) {
        this.ver = v;
        this.indegree = new int[ver];
        adj = (Bag<DirectedEdge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<DirectedEdge>();
        }
    }
    /**.
     * Adds an edge.
     * TIME complexity in average case is 1.
     * @param      e     { parameter_description }
     */
    public void addEdge(final DirectedEdge e) {
        int v = e.from();
        int w = e.to(v);
        adj[v].add(e);
        indegree[w]++;
        edg++;
    }
    /**.
     * { function_description }
     * TIME complexity in average case is 1.
     * @return     { description_of_the_return_value }
     */
    public int vertices() {
        return ver;
    }
    /**.
     * { function_description }
     * TIME complexity in average case is 1.
     * @return     { description_of_the_return_value }
     */
    public int edg() {
        return edg;
    }
    /**.
     * { function_description }
     * TIME complexity in average case is 1.
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<DirectedEdge> adj(final int v) {
        // validateVertex(v);
        return adj[v];
    }
    /**.
     * { function_description }
     * TIME complexity in average case is 1.
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int degree(final int v) {
        return adj[v].size();
    }
    /**.
     * { function_description }
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int outdegree(final int v) {
        return indegree[v];
    }
    /**.
     * { function_description }
     * TIME complexity is no of edges.
     * @return     { description_of_the_return_value }
     */
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int v = 0; v < ver; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }
}
