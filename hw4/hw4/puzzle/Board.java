package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;
public class Board implements WorldState{

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    private int[][] arr;
    private final int  BLANK = 0;
    //private int[][] goal;
    public Board(int[][] tiles) {
        arr = new int[tiles.length][tiles.length];
        for (int i = 0; i < size(); i++) {
            if (size() >= 0) System.arraycopy(tiles[i], 0, arr[i], 0, size());
        }

    }

    public int tileAt(int i, int j) {
        if (i <= size() - 1 && i >= 0 && j <= size() - 1 && j >= 0) {
            return arr[i][j];
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int size() {
        return arr.length;
    }

    public int hamming() {
        int cnt = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (i == j && i == size() - 1) {
                    if (tileAt(i, j) != 0)  cnt++;
                }
                else{
                    if (tileAt(i, j) != i * size() + j + 1){
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public int manhattan() {
        int cnt = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                //int correctValue = i * size() + j + 1;
                int value = tileAt(i, j);
                int indexI = (value - 1) / size();
                int indexJ = value - indexI * size() - 1;
                if (value == 0) {
                    indexI = size() - 1;
                    indexJ = size() - 1;
                }
                cnt += (Math.abs(i - indexI) + Math.abs( j - indexJ));
            }
        }
        return cnt;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (this.getClass() != y.getClass()) {
            return false;
        }
        Board b = (Board) y;
        if (size() != b.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != b.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int r = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                r += tileAt(i, j) * i * j;
            }
        }
        return r;
    }

    /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
