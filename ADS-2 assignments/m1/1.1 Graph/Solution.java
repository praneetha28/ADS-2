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
    // time complexity for the main method is N
    // Because there is one while loop.
    // while loop iterates until it has next line i.e N times.
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        switch (word) {
	        case"List":
	        int n =  sc.nextInt();
	        int m = sc.nextInt();
	        sc.nextLine();
	        String vtexes = sc.nextLine();
	        String[] tokens1 = vtexes.split(",");
	        GraphList gl = new GraphList(n);
	        for (int i = 0; i < n; i++) {
	        	gl.addVertex(tokens1[i]);
	        }
	        for (int i = 0; i < m; i++) {
	        	String line = sc.nextLine();
	        	String[] tokens = line.split(" ");
	        	gl.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
	        }
	       	System.out.println(gl.toString());
	        break;
	        default:
	        break;
	    }
    }
}
