class Kruskals {
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;
	private double weight;
	Kruskals(EdgeWeightedGraph ewg) {
		mst = new Queue<Edge>();
		pq = new MinPQ<Edge>();
		for (Edge e : ewg.edges()) {
			pq.insert(e);
		}
		UF uf = new UF(ewg.vertices());
		while (!pq.isEmpty() && mst.size() < ewg.vertices() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
                weight += e.weight();
            }
        }
    }
    public Iterable<Edge> edges() {
        return mst;
    }
    public double weight() {
        return weight;
    }
}
