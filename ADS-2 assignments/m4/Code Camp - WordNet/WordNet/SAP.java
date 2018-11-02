public class SAP {
    private Digraph dig;
    int ancestor = -1;
    int len = Integer.MAX_VALUE;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph g) {
        dig = g;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        int a = ancestor(v, w);
        if (a == -1) {
            return -1;
        } else {
            return len;
        }
    }

    public int ancestor(int v, int w) {
        BreadthFirstSearch bfsv = new BreadthFirstSearch(dig, v);
        BreadthFirstSearch bfsw = new BreadthFirstSearch(dig, w);
        for (int i = 0; i < dig.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int vlen = bfsv.distTo(i);
                int wlen = bfsw.distTo(i);
                if (vlen + wlen < len) {
                    len = vlen + wlen;
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int a = ancestor(v, w);
        if (a == -1) {
            return -1;
        } else {
            return len;
        }
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        for (int i : v) {
            for (int j : w) {
                ancestor(i, j);
            }
        }
        return ancestor;
    }

    // do unit testing of this class
    // public static void main(String[] args)
}
