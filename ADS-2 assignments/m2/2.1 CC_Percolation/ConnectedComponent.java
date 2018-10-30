public class ConnectedComponent {
    private boolean[] marked;
    private int[] id;
    private int[] size;
    private int count;

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph
     */
    public ConnectedComponent(GraphList g) {
        marked = new boolean[g.vert()];
        id = new int[g.vert()];
        size = new int[g.vert()];
        for (int v = 0; v < g.vert(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    // /**
    //  * Computes the connected components of the edge-weighted graph {@code G}.
    //  *
    //  * @param G the edge-weighted graph
    //  */
    // public ConnectedComponent(EdgeWeightedGraph G) {
    //     marked = new boolean[G.V()];
    //     id = new int[G.V()];
    //     size = new int[G.V()];
    //     for (int v = 0; v < G.V(); v++) {
    //         if (!marked[v]) {
    //             dfs(G, v);
    //             count++;
    //         }
    //     }
    // }

    private void dfs(GraphList g, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }
    // private void dfs(EdgeWeightedGraph G, int v) {
    //     marked[v] = true;
    //     id[v] = count;
    //     size[count]++;
    //     for (Edge e : G.adj(v)) {
    //         int w = e.other(v);
    //         if (!marked[w]) {
    //             dfs(G, w);
    //         }
    //     }
    // }


    /**
     * Returns the component id of the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the component id of the connected component containing vertex {@code v}
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * Returns the number of vertices in the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the number of vertices in the connected component containing vertex {@code v}
     */
    public int size(int v) {
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return the number of connected components in the graph {@code G}
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     */
    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     */
    public boolean areConnected(int v, int w) {
        return id(v) == id(w);
    }
}