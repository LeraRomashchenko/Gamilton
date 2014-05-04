import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
    public List<Integer> GenerateRandExemplar(int graph[][]) {
        List<Integer> cycle = new ArrayList<>();
        Random rand = new Random();
        int i1 = rand.nextInt(graph.length);
        int i2 = rand.nextInt(graph[i1].length);
        int top0 = graph[i1][i2];
        cycle.add(top0);
        int j = rand.nextInt(graph[top0 - 1].length);
        int cur = graph[top0 - 1][j];
        cycle.add(cur);
        int controller = 0;
        while (!(cur == top0) && (controller < graph.length)) {
            int i = rand.nextInt(graph[cur - 1].length);
            int cur_ = graph[cur - 1][i];
            boolean f = false;
            for (int k = 1; k < cycle.size(); k++) {
                if (cycle.get(k) == cur_) {
                    f = true;
                    break;
                }
            }
            if (!f) {
                cycle.add(cur_);
                cur = cur_;
            } else {
                controller++;
            }
        }
        if (controller != graph.length) {
            return cycle;
        }
        return null;
    }

    public List<Integer> Crossing(List<Integer> list, List<Integer> list2) {
        boolean reverse = false;
        Random rand = new Random();
        List<Integer> child1 = null;
        List<Integer> child2 = null;
        int gap_left = rand.nextInt(list.size());
        int gap_right = rand.nextInt(list.size());
        if (gap_left > gap_right) {
            int temp = gap_left;
            gap_left = gap_right;
            gap_right = temp;
        }
        int top_left = list.get(gap_left);
        int top_right = list.get(gap_right);
        if (list2.contains(top_left) && (list2.contains(top_right))) {
            int gap_left_ = list2.indexOf(top_left);
            int gap_right_ = list2.indexOf(top_right);
            if (gap_left_ > gap_right_) {
                int temp = gap_left_;
                gap_left_ = gap_right_;
                gap_right_ = temp;
                reverse = true;
            }
            List<Integer> list_ = new ArrayList<>(list);
            List<Integer> list__ = new ArrayList<>(list);
            List<Integer> list2_ = new ArrayList<>(list2);
            List<Integer> child11 = list_.subList(0, gap_left);
            List<Integer> child12 = list2_.subList(gap_left_, gap_right_ + 1);
            if (reverse) {
                List<Integer> revArray = new ArrayList<>();
                int n = child12.size();
                for (int i = 0; i < n; i++) {
                    revArray.add(child12.get(n - i - 1));
                }
                child12.clear();
                child12 = new ArrayList<>(revArray);
            }
            List<Integer> child13 = list__.subList(gap_right, list.size());
            child1 = child11;
            child1.addAll(child12);
            child1.addAll(child13);

            reverse = false;
            int gap2_left_ = list2.indexOf(top_left);
            int gap2_right_ = list2.indexOf(top_right);
            if (gap2_left_ > gap2_right_) {
                int temp = gap2_left_;
                gap2_left_ = gap2_right_;
                gap2_right_ = temp;
                reverse = true;
            }
            List<Integer> list2_second = new ArrayList<>(list2);
            List<Integer> list2_second_ = new ArrayList<>(list2);
            List<Integer> list_second = new ArrayList<>(list);
            List<Integer> child21 = list2_second.subList(0, gap2_left_);
            List<Integer> child22 = list_second.subList(gap_left, gap_right  + 1);
            if (reverse) {
                List<Integer> revArray = new ArrayList<>();
                int n = child22.size();
                for (int i = 0; i < n; i++) {
                    revArray.add(child22.get(n - i - 1));
                }
                child22.clear();
                child22 = new ArrayList<>(revArray);
            }
            List<Integer> child23 = list2_second_.subList(gap2_right_, list2.size());
            child2 = child21;
            child2.addAll(child22);
            child2.addAll(child23);
        }
        if (rand.nextBoolean())
            return child1;
        return child2;
    }

    public boolean selection(List<Integer> person, int n) {
        boolean f = true;
        for (int i = 1; i < person.size(); i++) {
            for (int j = 1; j < person.size(); j++) {
                if ((i != j) && person.get(i) == person.get(j)) {
                    f = false;
                    break;
                }
                if (!f) break;
            }
        }
        return (person.size() >= n / 3) && f;
    }

    public boolean isOptimal(List<Integer> person, int n) {
        return person.size() == n + 1;
    }
}
