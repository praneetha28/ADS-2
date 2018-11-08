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
        EdgeWeightedDiGraph ewdgh = new EdgeWeightedDiGraph(
            Integer.parseInt(line[0]));
        for (int i = 0; i < Integer.parseInt(line[1]); i++) {
            String[] line1 = sc.nextLine().split(" ");
            ewdgh.addEdge(new DirectedEdge(
                stations.indexOf(line1[0]), stations.indexOf(
                    line1[1]), Double.parseDouble(line1[2])));
            ewdgh.addEdge(new DirectedEdge(
                stations.indexOf(line1[1]), stations.indexOf(
                    line1[0]), Double.parseDouble(line1[2])));
        }
        int query = Integer.parseInt(sc.nextLine());
        for (int k = 0; k < query; k++) {
            String[] quer = sc.nextLine().split(" ");
            Dijkstras dij = new Dijkstras(
                ewdgh, stations.indexOf(quer[0]));
            System.out.println((long) dij.distTo(
                                   stations.indexOf(quer[1])));
        }
    }
}
