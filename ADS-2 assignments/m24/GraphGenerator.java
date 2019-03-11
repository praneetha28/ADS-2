// package edu.princeton.cs.algs4;
import java.util.Scanner;
/**
 *  The {@code GraphGenerator} class provides static methods for creating
 *  various graphs, including Erdos-Renyi random graphs, random bipartite
 *  graphs, random k-regular graphs, and random rooted trees.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class GraphGenerator {
    // this class cannot be instantiated
    private GraphGenerator() { }

    /**
     * Returns a random simple graph containing {@code V} vertices and {@code E} edges.
     * @param V the number of vertices
     * @param E the number of vertices
     * @return a random simple graph on {@code V} vertices, containing a total
     *     of {@code E} edges
     * @throws IllegalArgumentException if no such simple graph exists
     */
    public static EdgeWeightedGraph simple(int V, int E) {
        if (E > (long) V*(V-1)/2) throw new IllegalArgumentException("Too many edges");
        if (E < 0)                throw new IllegalArgumentException("Too few edges");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        SET<Edge> set = new SET<Edge>();
        while (G.E() < E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            int weight = StdRandom.uniform(100);
            Edge e = new Edge(v, w, weight);
            if ((v != w) && !set.contains(e)) {
                set.add(e);
                G.addEdge(e);
            }
        }
        return G;
    }

    /**
     * Returns a random simple Graph on {@code V} vertices, with an
     * edge between any two vertices with probability {@code p}. This is sometimes
     * referred to as the Erdos-Renyi random graph model.
     * @param V the number of vertices
     * @param p the probability of choosing an edge
     * @return a random simple graph on {@code V} vertices, with an edge between
     *     any two vertices with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static EdgeWeightedGraph simple(int V, double p) {
        int weight;
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        for (int v = 0; v < V; v++) {
            for (int w = v+1; w < V; w++) {
                if (StdRandom.bernoulli(p)) {
                    weight =StdRandom.uniform(100);
                    G.addEdge(new Edge(v, w, weight));
                }
            }
        }
        return G;
    }

    /**
     * Returns the complete graph on {@code V} vertices.
     * @param V the number of vertices
     * @return the complete graph on {@code V} vertices
     */
    public static EdgeWeightedGraph complete(int V) {
        return simple(V, 1.0);
    }

    /**
     * Returns a complete bipartite graph on {@code V1} and {@code V2} vertices.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @return a complete bipartite graph on {@code V1} and {@code V2} vertices
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static EdgeWeightedGraph completeBipartite(int V1, int V2) {
        return bipartite(V1, V2, V1*V2);
    }

    /**
     * Returns a random simple bipartite graph on {@code V1} and {@code V2} vertices
     * with {@code E} edges.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param E the number of edges
     * @return a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     *    containing a total of {@code E} edges
     * @throws IllegalArgumentException if no such simple bipartite graph exists
     */
    public static EdgeWeightedGraph bipartite(int V1, int V2, int E) {
        if (E > (long) V1*V2) throw new IllegalArgumentException("Too many edges");
        if (E < 0)            throw new IllegalArgumentException("Too few edges");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V1 + V2);

        int[] vertices = new int[V1 + V2];
        for (int i = 0; i < V1 + V2; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        SET<Edge> set = new SET<Edge>();
        while (G.E() < E) {
            int i = StdRandom.uniform(V1);
            int j = V1 + StdRandom.uniform(V2);
            int weight = StdRandom.uniform(100);
            Edge e = new Edge(vertices[i], vertices[j], weight);
            if (!set.contains(e)) {
                set.add(e);
                G.addEdge(e);
            }
        }
        return G;
    }

    /**
     * Returns a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     * containing each possible edge with probability {@code p}.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param p the probability that the graph contains an edge with one endpoint in either side
     * @return a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     *    containing each possible edge with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static EdgeWeightedGraph bipartite(int V1, int V2, double p) {
        int weight;
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        int[] vertices = new int[V1 + V2];
        for (int i = 0; i < V1 + V2; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        EdgeWeightedGraph G = new EdgeWeightedGraph(V1 + V2);
        for (int i = 0; i < V1; i++) {
            for (int j = 0; j < V2; j++) {
                weight = StdRandom.uniform(100);
                if (StdRandom.bernoulli(p)) {
                    G.addEdge(new Edge(vertices[i], vertices[V1+j], weight));
                }
            }
        }
        return G;
    }

    /**
     * Returns a path graph on {@code V} vertices.
     * @param V the number of vertices in the path
     * @return a path graph on {@code V} vertices
     */
    public static EdgeWeightedGraph path(int V) {
        int weight;
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[i+1], weight));
        }
        return G;
    }

    /**
     * Returns a complete binary tree graph on {@code V} vertices.
     * @param V the number of vertices in the binary tree
     * @return a complete binary tree graph on {@code V} vertices
     */
    public static EdgeWeightedGraph binaryTree(int V) {
        int weight;
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 1; i < V; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[(i-1)/2], weight));
        }
        return G;
    }

    /**
     * Returns a cycle graph on {@code V} vertices.
     * @param V the number of vertices in the cycle
     * @return a cycle graph on {@code V} vertices
     */
    public static EdgeWeightedGraph cycle(int V) {
        int weight;
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[i+1], weight));
        }
        weight = StdRandom.uniform(100);
        G.addEdge(new Edge(vertices[V-1], vertices[0], weight));
        return G;
    }

    /**
     * Returns an Eulerian cycle graph on {@code V} vertices.
     *
     * @param  V the number of vertices in the cycle
     * @param  E the number of edges in the cycle
     * @return a graph that is an Eulerian cycle on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E <= 0}
     */
    public static EdgeWeightedGraph eulerianCycle(int V, int E) {
        int weight;
        if (E <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one edge");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one vertex");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[E];
        for (int i = 0; i < E; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E-1; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[i+1], weight));
        }
        weight = StdRandom.uniform(100);
        G.addEdge(new Edge(vertices[E-1], vertices[0], weight));
        return G;
    }

    /**
     * Returns an Eulerian path graph on {@code V} vertices.
     *
     * @param  V the number of vertices in the path
     * @param  E the number of edges in the path
     * @return a graph that is an Eulerian path on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E < 0}
     */
    public static EdgeWeightedGraph eulerianPath(int V, int E) {
        int weight;
        if (E < 0)
            throw new IllegalArgumentException("negative number of edges");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian path must have at least one vertex");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[E+1];
        for (int i = 0; i < E+1; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[i+1], weight));
        }
        return G;
    }

    /**
     * Returns a wheel graph on {@code V} vertices.
     * @param V the number of vertices in the wheel
     * @return a wheel graph on {@code V} vertices: a single vertex connected to
     *     every vertex in a cycle on {@code V-1} vertices
     */
    public static EdgeWeightedGraph wheel(int V) {
        int weight;
        if (V <= 1) throw new IllegalArgumentException("Number of vertices must be at least 2");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        // simple cycle on V-1 vertices
        for (int i = 1; i < V-1; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[i], vertices[i+1], weight));
        }
        weight = StdRandom.uniform(100);
        G.addEdge(new Edge(vertices[V-1], vertices[1], weight));

        // connect vertices[0] to every vertex on cycle
        for (int i = 1; i < V; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[0], vertices[i], weight));
        }
        return G;
    }

    /**
     * Returns a star graph on {@code V} vertices.
     * @param V the number of vertices in the star
     * @return a star graph on {@code V} vertices: a single vertex connected to
     *     every other vertex
     */
    public static EdgeWeightedGraph star(int V) {
        int weight;
        if (V <= 0) throw new IllegalArgumentException("Number of vertices must be at least 1");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);

        // connect vertices[0] to every other vertex
        for (int i = 1; i < V; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[0], vertices[i], weight));
        }

        return G;
    }

    /**
     * Returns a uniformly random {@code k}-regular graph on {@code V} vertices
     * (not necessarily simple). The graph is simple with probability only about e^(-k^2/4),
     * which is tiny when k = 14.
     *
     * @param V the number of vertices in the graph
     * @param k degree of each vertex
     * @return a uniformly random {@code k}-regular graph on {@code V} vertices.
     */
    public static EdgeWeightedGraph regular(int V, int k) {
        int weight;
        if (V*k % 2 != 0) throw new IllegalArgumentException("Number of vertices * k must be even");
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);

        // create k copies of each vertex
        int[] vertices = new int[V*k];
        for (int v = 0; v < V; v++) {
            for (int j = 0; j < k; j++) {
                vertices[v + V*j] = v;
            }
        }

        // pick a random perfect matching
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V*k/2; i++) {
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(vertices[2*i], vertices[2*i + 1], weight));
        }
        return G;
    }

    // http://www.proofwiki.org/wiki/Labeled_Tree_from_Prüfer_Sequence
    // http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.36.6484&rep=rep1&type=pdf
    /**
     * Returns a uniformly random tree on {@code V} vertices.
     * This algorithm uses a Prufer sequence and takes time proportional to <em>V log V</em>.
     * @param V the number of vertices in the tree
     * @return a uniformly random tree on {@code V} vertices
     */
    public static EdgeWeightedGraph tree(int V) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        int weight;
        // special case
        if (V == 1) return G;

        // Cayley's theorem: there are V^(V-2) labeled trees on V vertices
        // Prufer sequence: sequence of V-2 values between 0 and V-1
        // Prufer's proof of Cayley's theorem: Prufer sequences are in 1-1
        // with labeled trees on V vertices
        int[] prufer = new int[V-2];
        for (int i = 0; i < V-2; i++)
            prufer[i] = StdRandom.uniform(V);

        // degree of vertex v = 1 + number of times it appers in Prufer sequence
        int[] degree = new int[V];
        for (int v = 0; v < V; v++)
            degree[v] = 1;
        for (int i = 0; i < V-2; i++)
            degree[prufer[i]]++;

        // pq contains all vertices of degree 1
        MinPQ<Integer> pq = new MinPQ<Integer>();
        for (int v = 0; v < V; v++)
            if (degree[v] == 1) pq.insert(v);

        // repeatedly delMin() degree 1 vertex that has the minimum index
        for (int i = 0; i < V-2; i++) {
            int v = pq.delMin();
            weight = StdRandom.uniform(100);
            G.addEdge(new Edge(v, prufer[i], weight));
            degree[v]--;
            degree[prufer[i]]--;
            if (degree[prufer[i]] == 1) pq.insert(prufer[i]);
        }
        weight = StdRandom.uniform(100);
        G.addEdge(new Edge(pq.delMin(), pq.delMin(), weight));
        return G;
    }

    /**
     * Unit tests the {@code GraphGenerator} library.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        int V1 = V/2;
        int V2 = V - V1;
        int count = 0;
        StdOut.println("complete graph");
        EdgeWeightedGraph graph = complete(V);
        StdOut.println(graph);
        Kruskals krs = new Kruskals(graph);
        MaxPQ<Integer> maxpq = new MaxPQ<Integer>();
        for (Edge e : krs.edges()) {
            maxpq.insert(e.weight());
        }
        System.out.print("highest weight : ");
        System.out.println(maxpq.max());
        int max = maxpq.delMax();
        for (Edge e : graph.edges()) {
            if (e.weight() < max) {
                count = count + 1;
            }
        }
        System.out.println(count);
        StdOut.println();

        StdOut.println("simple");
        EdgeWeightedGraph g = simple(V, E);
        StdOut.println(g);
        Kruskals k = new Kruskals(g);
        MaxPQ<Integer> mpq = new MaxPQ<Integer>();
        for (Edge e : k.edges()) {
            mpq.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max1 = mpq.delMax();
        System.out.println(max1);
        int c = 0;
        for (Edge e : g.edges()) {
            if (e.weight() < max1) {
                c = c + 1;
            }
        }
        System.out.println(c);
        StdOut.println();

        StdOut.println("Erdos-Renyi");
        double p = (double) E / (V*(V-1)/2.0);
        EdgeWeightedGraph g1 = simple(V, p);
        StdOut.println(g1);
        Kruskals k1 = new Kruskals(g1);
        MaxPQ<Integer> mpq1 = new MaxPQ<Integer>();
        for (Edge e : k1.edges()) {
            mpq1.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max2 = mpq1.delMax();
        System.out.println(max2);
        int c1 = 0;
        for (Edge e : g1.edges()) {
            if (e.weight() < max2) {
                c1 = c1 + 1;
            }
        }
        System.out.println(c1);
        StdOut.println();

        StdOut.println("complete bipartite");
        EdgeWeightedGraph g2 = completeBipartite(V1, V2);
        StdOut.println(g2);
        Kruskals k2 = new Kruskals(g2);
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        for (Edge e : k2.edges()) {
            pq.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max3 = pq.delMax();
        System.out.println(max3);
        int c2 = 0;
        for (Edge e : g2.edges()) {
            if (e.weight() < max3) {
                c2 = c2 + 1;
            }
        }
        System.out.println(c2);
        StdOut.println();

        StdOut.println("bipartite");
        EdgeWeightedGraph g3 = bipartite(V1, V2, E);
        StdOut.println(g3);
        Kruskals k3 = new Kruskals(g3);
        MaxPQ<Integer> pq1 = new MaxPQ<Integer>();
        for (Edge e : k3.edges()) {
            pq1.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max4 = pq1.delMax();
        System.out.println(max4);
        int c3 = 0;
        for (Edge e : g3.edges()) {
            if (e.weight() < max4) {
                c3 = c3 + 1;
            }
        }
        System.out.println(c3);
        StdOut.println();

        StdOut.println("Erdos Renyi bipartite");
        double q = (double) E / (V1*V2);
        EdgeWeightedGraph g4 = bipartite(V1, V2, q);
        StdOut.println(g4);
        Kruskals k4 = new Kruskals(g4);
        MaxPQ<Integer> pq2 = new MaxPQ<Integer>();
        for (Edge e : k4.edges()) {
            pq2.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max5 = pq2.delMax();
        System.out.println(max5);
        int c4 = 0;
        for (Edge e : g4.edges()) {
            if (e.weight() < max5) {
                c4 = c4 + 1;
            }
        }
        System.out.println(c4);
        StdOut.println();

        StdOut.println("path");
        EdgeWeightedGraph g5 = path(V);
        StdOut.println(g5);
        Kruskals k5 = new Kruskals(g5);
        MaxPQ<Integer> pq3 = new MaxPQ<Integer>();
        for (Edge e : k5.edges()) {
            pq3.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max6 = pq3.delMax();
        System.out.println(max6);
        int c5 = 0;
        for (Edge e : g5.edges()) {
            if (e.weight() < max6) {
                c5 = c5 + 1;
            }
        }
        System.out.println(c5);
        StdOut.println();

        StdOut.println("cycle");
        EdgeWeightedGraph g6 = cycle(V);
        StdOut.println(g6);
        Kruskals k6 = new Kruskals(g6);
        MaxPQ<Integer> pq4 = new MaxPQ<Integer>();
        for (Edge e : k6.edges()) {
            pq4.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max7 = pq4.delMax();
        System.out.println(max7);
        int c6 = 0;
        for (Edge e : g6.edges()) {
            if (e.weight() < max7) {
                c6 = c6 + 1;
            }
        }
        System.out.println(c6);
        StdOut.println();

        StdOut.println("binary tree");
        EdgeWeightedGraph g7 = binaryTree(V);
        StdOut.println(g7);
        Kruskals k7 = new Kruskals(g7);
        MaxPQ<Integer> pq5 = new MaxPQ<Integer>();
        for (Edge e : k7.edges()) {
            pq5.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max8 = pq5.delMax();
        System.out.println(max8);
        int c7 = 0;
        for (Edge e : g7.edges()) {
            if (e.weight() < max8) {
                c7 = c7 + 1;
            }
        }
        System.out.println(c7);
        StdOut.println();

        StdOut.println("tree");
        EdgeWeightedGraph g8 = tree(V);
        StdOut.println(g8);
        Kruskals k8 = new Kruskals(g8);
        MaxPQ<Integer> pq6 = new MaxPQ<Integer>();
        for (Edge e : k8.edges()) {
            pq6.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max9 = pq6.delMax();
        System.out.println(max9);
        int c8 = 0;
        for (Edge e : g8.edges()) {
            if (e.weight() < max9) {
                c8 = c8 + 1;
            }
        }
        System.out.println(c8);
        StdOut.println();

        StdOut.println("4-regular");
        EdgeWeightedGraph g9 = regular(V, 4);
        StdOut.println(g9);
        Kruskals k9 = new Kruskals(g9);
        MaxPQ<Integer> pq7 = new MaxPQ<Integer>();
        for (Edge e : k9.edges()) {
            pq7.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max10 = pq7.delMax();
        System.out.println(max10);
        int c9 = 0;
        for (Edge e : g9.edges()) {
            if (e.weight() < max10) {
                c9 = c9 + 1;
            }
        }
        System.out.println(c9);
        StdOut.println();

        StdOut.println("star");
        EdgeWeightedGraph g10 = star(V);
        StdOut.println(g10);
        Kruskals k10 = new Kruskals(g10);
        MaxPQ<Integer> pq8 = new MaxPQ<Integer>();
        for (Edge e : k10.edges()) {
            pq8.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max11 = pq8.delMax();
        System.out.println(max11);
        int c10 = 0;
        for (Edge e : g10.edges()) {
            if (e.weight() < max11) {
                c10 = c10 + 1;
            }
        }
        System.out.println(c10);
        StdOut.println();

        StdOut.println("wheel");
        EdgeWeightedGraph g11 = wheel(V);
        StdOut.println(g11);
        Kruskals k11 = new Kruskals(g11);
        MaxPQ<Integer> pq9 = new MaxPQ<Integer>();
        for (Edge e : k11.edges()) {
            pq9.insert(e.weight());
        }
        System.out.print("highest weight : ");
        int max12 = pq9.delMax();
        System.out.println(max12);
        int c11 = 0;
        for (Edge e : g11.edges()) {
            if (e.weight() < max12) {
                c11 = c11 + 1;
            }
        }
        System.out.println(c11);
        StdOut.println();
    }

}