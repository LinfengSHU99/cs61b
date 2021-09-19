package lab11.graphs;

import java.util.ArrayList;
import java.util.List;
/*
 * 可以判断有环图，但是画循环部分的线还有一些bug
 */
/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private boolean cycleFound = false;
    private int firstCycledNode;
    private List<Integer> arr;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;

        edgeTo[0] = 0;
        arr = new ArrayList<>(maze.V());
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        arr.add(0);
        DFS(0,0);
        while (arr.get(0) != firstCycledNode) {
            arr.remove(0);
        }
        for (int i = 0; i < arr.size() - 1; i++){
            edgeTo[arr.get(i+1)] = arr.get(i);
        }
        edgeTo[arr.get(0)] = arr.get(arr.size() - 1);
        announce();
    }

    // Helper methods go here
    private void DFS(int s, int p) {
        marked[s] = true;
        announce();
        for (int v : maze.adj(s)) {
            if (marked[v] && v != p) {
                cycleFound = true;
                firstCycledNode = v;
            }
            if (!marked[v]) {
                arr.add(v);
                DFS(v, s);
                if (cycleFound) {
                    return;
                }
            }

        }

    }
}

