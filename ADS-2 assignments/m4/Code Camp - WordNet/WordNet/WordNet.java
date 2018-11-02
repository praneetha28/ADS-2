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
import java.io.*;
import java.util.*;
import java.util.ArrayList;
public class WordNet {
    private SAP sap;
    private Digraph dg;
    HashTable<String, ArrayList<Integer>> htable;
    HashTable<Integer, String> htable1;
    private int ver = 0;

    WordNet(String synsets, String hypernyms) throws Exception {
        readSynsets(synsets);
        readHypernyms(hypernyms);
        // dg = new Digraph(ver);
        // readHypernyms(hypernyms);
        // sap = new SAP(dg);
    }
    public void readSynsets(String synsets) throws Exception {
        htable = new HashTable<String, ArrayList<Integer>>();
        htable1 = new HashTable<Integer, String>();
        int id = 0;
            Scanner synIn = new Scanner(new File(synsets));
            while (synIn.hasNextLine()) {
                ver++;
                // String line = synIn.readString();
                String[] tokens = synIn.nextLine().split(",");
                id = Integer.parseInt(tokens[0]);
                htable1.put(id, tokens[1]);
                String[] word = tokens[1].split(" ");
                for (int i = 0; i < word.length; i++) {
                    if (htable.contains(word[i])) {
                        ArrayList<Integer> list = htable.get(word[i]);
                        list.add(id);
                        htable.put(word[i], list);
                    } else {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(Integer.parseInt(tokens[0]));
                        htable.put(word[i], list);
                    }
                }
            }
    }

    public void readHypernyms(String hypernyms) throws Exception {
        dg = new Digraph(ver);
        Scanner hyperIn = new Scanner(new File(hypernyms));
        while (hyperIn.hasNextLine()) {
            // String line = ;
            String[] tokens = hyperIn.nextLine().split(",");
            for(int i = 1; i < tokens.length; i++) {
                dg.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
    }
    public void display() {
        int c = 0;
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

    public int distance(String nounA, String nounB) {
        sap = new SAP(dg);
        int dist = sap.length(htable.get(nounA), htable.get(nounB));
        return dist;
    }

    public String sap(String nounA, String nounB) {
        sap = new SAP(dg);
        String str = "";
        int id = sap.ancestor(htable.get(nounA), htable.get(nounB));
        return htable1.get(id);
    }
}