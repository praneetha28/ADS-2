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
    private double[] prvalues;
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
        prvalues = new double[dg.vert()];
        for (int i = 0; i < dg.vert(); i++) {
			prvalues[i] = 1.0 / dg.vert();
		}
        for (int l = 0; l < 1000; l++) {
			// Double[] tempArray = new Double[digraph.V()];
			//System.out.println("first for loop");

			for (int i = 0; i < dg.vert(); i++) {
				if (dg.outdegree(i) == 0) {
					for (int j = 0; j < dg.vert(); j++) {
						if(i!=j){
						dg.addEdge(i, j);
						}
					}
				}
				Double rank = 0.0;
				//for (int j = 0; j < digraph.V(); j++) {
				//System.out.println("second for loop");

				for (int k : dg.reverse().adj(i)) {
					//if (k == i) {
					//if (digraph.outdegree(j) > 0) {
					rank += prvalues[k] / dg.outdegree(k);
					//	}
					//}
					//}
				}
				prvalues[i] = rank;
			}

		}

    }
    public Double getPR(int v) {
    	return prvalues[v];
    }
    /**.
     * { function_description }
     */
    public void string() {
        for (int i = 0; i < prvalues.length; i++) {
            String s = "";
            s += String.valueOf(i) + " - " + prvalues[i];
            System.out.println(s);
        }
    }
}
