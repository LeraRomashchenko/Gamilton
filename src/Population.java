import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

public class Population {
	public List<Integer> GenerateRandCyc(int graph[][]) {
		List<Integer> cycle = new ArrayList<Integer>();
		Random rand = new Random();
		int i1 = rand.nextInt(graph.length);
		int i2 = rand.nextInt(graph[i1].length);
		int top0 = graph[i1][i2];
		cycle.add(top0);
		int j = rand.nextInt(graph[top0-1].length);
		int cur = graph[top0-1][j];
		cycle.add(cur);
        int controller = 0;
		while (!(cur ==top0)&&(controller < graph.length)) {
			int i = rand.nextInt(graph[cur -1].length);
            int cur_ = graph[cur -1][i];
            boolean f = false;
            for (int k = 1; k<cycle.size(); k++) {
                if (cycle.get(k)== cur_) {
                   f = true;
                   break;
                }
            }
			if (!f) {
                cycle.add(cur_);
                cur = cur_;
             }
                else {
                controller++;
            }
		}
        if (controller!=graph.length) {
            return cycle;
        }
        return null;
	}
	
	public List<Integer> Crossing(List<Integer> list, List<Integer> list2) throws ConcurrentModificationException {
		Random rand = new Random();
		List<Integer> child1 = null;
		//List<Integer> child2 = null;
		int gap_left = rand.nextInt(list.size());
        int gap_right = rand.nextInt(list.size());
		if (gap_left > gap_right) {
			int temp = gap_left;
			gap_left = gap_right;
			gap_right = temp;
		}
		int top_left = list.get(gap_left);
		int top_right = list.get(gap_right);
		if (list2.contains(top_left)&&(list2.contains(top_right))) {
            int gap_left_ = list2.indexOf(top_left);
            int gap_right_ = list2.indexOf(top_right);
			if (gap_left_>gap_right_) {
				int temp = gap_left_;
				gap_left_ = gap_right_;
				gap_right_ = temp;
			}
            List<Integer> list_ = list;
            List<Integer> list2_ = list2;
            List<Integer> child11 = list_.subList(0, gap_left);
            List<Integer> child12 = list2_.subList(gap_left_, gap_right_);
            List<Integer> child13 = list_.subList(gap_right, list_.size());
            child1 = child11;
            child1.addAll(child12);
            child1.addAll(child13);
            return child1;
        }
        return null;
//			int gap2_left_ = list2.indexOf(top_left);
//			int gap2_right_ = list2.indexOf(top_right);
//			if (gap2_left_>gap2_right_) {
//				int temp = gap2_left_;
//				gap2_left_ = gap2_right_;
//				gap2_right_ = temp;
//			}
//			list2.indexOf(top_right);
//			List<Integer> child21 = list2.subList(0, gap2_left_);
//			List<Integer> child22 = list.subList(gap_left, gap_right);
			//List<Integer> child23 = list2.subList(gap2_right_, list2.size());
//            child2 = child21;
//            child2.addAll(child22);
            //child2.addAll(child23);
	//	}
		//if (rand.nextBoolean())
		//return child2;
	}
    public boolean selection (List<Integer> person, int n) {
        if (person.size() >= n/3) {
            return true;
        }
        return false;
    }
    public boolean isOptimal (List<Integer> person, int n) {
        if (person.size() == n) {
            return true;
        }
        return false;
    }
}
