/**.
 * { item_description }
 */
import java.util.Scanner;
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
    // time complexity for the main method is N.
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        sc.nextLine();
        EdgeWeightedGraph edwgh = new EdgeWeightedGraph();
        for (int i = 0; i < edges; i++) {
            String line = sc.nextLine();
            String[] tokens = line.split(" ");
            edwgh.addEdge(new Edge(Integer.parseInt(tokens[0]),
             Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
        }
        Kruskals krs = new Kruskals(edwgh);
        System.out.println(krs.weight());
    }
}