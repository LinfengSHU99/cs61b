package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int source;
    private int target;
    private Maze maze;
    private boolean targetFound = false;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        source = m.xyTo1D(sourceX, sourceY);
        target = m.xyTo1D(targetX, targetY);
        maze = m;
        edgeTo[source] = source;
        distTo[source] = 0;

    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        marked[source] = true;
        Queue<Integer> queue = new ArrayDeque<>(maze.V());
        queue.add(source);
        while (!queue.isEmpty()) {

            int v = queue.remove();
            announce();

            if (v == target) {
                targetFound = true;
                break;
            }
            for (int i : maze.adj(v)) {
                if (!marked[i]) {
                    marked[i] = true;   //入栈的时候将makred设置为true
                    queue.add(i);
                    distTo[i] = distTo[v] + 1;
                    edgeTo[i] = v;
                }

            }
        }

    }


    @Override
    public void solve() {
         bfs();
    }
}

