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
    private DirectedEdge[] edgeTo;
    /**.
     * { var_description }
     */
    private MinPQ<Double> pq;
    /**.
     * Constructs the object.
     *
     * @param      g     { parameter_description }
     * @param      s     { parameter_description }
     */
    Dijkstras(final EdgeWeightedDiGraph g, final int s) {
        for (DirectedEdge e : g.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException
                ("edge " + e + " has negative weight");
            }
        }

        distTo = new double[g.vertices()];
        edgeTo = new DirectedEdge[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq = new MinPQ<Double>(g.vertices());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : g.adj(v)) {
                relax(e);
            }
        }
    }
    /**.
     * { function_description }
     *
     * @param      e     { parameter_description }
     */
    public void relax(final DirectedEdge e) {
        int v = e.from();
        int w = e.to(v);
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
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public double distTo(final int v) {
        return distTo[v];
    }
    /**.
     * Determines if it has path to.
     *
     * @param      v     { parameter_description }
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**.
     * { function_description }
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<DirectedEdge> pathTo(final int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}