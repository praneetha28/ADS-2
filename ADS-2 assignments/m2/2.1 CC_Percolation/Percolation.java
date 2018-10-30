class Percolation {
	private boolean[][] grid;
	int size;
	int count = 0;
	GraphList ghl;
	Percolation(int sze) {
		this.size = sze;
		ghl = new GraphList((sze * sze) + 2);
		grid = new boolean[size][size];
	}
	public boolean isOpen(int r, int c) {
		return grid[r][c];
	}
	public int getIndex(int i, int j) {
		return (i*size) + j;
	}
	public void open(int r, int c) {
		if (isOpen(r, c) == false) {
			grid[r][c] = true;
			count += 1;
		}
		if (r == 0) {
			ghl.addEdge(size * size, getIndex(r, c));
		}
		if (r == size - 1) {
			ghl.addEdge((size * size) + 1, getIndex(r, c));
		}
        if (c < size - 1 && isOpen(r, c + 1)) { //bottom
        	ghl.addEdge(getIndex(r, c), getIndex(r, c + 1) );
        }
        if (c > 0 && isOpen(r, c - 1)) { // left
        	ghl.addEdge(getIndex(r, c), getIndex(r, c - 1) );
        }
        if ( r < size - 1 && isOpen(r + 1, c)) { //right
        	ghl.addEdge(getIndex(r, c), getIndex(r + 1, c) );
        }
        if (r > 0 && isOpen(r - 1, c)) { // top
        	ghl.addEdge(getIndex(r, c), getIndex(r - 1, c) );
        }
	}
	// public int numberOfOpenSites() {
	// 	return count;
	// }
	public boolean percolates() {
		ConnectedComponent concom = new ConnectedComponent(ghl);

		return concom.connected(size * size, (size*size) + 1);

	}
}