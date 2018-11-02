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
        // Scanner sc = new Scanner(System.in);
        String n = StdIn.readString();
        String m = StdIn.readString();
        WordNet wn = new WordNet(n , m);
        // String word = sc.nextLine()
        // DirectedCycle dc = new DirectedCycle(g);
        // if (dc.isBipartite()) {
        //     System.out.println("Graph is bipartite");
        // } else {
        //     System.out.println("Graph is not a bipartite");
        // }
    }
}
