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
    // time complexity for the main method is O(N).
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        Graph g = new Graph(n);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] tokens = line.split(" ");
            g.addEdge(Integer.parseInt(tokens[0]),
                         Integer.parseInt(tokens[1]));
        }
        DirectedCycle dc = new DirectedCycle(g);
        if (dc.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}
