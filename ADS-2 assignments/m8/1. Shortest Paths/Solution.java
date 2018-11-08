/**.
 * { item_description }
 */
import java.util.Scanner;
/**.
 * { item_description }
 */
import java.util.ArrayList;
/**.
 * { item_description }
 */
public final class Solution {
    /**.
     * Constructs the object.
     */
    private Solution() {
        /**.
         * { item_description }
         */
    }
    /**.
     * { function_description }
     *
     * @param      args  The arguments
     */
    // time complexity for the main method is E.
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> stations = new ArrayList<>();
        String[] line = sc.nextLine().split(" ");
        String[] stnames = sc.nextLine().split(" ");
        for (int i = 0; i < stnames.length; i++) {
            stations.add(stnames[i]);
        }
        EdgeWeightedDiGraph edwdgh = new EdgeWeightedDiGraph(Integer.parseInt(line[0]));
        for (int i = 0; i < Integer.parseInt(line[1]); i++) {
            String line1 = sc.nextLine();
            String[] tokens1 = line1.split(" ");
            edwdgh.addEdge(new DirectedEdge(stations.indexOf(tokens1[0]),
             stations.indexOf(tokens1[1]), Double.parseDouble(tokens1[2])));
            edwdgh.addEdge(new DirectedEdge(stations.indexOf(tokens1[1]),
             stations.indexOf(tokens1[0]), Double.parseDouble(tokens1[2])));
        }
        int query = sc.nextInt();
        for (int i = 0; i < query; i++) {
        	String[] quer = sc.nextLine().split(" ");
        	// System.out.println(stations.indexOf(quer[0]));
        	Dijkstras dij = new Dijkstras(edwdgh, stations.indexOf(quer[0]));
        	System.out.println((long) dij.distTo(stations.indexOf(quer[1])));

        }
    }
}
