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
     * the time complexity is N as it iterates through N times.
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        String[] rollnum = new String[num];
        for (int i = 0; i < num; i++) {
            String line = sc.nextLine();
            rollnum[i] = line;
        }
        Quick3string q3str = new Quick3string();
        q3str.sort(rollnum);
        System.out.println(q3str.display(rollnum));
    }
}
