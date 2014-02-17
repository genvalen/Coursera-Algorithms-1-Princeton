import java.io.BufferedInputStream;
import java.util.Scanner;

public class PercolationStats {

    /**
     * Perform T independent computational experiments on an N-by-N grid
     * @param N grid side
     * @param T # of computations
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();


    }

    /**
     * Computes sample mean of percolation threshold
     * @return  mean
     */
    public double mean() {

        return 0.0;
    }

    /**
     * Computes standard deviation of perocolation threshold
     * @return  SD
     */
    public double stddev() {
        return 0.0;
    }

    /**
     * Returns lower bound of the 95% confidence interval
     * @return lower bound
     */
    public double confidenceLo() {
        return 0.0;
    }

    /**
     * Returns upper bound of the 95% confidence interval
     * @return upper bound
     */
    public double confidenceHi() {
        return 0.0;
    }

    /**
     * Test client
     * @param args
     */
    public static void main(String[] args) {

        System.out.println();


    }

}
