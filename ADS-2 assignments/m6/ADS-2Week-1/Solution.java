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
        int vertices = sc.nextInt();
        sc.nextLine();
        Digraph dig = new Digraph(vertices);
        for (int i = 0; i < vertices; i++) {
            String line = sc.nextLine();
            String[] tokens = line.split(" ");
            for (int j = 1; j < tokens.length; j++) {
                dig.addEdge(Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[j]));
            }
        }
        System.out.println(dig);
        PageRank pgr = new PageRank(dig);
        pgr.string();
        // Create page rank object and pass the graph
        //object to the constructor

        // print the page rank object

        // This part is only for the final test case

        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object and the file path
        // to the constructor

        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky

    }
}

