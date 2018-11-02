public class SAP {
    private Digraph dig;
    private static final int INFINITY = Integer.MAX_VALUE;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph g) {
        dig = g;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        BreadthFirstSearch bfsv = new BreadthFirstSearch(dig, v);
        BreadthFirstSearch bfsw = new BreadthFirstSearch(dig, w);
        return sap(bfsv, bfsw, true);
    }

    public int ancestor(int v, int w) {
        BreadthFirstSearch bfsv = new BreadthFirstSearch(dig, v);
        BreadthFirstSearch bfsw = new BreadthFirstSearch(dig, w);
        return sap(bfsv, bfsw, false);
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstSearch bfsv = new BreadthFirstSearch(dig, v);
        BreadthFirstSearch bfsw = new BreadthFirstSearch(dig, w);
        return sap(bfsv, bfsw, true);
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstSearch bfsv = new BreadthFirstSearch(dig, v);
        BreadthFirstSearch bfsw = new BreadthFirstSearch(dig, w);
        return sap(bfsv, bfsw, false);
    }
    public int sap(BreadthFirstSearch v, BreadthFirstSearch w) {
        int ancestor = -1;
        int len = INFINITY;
        for (int i = 0; i < dig.V(); i++) {
            if (v.hasPath(i) && w.hasPath(i)) {
                int vlen = v.dist(i);
                int wlen = w.dist(i);
                if (vlen + wlen < len) {
                    len = vlen + wlen;
                    ancestor = 1;
                }
            }
        }
        if (length) {
            return minlen < INFINITY ? minlen : -1;
        }
        else {
            return ancestor;
        }
    }

    // do unit testing of this class
    // public static void main(String[] args)
}
