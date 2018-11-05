class Edge {
	private int v;
	private int w;
	private double weight;
	Edge(final int ver, final int edg, final int wt) {
		this.v = ver;
		this.w = edg;
		this.weight = (double) wt;
	}
	public double weight() {
		return weight;
	}
	public int either() {
		return v;
	}
	public int other(final int ver) {
		if (v == ver) {
			return w;
		} else {
			return v;
		}
	}
	// public int compareTo(Edge that) {
	// 	return this.weight.compareTo(that.weight);
	// }
}