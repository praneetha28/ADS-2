/**.
 * Class for page rank.
 */
class PageRank {
	/**.
	 * { var_description }
	 */
	private Digraph dg;
	/**.
	 * { var_description }
	 */
	private HashTable<Integer, Double> ht;
	/**.
	 * { var_description }
	 */
	private static final double PRERV_PR = 0.25;
	/**.
	 * { var_description }
	 */
	private static final long IT_ER = 1000;
	/**.
	 * { var_description }
	 */
	private int size = 0;
	/**.
	 * Constructs the object.
	 *
	 * @param      dig   The dig
	 */
	PageRank(final Digraph dig) {
		dg = dig;
		ht = new HashTable<Integer, Double>();
	}
	/**.
	 * Gets the pr.
	 */
	public void getPR() {
		// double prevpr = 0.25;
		for (int p = 0; p < dg.vert(); p++) {
			ht.put(p, PRERV_PR);
		}
		for (int i = 0; i < IT_ER; i++) {
			for (int j = 0; j < dg.vert(); j++) {
				double temp = 0.0;
				double temp1 = 0.0;
				for (int k : dg.adj(j)) {
                    //int cnt = 0;
                    if (dg.hasParallelEdges(k)) {
                        temp = ht.get(k) / dg.outdegree(k) + 1;
                        temp1 = temp1 + temp;
                    } else {
                        temp = ht.get(j) / dg.outdegree(j);
                        temp1 = temp1 + temp;
                    }
                    //System.out.println(finaltemp);
                }
				ht.put(j, temp1);
			}
		}
	}
	/**.
	 * { function_description }
	 */
	public void String() {
		for (int i : ht.keys()) {
			String s = "";
			s += String.valueOf(i) + " - " + ht.get(i);
			System.out.println(s);
		}
	}
}
