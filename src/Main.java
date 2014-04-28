import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws NumberFormatException,
            IOException {
        Reader reader = new Reader();
        Random rand = new Random();
        List<List<Integer>> all = new ArrayList<List<Integer>>();
        int graph[][] = reader.readData("in.txt");
        int n = graph.length;
        Population cl = new Population();
        List<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i < 50; i++) {
            if ((temp = cl.GenerateRandCyc(graph))!=null)
                all.add(temp);
        }
        List<List<Integer>> nextgen = new ArrayList<List<Integer>>();
        int i = 0;
        while (i < all.size()*2) {
            int k = rand.nextInt(all.size());
            int l = rand.nextInt(all.size());
            if (k != l) {
                List<Integer> child = cl.Crossing(all.get(l), all.get(k)); //TODO: ОБЪЕДИНИТЬ МАССИВЫ
                nextgen.add(child);
            }
            i++;
        }
//        all.clear();
//        for (int j=0; j<nextgen.size(); j++) {
//            if (cl.selection(nextgen.get(j), n)) {
//                all.add(nextgen.get(j));
//            }
//            if (cl.isOptimal(nextgen.get(j), n)) {
//               // TODO: Find optimal solution
//            }
//        }
    }
    // TODO: cycle
}