/**.
 * Class for page rank.
 */
import java.util.Arrays;
class PageRank {
    /**.
     * { var_description }
     */
    private Digraph dg;
    // /**.
    //  * { var_description }
    //  */
    // private HashTable<Integer, Double> ht;
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
        this.dg = dig;
        prvalues = new double[dg.vert()];
        for (int i = 0; i < dg.vert(); i++) {
			prvalues[i] = 1.0 / dg.vert();
		}
    }
    public void calculatePR() {
    	double[] temppr = new double[dg.vert()];
    	for (int i = 0; i < dg.vert(); i++) {
			if (dg.outdegree(i) == 0) {
				for (int j = 0; j < dg.vert(); j++) {
					if(i != j){
						dg.addEdge(i, j);
					}
				}
			}
		}
		Digraph revdg = dg.reverse();
        for (int l = 0; l < 1000; l++) {
			for (int i = 0; i < dg.vert(); i++) {
				Double rank = 0.0;
				for (int k : revdg.adj(i)) {
					rank += prvalues[k] / dg.outdegree(k);
				}
				temppr[i] = rank;
			}
			if (Arrays.equals(prvalues, temppr)) {
				break;
			} else {
				prvalues = temppr;
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
