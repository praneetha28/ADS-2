/**.
 * Class for percolation.
 */
class Percolation {
    /**.
     * { var_description }
     */
    private boolean[][] grid;
    /**.
     * { var_description }
     */
    private int size;
    /**.
     * { var_description }
     */
    private int count = 0;
    /**.
     * { var_description }
     */
    private GraphList ghl;
    /**.
     * Constructs the object.
     *
     * @param      sze   The sze
     */
    Percolation(final int sze) {
        this.size = sze;
        ghl = new GraphList((sze * sze) + 2);
        grid = new boolean[size][size];
    }
    /**.
     * Gets the index.
     * time complexity in average case is 1.
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     *
     * @return     The index.
     */
    public int getIndex(final int i, final int j) {
        return (i * size) + j;
    }
    /**.
     * { function_description }
     * time complexity in average case is 1.
     * @param      r     { parameter_description }
     * @param      c     { parameter_description }
     */
    public void open(final int r, final int c) {

            grid[r][c] = true;
            count += 1;
        if (r == 0) {
            ghl.addEdge(size * size, getIndex(r, c));
        }
        if (r == size - 1) {
            ghl.addEdge((size * size) + 1, getIndex(r, c));
        }
        if (c < size - 1 && grid[r][c + 1]) { //bottom
            ghl.addEdge(getIndex(r, c), getIndex(r, c + 1));
        }
        if (c > 0 && grid[r][c - 1]) { // left
            ghl.addEdge(getIndex(r, c), getIndex(r, c - 1));
        }
        if (r < size - 1 && grid[r + 1][c]) { //right
            ghl.addEdge(getIndex(r, c), getIndex(r + 1, c));
        }
        if (r > 0 && grid[r - 1][c]) { // top
            ghl.addEdge(getIndex(r, c), getIndex(r - 1, c));
        }
    }
    /**.
     * { function_description }
     * time complexity in average case is 1.
     * @return     { description_of_the_return_value }
     */
    public boolean percolates() {
        ConnectedComponent concom = new ConnectedComponent(ghl);
        return concom.connected(size * size, (size * size) + 1);
    }
}
