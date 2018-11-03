class PageRank {
	private Digraph dg;
	private HashTable<Integer, Double> ht;
	private int size = 0;
	PageRank(final Digraph dig) {
		dg = dig;
		ht = new HashTable<Integer, Double>();
	}
	public void getPR() {
		double res = 0.0;
		double prevpr = 0.25;
		for (int p = 0; p < dg.V(); p++) {
			ht.put(p, prevpr);
		}
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < dg.V(); j++) {
				double temp = 0.0;
				for (int k : dg.adj(i)) {
					temp = ht.get(k) / dg.outdegree(k);
					res = res + temp;
				}
				ht.put(j, res);
			}
		}
	}
	public void String() {
		for (int i : ht.keys()) {
			String s = "";
			s += String.valueOf(i) + " - " + ht.get(i);
			System.out.println(s);
		}
	}
}
