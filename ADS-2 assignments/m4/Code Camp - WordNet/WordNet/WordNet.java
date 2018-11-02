/*public class WordNet {

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)

    // returns all WordNet nouns
    public Iterable<String> nouns()

    // is the word a WordNet noun?
    public boolean isNoun(String word)

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)

    // do unit testing of this class
    public static void main(String[] args)
}
*/
import java.util.HashMap;
public class WordNet {
    // private SAP sap;
    // private Digraph dg;
    private int ver = 0;
    // private HashMap<Integer, Bag<String>> idmap;
    // private HashMap<String, Bag<Integer>> wordmap;
    WordNet(String synsets, String hypernyms) {
        readSynsets(synsets, hypernyms);
        // dg = new Digraph(ver);
        // readHypernyms(hypernyms);
        // sap = new SAP(dg);
    }
    public void readSynsets(String synsets, String hypernyms) {
        int id = 0;
        try {
            In synIn = new In("./Files/" + synsets);
            while (!synIn.isEmpty()) {
                ver++;
                // String line = synIn.readString();
                String[] tokens = synIn.readString().split(",");
                id = Integer.parseInt(tokens[0]);
                String[] word = tokens[1].split(" ");
            }
            Digraph dg = new Digraph(ver);
            readHypernyms(hypernyms, dg);
        } catch (Exception e) {
            System.out.println("File not found");
        }

    }

    public void readHypernyms(String hypernyms, Digraph dg) {
        int c = 0;
        In hyperIn = new In("./Files/" + hypernyms);
        while (!hyperIn.isEmpty()) {
            // String line = ;
            String[] tokens = hyperIn.readString().split(",");
            for(int i = 1; i < tokens.length; i++) {
                dg.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
            DirectedCycle dc = new DirectedCycle(dg);
            for (int i = 0; i < ver; i++) {
                if (dg.outdegree(i) == 0) {
                    c++;
                }
            }
            if (c > 1) {
                System.out.println("Multiple roots");
            } else if (dc.hasCycle()) {
                System.out.println("Cycle detected");
            } else {
                System.out.println(dg);
            }
    }
}