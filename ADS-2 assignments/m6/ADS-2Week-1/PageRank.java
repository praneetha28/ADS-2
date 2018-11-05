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
            prvalues[i] = 1.0 / (dg.vert());
        }
        for (int i = 0; i < dg.vert(); i++) {
        	if (dg.outdegree(i) == 0) {
        		for (int j = 0; j < dg.vert(); j++) {
        			if (i != j) {
        				dg.addEdge(i, j);
        			}
        		}
        	}
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < dg.vert(); j++) {
                double temp = 0.0;
                for (int k : dg.adj(j)) {
                		// System.out.println(k);
                		// System.out.println(dg.outdegree(k));
		            temp += (double) (prvalues[k] / dg.outdegree(k));
	            }
                prvalues[j] = temp;
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
