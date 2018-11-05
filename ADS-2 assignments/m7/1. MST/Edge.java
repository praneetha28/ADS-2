/**.
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**.
     * { var_description }
     */
    private int v;
    /**.
     * { var_description }
     */
    private int w;
    /**.
     * { var_description }
     */
    private double weight;
    /**.
     * Constructs the object.
     *
     * @param      ver   The version
     * @param      edg   The edg
     * @param      wt    { parameter_description }
     */
    Edge(final int ver, final int edg, final double wt) {
        this.v = ver;
        this.w = edg;
        this.weight = wt;
    }
    /**.
     * { function_description }
     * TIME complexity in average case is 1.
     * @return     { description_of_the_return_value }
     */
    public double weight() {
        return weight;
    }
    /**.
     * { function_description }
     * TIME complexity in average case is 1.
     * @return     { description_of_the_return_value }
     */
    public int either() {
        return v;
    }
    /**.
     * { function_description }
     *  TIME complexity in average case is 1.
     * @param      ver   The version
     *
     * @return     { description_of_the_return_value }
     */
    public int other(final int ver) {
        if (v == ver) {
            return w;
        } else {
            return v;
        }
    }
    /**.
     * { function_description }
     * TIME complexity in average case is 1.
     * @param      that  The that
     *
     * @return     { description_of_the_return_value }
     */
    public int compareTo(final Edge that) {
        if (this.weight < that.weight) {
            return -1;
        } else if (this.weight > that.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}
