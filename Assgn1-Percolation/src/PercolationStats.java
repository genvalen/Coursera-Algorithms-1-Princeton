/**
 * @author Mandeep Condle
 *
 * Coursera: Algorithms, Part 1
 * Programming Assignment 1: Percolation
 *
 * PercolationStats.java
 *
 */


public class PercolationStats {
    private int T;
    private double[] fractionOpened;

    /**
     * Perform T independent computational experiments on an N-by-N grid
     * @param N grid side
     * @param T # of computations
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();

        this.T = T;
        this.fractionOpened = new double[T];

        //run T experiments
        for (int run=0; run<T; run++) {

            //initialized for each run
            Percolation perc = new Percolation(N);
            int openLocal = 0;
            double fracLocal;

            while (!perc.percolates()) {

                //generate uniform random numbers
                int i = StdRandom.uniform(1, N+1);
                int j = StdRandom.uniform(1, N+1);

                if (!perc.isOpen(i, j)) {
                    perc.open(i, j);
                    openLocal++;    //CANT ADD THIS UNLESS IT ACTUALLY OPENS
                }
            }

            fracLocal = (double) openLocal / (N*N);
            this.fractionOpened[run] = fracLocal;
        }
    }

    /**
     * Computes sample mean of percolation threshold
     * @return  mean
     */
    public double mean() {
        double sum = 0.0;

        for (int i=0; i<this.T; i++) {
            sum += this.fractionOpened[i];
        }
        return sum / ((double) this.T);
    }

    /**
     * Computes standard deviation of perocolation threshold
     * @return  SD
     */
    public double stddev() {
        double mu = this.mean();
        double sum = 0.0;

        for (int i=0; i<this.T; i++) {
            sum += (this.fractionOpened[i] - mu) * (this.fractionOpened[i] - mu);
        }
        return sum / ((double) T - 1);
    }

    /**
     * Returns lower bound of the 95% confidence interval
     * @return lower bound
     */
    public double confidenceLo() {
        double cLow;
        double mu = this.mean();
        double sd = this.stddev();

        cLow = mu - ((1.96*sd) / (Math.sqrt(T)));
        return cLow;
    }

    /**
     * Returns upper bound of the 95% confidence interval
     * @return upper bound
     */
    public double confidenceHi() {
        double cHigh;
        double mu = this.mean();
        double sd = this.stddev();

        cHigh = mu + ((1.96*sd) / (Math.sqrt(T)));
        return cHigh;
    }

    /**
     * Test client
     * @param args
     */
    public static void main(String[] args) {

        //PercolationStats stats = new PercolationStats(800, 30);

    }

}
