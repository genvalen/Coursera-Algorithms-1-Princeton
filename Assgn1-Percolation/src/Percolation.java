/**
 * @author Mandeep Condle
 *
 * Coursera: Algorithms, Part 1
 * Programming Assignment 1: Percolation
 *
 * Percolation.java
 *
 */

public class Percolation {

    private static final int CLOSED = 0;
    private static final int OPEN   = 1;

	private int[][] sites;
    private int N;
    private WeightedQuickUnionUF wqUF;
    private int top;    //virtual top site
    private int bot;    //virtual bottom site


    /**
     * Creates an N-by-N grid with all sites blocked
     * @param N grid side
     */
    public Percolation(int N) {
        this.N = N+2;
        this.wqUF = new WeightedQuickUnionUF((this.N) * (this.N));
    	this.sites = new int[this.N][this.N];

        this.top = this.N / 2;
        this.bot = (this.N * this.N) - (this.N / 2);

        //initialize all sites are closed
    	for (int i=1; i<this.N; i++) {
    		for (int j=0; j<this.N; j++) {
    			this.sites[i][j] = CLOSED;
    		}
    	}
    }

    /**
     * Opens site(i,j) if not opened already
     * @param i row
     * @param j col
     */
    public void open(int i, int j) {
    	this.validateInput(i, j);
        int n = this.xyTo1D(i, j);

        if (this.isInTopRow(i, j)) {
            wqUF.union(top, n);
        }

        if (this.isInBottomRow(i, j)) {
            wqUF.union(bot, n);
        }

        if (!this.isOpen(i, j)) {
            this.sites[i][j] = OPEN;

            //If neighbors are open then union them
            if (this.isOpen(i, j+1)) {
                wqUF.union(n, n+1);
            }
            if (this.isOpen(i, j-1)) {
                wqUF.union(n, n-1);
            }
            if (this.isOpen(i+1, j)) {
                wqUF.union(n, n+this.N);
            }
            if (this.isOpen(i-1, j)) {
                wqUF.union(n, n-this.N);
            }

        }
    }

    /**
     * Is site(i,j) open?
     * @param i row
     * @param j col
     * @return  T/F
     */
    public boolean isOpen(int i, int j) {
        this.validateInput(i, j);
		return this.sites[i][j] == OPEN;
    }

    /**
     * Is site(i,j) full?
     * @param i row
     * @param j col
     * @return  T/F
     */
    public boolean isFull(int i, int j) {
        this.validateInput(i, j);

        int n = this.xyTo1D(i, j);
        if (wqUF.connected(top, n)) {   return true;    }
        else                        {   return false;   }
    }

    /**
     * Computes if the system percolates
     * @return  T/F
     */
    public boolean percolates() {
        if (wqUF.connected(bot, top)) { return true;    }
        else {  return false;   }
    }

    /**
     * Checks if the 2D input is valid or not
     * @param i row
     * @param j col
     * @return  true if input is valid, false if invalid
     */
    private boolean validateInput(int i, int j) {
        if (i<0 || i>this.N) {
            System.out.println("i: " + i);
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        if (j<0 || j>this.N) {
            System.out.println("j: " + j);
            throw new IndexOutOfBoundsException("col index j out of bounds");
        }
        return true;
    }

    /**
     * Converts the 2D coordinates to 1D
     * @param i row
     * @param j col
     * @return  1D coordinate for the wqUK data structure
     */
    private int xyTo1D(int i, int j) {
        return (i * this.N) + j;
    }

    private boolean isInTopRow(int i, int j) {
        if (i == 1) { return true;  }
        return false;
    }

    private boolean isInBottomRow(int i, int j) {
        if (i == this.N-2) {    return true;    }
        return false;
    }

}
