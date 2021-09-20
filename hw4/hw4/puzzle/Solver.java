package hw4.puzzle;


import edu.princeton.cs.algs4.MinPQ;

import java.util.HashSet;
import java.util.LinkedList;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private int move = 0;
        private SearchNode prior = null;
        private WorldState word;
        private int dist;
        private int layer;

        private SearchNode(WorldState w, SearchNode p) {
            this.word = w;
            if (p != null) {
                layer = p.layer + 1;
            }
            prior = p;

            this.move = layer;
            dist = word.estimatedDistanceToGoal() + move;

        }

        @Override
        public int compareTo(SearchNode n1) {
            int x = this.dist - n1.dist;
            return Integer.compare(x, 0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            SearchNode n = (SearchNode) o;
            return n.word.equals(this.word);
        }

        @Override
        public int hashCode() {
            return word.hashCode();
        }
    }

    private LinkedList<WorldState> lst = new LinkedList<>();
    private MinPQ<SearchNode> pq = new MinPQ<>();
    private SearchNode finalNode;
    private HashSet<SearchNode> set = new HashSet<>();

    public Solver(WorldState initial) {
        SearchNode t = new SearchNode(initial, null);
        set.add(t);
        pq.insert(t);
        finalNode = t;

        while (!initial.isGoal()) {
            for (WorldState w : initial.neighbors()) {
                t = new SearchNode(w, finalNode);
                if (!set.contains(t)) {
                    pq.insert(t);
                    //System.out.println(t.word);
                    set.add(t);
                }
            }
            finalNode = pq.delMin();
            initial = finalNode.word;
        }

        SearchNode p = finalNode;
        while (p != null) {
            lst.addFirst(p.word);
            p = p.prior;
        }
    }

    public int moves() {
        return finalNode.layer;
    }

    public Iterable<WorldState> solution() {
        return lst;
    }
}
