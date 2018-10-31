/**.
 * Class for directed cycle.
 */
public class DirectedCycle {
    /**.
     * { var_description }
     */
    private boolean[] marked;
    /**.
     * { var_description }
     */
    private int[] edgeTo;
    /**.
     * { var_description }
     */
    private boolean[] onStack;
    /**.
     * { var_description }
     */
    private Stack<Integer> cycle;
    /**.
     * Determines whether the digraph has a directed cycle and, if so,
     * finds such a cycle.
     * @param g the digraph
     */
    public DirectedCycle(final Digraph g) {
        marked  = new boolean[g.vert()];
        onStack = new boolean[g.vert()];
        edgeTo  = new int[g.vert()];
        // for (int v = 0; v < g.vert(); v++) {
        //     if (!marked[v]) {
        //         dfs(g, v);
        //     }
        // }
    }
    // /**.
    //  * { function_description }
    //  *
    //  * @param      g     { parameter_description }
    //  * @param      v     { parameter_description }
    //  */
    // private void dfs(final Digraph g, final int v) {
    //     onStack[v] = true;
    //     marked[v] = true;
    //     for (int w : g.adj(v)) {
    //         if (cycle != null) {
    //             return;
    //         } else if (!marked[w]) {
    //             edgeTo[w] = v;
    //             dfs(g, w);
    //         } else if (onStack[w]) {
    //             cycle = new Stack<Integer>();
    //             for (int x = v; x != w; x = edgeTo[x]) {
    //                 cycle.push(x);
    //             }
    //             cycle.push(w);
    //             cycle.push(v);
    //             // assert check();
    //         }
    //     }
    //     onStack[v] = false;
    // }
    /**.
     * Determines if it has cycle.
     *
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return cycle != null;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }
    public boolean dfs(final Digraph g) {
        boolean r = false;
        for (int v = 0; v < g.vert(); v++) {
            if (!marked[v]) {
                r = dfs(g, v);
            }
        }
        return r;
    }
    public boolean dfs(final Digraph g, final int v) {
        // onStack[v] = true;
        if (marked[v]) {
            return false;
        } else {
            marked[v] = true;
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    dfs(g, w);
                }
            }
        }
        return false;
    }
}
