/**.
 * { item_description }
 */
import java.util.Scanner;
/**.
 * Class for solution.
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
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        sc.nextLine();
        EdgeWeightedGraph edwgh = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = sc.nextLine().split(" ");
            edwgh.addEdge(new Edge(Integer.parseInt(tokens[0]),
             Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2])));
        }
        String caseToGo = sc.nextLine();
        switch (caseToGo) {
        case "Graph":
            //Print the Graph Object.
            System.out.println(edwgh);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] line = sc.nextLine().split(" ");
            Dijkstras dij = new Dijkstras(
                edwgh, Integer.parseInt(line[0]));
            double num = dij.distTo(Integer.parseInt(line[1]));
            if (!dij.hasPathTo(Integer.parseInt(line[1]))) {
                System.out.println("No Path Found.");
            } else {
                System.out.println(num);
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the
            //  one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] line1 = sc.nextLine().split(" ");
            String str = line1[0] + " ";
            Dijkstras dijk = new Dijkstras(
                edwgh, Integer.parseInt(line1[0]));
            Dijkstras dijk1 = new Dijkstras(
                edwgh, Integer.parseInt(line1[1]));
            double num1 = dijk.distTo(Integer.parseInt(line1[1]));
            double num2 = dijk1.distTo(Integer.parseInt(line1[2]));
            if (!dijk.hasPathTo(Integer.parseInt(line1[1]))
                || !dijk.hasPathTo(Integer.parseInt(line1[2]))) {
                System.out.println("No Path Found.");
            } else {
                System.out.println(num1 + num2);
                for (Edge e : dijk.pathTo(Integer.parseInt(line1[1]))) {
                    str += e.either() + " ";
                }
                int viaver = Integer.parseInt(line1[1]);
                for (Edge e : dijk1.pathTo(Integer.parseInt(line1[2]))) {
                    int temp = e.either();
                    if (viaver == temp) {
                        str += e.other(temp) + " ";
                    } else {
                        str += temp + " ";
                    }
                    viaver = temp;
                }
                System.out.println(str);
            }
            break;

        default:
            break;
        }

    }
}
