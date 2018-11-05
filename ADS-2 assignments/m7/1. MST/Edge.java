class Edge implements Comparable<Edge>{
	private int v;
	private int w;
	private double weight;
	Edge(final int ver, final int edg, final double wt) {
		this.v = ver;
		this.w = edg;
		this.weight = wt;
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
    public int compareTo(Edge that) {
        if (this.weight < that.weight) {
        	return -1;
        } else if (this.weight > that.weight) {
        	return 1;
        } else {
        	return 0;
        }
    }
}