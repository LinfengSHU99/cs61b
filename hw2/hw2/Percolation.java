package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private class Site {
        private boolean state = false;
        private boolean isfull = false;
    }
    private Site topsite = new Site();
    private Site[][] square;
    private int N;
    private boolean isPercolates = false;
    private int openSites = 0;
    private WeightedQuickUnionUF set;

    public Percolation(int N){
        this.N = N;
        square = new Site[N][N];
        set = new WeightedQuickUnionUF(N * N + 1);
        for(int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                square[i][j] = new Site();
                square[i][j].state = false;
            }
            set.union(i, N * N);
        }

    }
    private int getIndex(int row, int col){
        int index = N * row + col;
        return index;
    }
    private int[] restoreIndex(int index){
        int[] indice = new int[2];
        indice[0] = index / N;
        indice[1] = index - index / N * N;
        return indice;
    }

    private void throwException(int row, int col){
        if (row < 0 || col < 0 || row > N - 1 || col > N - 1){
            throw new IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col){
        throwException(row, col);
        square[row][col].state = true;
        openSites++;
        if (row == 0){
            square[row][col].isfull = true;
        }
        for (int i = Math.max(row - 1, 0); i <= Math.min(row + 1, N - 1); i++){
            if(isOpen(i,col)){
//                if (square[i][col].isfull){
//                    square[row][col].isfull = true;
//                    if(row == N - 1){
//                        isPercolates = true;
//                    }
//                }
                set.union(getIndex(row, col), getIndex(i, col));
            }
        }
        for (int i = Math.max(col - 1, 0); i <= Math.min(col + 1, N - 1); i++){

            if(isOpen(row, i)){
//                if (square[row][i].isfull){
//                    square[row][col].isfull = true;
//                    if(row == N - 1){
//                        isPercolates = true;
//                    }
//                }
                set.union(getIndex(row, col), getIndex(row, i));
            }
        }
    }

    public boolean isOpen(int row, int col){
        throwException(row, col);
        return square[row][col].state;

    }

    public boolean isFull(int row, int col){
        if (isOpen(row, col)){
//            for (int i = 0; i < N; i++){
//                if (set.connected(getIndex(row, col), i)){
//                    return true;
//                }
//            }
            if (set.connected(getIndex(row, col), N * N)){
                if (row == N - 1){
                    isPercolates = true;
                }
                return true;
            }
        }

        return false;
    }

    public int numberOfOpenSites(){
        return openSites;
    }

    public boolean percolates(){
//        for (int i = 0; i < N; i++){
//            if (isFull(N - 1, i)){
//                return true;
//            }
//        }
        return isPercolates;
    }
}
