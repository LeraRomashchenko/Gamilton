import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws NumberFormatException,
            IOException {
        long start = System.currentTimeMillis( );
        long cur = System.currentTimeMillis( );
        boolean optimal = false;
        Reader reader = new Reader();
        Random rand = new Random();
        List<List<Integer>> all = new ArrayList<List<Integer>>();
        int graph[][] = reader.readData("in.txt");
        int n = graph.length;
        Population cl = new Population();
        while (cur-start<4000) {
            List<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < 100; i++) {
                temp = cl.GenerateRandCyc(graph);
                if (temp != null) {
                    all.add(temp);
                }
            }
            for (int i = 0; i < all.size(); i++) {
                for (int j = 0; j < all.size(); j++) {
                    if (all.get(i).equals(all.get(j))) {
                        all.remove(i);
                    }
                }
            }
            List<List<Integer>> nextgen = new ArrayList<List<Integer>>();
            int i = 0;
            while (i < all.size() * 4) {
                int k = rand.nextInt(all.size());
                int l = rand.nextInt(all.size());
                if (k != l) {
                    List<Integer> child = cl.Crossing(all.get(l), all.get(k));
                    if (child != null) {
                        for (int h = 1; h < child.size(); h++) {
                            if (child.get(h) == child.get(h - 1)) {
                                child.remove(h);
                            }
                        }
                        nextgen.add(child);
                    }
                }
                i++;
            }
            all.clear();
            for (int j = 0; j < nextgen.size(); j++) {
                if (cl.selection(nextgen.get(j), n)) {
                    all.add(nextgen.get(j));
                    if (cl.isOptimal(nextgen.get(j), n)) {
                        System.out.print("The solution is " + nextgen.get(j) + "\n");
                        optimal = true;
                        break;
                    }
                }
            }
            cur = System.currentTimeMillis();
            if (optimal) break;
        }
        if (!optimal) System.out.print("Can't find gamilton cycle");
    }
}