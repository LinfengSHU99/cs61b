package hw2;
import  edu.princeton.cs.introcs.StdRandom;
import  edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private double[] fractions;
    private int T;

    public PercolationStats(int N, int T, PercolationFactory pf){
        int row;
        int col;
        this.T = T;
        int cnt = 0;
        fractions = new double[T];
        if(N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        Percolation p = pf.make(N);
        StdRandom.setSeed(73816);
        while(cnt < T){

            row = StdRandom.uniform(N);
            col = StdRandom.uniform(N);
            p.open(row, col);
            if (p.percolates()){
                fractions[cnt] =(double) p.numberOfOpenSites() / N / N;
                cnt++;
                if (cnt < T){
                    p = pf.make(N);
                }

            }
        }


    }

    public double mean(){
        double sum = 0;
        for (int i = 0; i < T; i++){
            sum += fractions[i];
        }
        return sum / T;
    }

    public double stddev(){
        double mean = mean();
        double sum = 0;
        for (int i = 0; i < T; i++){
            sum += (fractions[i] - mean) * (fractions[i] - mean);
        }
        return Math.sqrt(sum / (T - 1));

    }

    public double confidenceLow(){
        double mean = mean();
        double dev = stddev();
        return mean - 1.96 * dev / Math.sqrt(T);
    }

    public double confidenceHigh(){
        double mean = mean();
        double dev = stddev();
        return mean + 1.96 * dev / Math.sqrt(T);
    }
}
