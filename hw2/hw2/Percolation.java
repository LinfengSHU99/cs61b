package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static class Site {
        private boolean state = false;
        private boolean isfull = false;
    }
    private Site[][] square;
    private int N;
    private boolean isPercolates = false;
    private int openSites = 0;
    private WeightedQuickUnionUF set;
    private WeightedQuickUnionUF set2;

    public Percolation(int N){
        if (N <= 0){
            throw new IllegalArgumentException();
        }
        this.N = N;
        square = new Site[N][N];
        set = new WeightedQuickUnionUF(N * N + 2);
        set2 = new WeightedQuickUnionUF(N * N + 1);
        for(int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                square[i][j] = new Site();
                square[i][j].state = false;
            }
//            set.union(i, N * N);
//            set.union(N * N - 1 - i, N * N + 1);
//            set2.union(i, N * N);
        }
    }
    private int getIndex(int row, int col){
        int index = N * row + col;
        return index;
    }

    private void throwException(int row, int col){
        if (row < 0 || col < 0 || row > N - 1 || col > N - 1){
            throw new IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col){
        throwException(row, col);
        if(!isOpen(row, col)){
            openSites++;
        }
        square[row][col].state = true;
        if (row == 0){
            square[row][col].isfull = true;
        }
        if (row == 0){
            set.union(getIndex(row, col), N * N);
            set2.union(getIndex(row, col), N * N);
        }
        if (row == N - 1){
            set.union(getIndex(row, col), N * N + 1);
        }
        for (int i = Math.max(row - 1, 0); i <= Math.min(row + 1, N - 1); i++){
            if(isOpen(i,col)){
                set.union(getIndex(row, col), getIndex(i, col));

                set2.union(getIndex(row, col), getIndex(i, col));
            }
        }
        for (int i = Math.max(col - 1, 0); i <= Math.min(col + 1, N - 1); i++){
            if(isOpen(row, i)){
                set.union(getIndex(row, col), getIndex(row, i));
                set2.union(getIndex(row, col), getIndex(row, i));
            }
        }
//        if (set.connected(getIndex(row, col), N * N) && row == N - 1){
//            isPercolates = true;
//        }
    }

    public boolean isOpen(int row, int col){
        throwException(row, col);
        return square[row][col].state;

    }

    public boolean isFull(int row, int col){
        //if (isOpen(row, col)){

            return set2.connected(getIndex(row, col), N * N);
        //}
        //return false;
    }

    public int numberOfOpenSites(){
        return openSites;
    }

    public boolean percolates(){
        return set.connected(N * N, N * N + 1);
    }

    public static void main(String[] args){

    }
}