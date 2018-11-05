/**.
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
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
    private Bag<Edge>[] adj;
    /**.
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     */
    EdgeWeightedGraph(final int v) {
        this.ver = v;
        adj = (Bag<Edge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    /**.
     * Adds an edge.
     * TIME complexity in average case is 1.
     * @param      e     { parameter_description }
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
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
    public Iterable<Edge> adj(final int v) {
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
     * TIME complexity is no of edges.
     * @return     { description_of_the_return_value }
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int i = 0; i < ver; i++) {
            int selfLoops = 0;
            for (Edge ed : adj(i)) {
                if (ed.other(i) > i) {
                    list.add(ed);
                } else if (ed.other(i) == i) {
                    if (selfLoops % 2 == 0) {
                        list.add(ed);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }
}
