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
        int n =  sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        switch (word) {
            case"List":
                if (n == 0 && m == 0) {
                    System.out.println(n + " vertices, " + m + " edges");
                    System.out.println("No edges");
                    break;
                }
                String vtexes = sc.nextLine();
                String[] tokens1 = vtexes.split(",");
                GraphList gl = new GraphList(n);
                for (int i = 0; i < n; i++) {
                    gl.addVertex(tokens1[i]);
                }
                String line1;
                String[] tokens2 = new String[m];
                for (int i = 0; i < m; i++) {
                    line1 = sc.nextLine();
                    tokens2 = line1.split(" ");
                        gl.addEdge(Integer.parseInt(tokens2[0]),
                         Integer.parseInt(tokens2[1]));
                }
                if (Integer.parseInt(tokens2[0])
                 == Integer.parseInt(tokens2[1])) {
                    break;
                } else {
                    System.out.println(gl.toString());
                }
                break;
            case"Matrix":
                if (n == 0 && m == 0) {
                    System.out.println(n + " vertices, " + m + " edges");
                    System.out.println("No edges");
                    break;
                }
                String vtxes = sc.nextLine();
                String[] tkens = vtxes.split(",");
                GraphMatrix gm = new GraphMatrix(n);
                for (int i = 0; i < n; i++) {
                    gm.addVertex(tkens[i]);
                }
                String line;
                String[] tokens = new String[m];
                for (int i = 0; i < m; i++) {
                    line = sc.nextLine();
                    tokens = line.split(" ");
                    gm.addEdge(Integer.parseInt(tokens[0]),
                     Integer.parseInt(tokens[1]));
                }
                if (Integer.parseInt(tokens[0])
                 == Integer.parseInt(tokens[1])) {
                    break;
                } else {
                    System.out.println(gm.toString());
                }
                break;
            default:
            break;
        }
    }
}

