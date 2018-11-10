/**.
 * Class for dijkstras.
 */
class Dijkstras {
    /**.
     * { var_description }
     */
    private double[] distTo;
    /**.
     * { var_description }
     */
    private Edge[] edgeTo;
    /**.
     * { var_description }
     */
    private MinPQ<Double> pq;
    /**.
     * Constructs the object.
     * TIME complexity in average case is E log V.
     * @param      g     { parameter_description }
     * @param      s     { parameter_description }
     */
    Dijkstras(final EdgeWeightedGraph g, final int s) {
        for (Edge e : g.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException(
                "edge " + e + " has negative weight");
            }
        }

        distTo = new double[g.vertices()];
        edgeTo = new Edge[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq = new MinPQ<Double>(g.vertices());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : g.adj(v)) {
                relax(e, v);
            }
        }
    }
    /**.
     * { function_description }
     * TIME complexity in average case is 1.
     * @param      e     { parameter_description }
     * @param      v     { parameter_description }
     */
    public void relax(final Edge e, final int v) {
        // int v = e.either();
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }
    /**.
     * { function_description }
     * TIME complexity in average case is 1.
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public double distTo(final int v) {
        if (distTo[v] == Double.POSITIVE_INFINITY) {
            return -1.0;
        } else {
            return distTo[v];
        }
    }
    /**.
     * Determines if it has path to.
     * TIME complexity in average case is 1.
     * @param      v     { parameter_description }
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**.
     * { function_description }
     * TIME complexity in O(E) .
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Edge> pathTo(final int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Edge> path = new Stack<Edge>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.either()]) {
            path.push(e);
        }
        return path;
    }
}
