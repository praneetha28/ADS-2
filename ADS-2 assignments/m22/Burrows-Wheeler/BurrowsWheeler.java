import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;
import java.util.HashMap;
import java.util.Arrays;
// import java.util.Queue;
public class BurrowsWheeler {
	private static final int R = 256;
    public static void transform() {
    	String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int first = 0;
        while (first < csa.length() && csa.index(first) != 0) {
            first++;
        }
        BinaryStdOut.write(first);
        for (int i = 0; i < csa.length(); i++) {
            BinaryStdOut.write(s.charAt((csa.index(i) + s.length() - 1) % s.length()));
        }
        BinaryStdOut.close();
    }

    public static void inverseTransform() {
    	HashMap<Character, Queue<Integer>> hmap = new HashMap<Character, Queue<Integer>>();
    	int num = BinaryStdIn.readInt();
    	String str = BinaryStdIn.readString();
    	char[] chars = str.toCharArray();
    	for (int i = 0; i < chars.length; i++) {
    		if (!hmap.containsKey(chars[i])) {
    			hmap.put(chars[i], new Queue<Integer>());
    		}
    			hmap.get(chars[i]).enqueue(i);
    	}
    	Arrays.sort(chars);
    	int[] array = new int[chars.length];
    	for (int i = 0; i < array.length; i++) {
    		array[i] = hmap.get(chars[i]).dequeue();
    	}
    	for (int i = 0; i < array.length; i++) {
    		BinaryStdOut.write(chars[num]);
    		num = array[num];
    	}
    	BinaryStdOut.close();
    }

    public static void main(String[] args) {
    	if (args[0].equals("+")) {
    		inverseTransform();
    	} else {
    		transform();
    	}
    }
}
