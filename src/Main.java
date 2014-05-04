import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws NumberFormatException,
            IOException {
        long start = System.currentTimeMillis();
        long cur = System.currentTimeMillis();
        long end;
        boolean optimal = false;
        Reader reader = new Reader();
        Random rand = new Random();
        List<List<Integer>> all = new ArrayList<>();
        int graph[][] = reader.readData("in.txt");
        int n = graph.length;
        Population population = new Population();
        while (cur - start < 30000) {
            List<Integer> temp;
            for (int i = 0; i < 100; i++) {
                temp = population.GenerateRandExemplar(graph);
                if (temp != null) {
                    all.add(temp);
                }
            }
            List<List<Integer>> nextgen = new ArrayList<>();
            int i = 0;
            while (i < all.size() * 4) {
                int k = rand.nextInt(all.size());
                int l = rand.nextInt(all.size());
                if (k != l) {
                    List<Integer> child = population.Crossing(all.get(l), all.get(k));
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
                if (population.selection(nextgen.get(j), n)) {
                    all.add(nextgen.get(j));
                    if (population.isOptimal(nextgen.get(j), n)) {
                        end = System.currentTimeMillis();
                        System.out.print("The solution is " + nextgen.get(j) + "\n");
                        System.out.print("The time " + (end-start) + "\n");
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