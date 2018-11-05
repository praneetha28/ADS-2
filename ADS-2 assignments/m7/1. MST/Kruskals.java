/**.
 * Class for kruskals.
 */
class Kruskals {
	/**.
	 * { var_description }
	 */
	private Queue<Edge> mst;
	/**.
	 * { var_description }
	 */
	private MinPQ<Edge> pq;
	/**.
	 * { var_description }
	 */
	private double weight;
	/**.
	 * Constructs the object.
	 *
	 * @param      ewg   The ewg
	 */
	Kruskals(final EdgeWeightedGraph ewg) {
		mst = new Queue<Edge>();
		pq = new MinPQ<Edge>();
		for (Edge e : ewg.edges()) {
			pq.insert(e);
		}
		UF uf = new UF(ewg.vertices());
		while (!pq.isEmpty() && mst.size() < ewg.vertices() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
                weight += e.weight();
            }
        }
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Edge> edges() {
        return mst;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public double weight() {
        return weight;
    }
}
