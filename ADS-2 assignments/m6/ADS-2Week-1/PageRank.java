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
    // private double[] pr1;
    private int vertices;
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
        // vertices = dig.vert();
        prvalues = new double[vertices];
        // pr1 = new double[vertices];
        for (int i = 0; i < vertices; i++) {
            prvalues[i] = (1 / ((double) dig.vert()));
        }
        calculatePR();
        // Digraph revdigraph = dig.reverse();
        // for (int i = 0; i < 1000; i++) {
        //     for (int j = 0; j < vertices; j++) {
        //         double result = 0.0;
        //         // System.out.println("helloS");
        //         for (int k : revdigraph.adj(j)) {
        //             result += ((pr[k]) / ((double) (dig.outdegree(k))));
        //             // System.out.println(result);
        //         }

        //         pr1[j] = result;
        //     }
        //     if (Arrays.equals(pr, pr1)) {
        //         break;
        //     } else {
        //         pr = pr1.clone();
        //     }
        // }
        // for (int i = 0; i < vertices; i++) {
        //     System.out.print(i + " - " + pr1[i] + "\n");
        // }
    }
    public void calculatePR() {
    	double[] temppr = new double[dg.vert()];
		Digraph revdg = dg.reverse();
        for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < dg.vert(); j++) {
				Double rank = 0.0;
				for (int k : revdg.adj(j)) {
					rank += prvalues[k] / (double) dg.outdegree(k);
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
