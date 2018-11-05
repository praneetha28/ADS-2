class EdgeWeightedGraph {
	private int v;
	private int edg;
	private double weight;
	private Bag<Edge>[] adj;
	EdgeWeightedGraph() {
		// addEdge(e);
	}
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		edg++;
	}
	public int vertices() {
		return v;
	}
	public int edg() {
		return edg;
	}
	public Iterable<Edge> adj(int v) {
        // validateVertex(v);
        return adj[v];
    }
    public int degree(int v) {
    	return adj[v].size();
    }
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int i = 0; i < v; i++) {
            int selfLoops = 0;
            for (Edge ed : adj(i)) {
                if (ed.other(i) > i) {
                    list.add(ed);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (ed.other(i) == i) {
                    if (selfLoops % 2 == 0) list.add(ed);
                    selfLoops++;
                }
            }
        }
        return list;
    }
}