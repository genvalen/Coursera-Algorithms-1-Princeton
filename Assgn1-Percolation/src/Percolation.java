

public class Percolation {

    public static final int CLOSED = 0;
    public static final int OPEN   = 1;

	private int[][] sites;
    private int N;
    private UF uf;

    /**
     * Creates an N-by-N grid with all sites blocked
     * @param N grid side
     */
    public Percolation(int N) {
        this.N = N+1;
        this.uf = new UF(this.N * this.N);
    	this.sites = new int[this.N][this.N];

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
    	if (i<1 || j<1 || i>this.N || j>this.N) throw new IndexOutOfBoundsException();
        if (this.sites[i][j] == CLOSED) {
    		this.sites[i][j] = OPEN;
    	}
    }

    /**
     * Is site(i,j) open?
     * @param i row
     * @param j col
     * @return  T/F
     */
    public boolean isOpen(int i, int j) {
        if (i<1 || j<1 || i>this.N || j>this.N) throw new IndexOutOfBoundsException();
		return this.sites[i][j] == OPEN;
    }

    /**
     * Is site(i,j) full?
     * @param i row
     * @param j col
     * @return  T/F
     */
    public boolean isFull(int i, int j) {
        if (i<1 || j<1 || i>this.N || j>this.N) throw new IndexOutOfBoundsException();

        //For (i,j) to be full, it should be open and connected to an open site on the first row
        int n = (N-1)*i + j;

        if (this.isOpen(i, j) && (uf.find(n) >= 1 && uf.find(n) <= this.N )) {
            return true;
        }
        return false;
    }

    /**
     * Computes if the system percolates
     * @return  T/F
     */
    public boolean percolates() {
    	UF uf = new UF(this.N * this.N); //sample test for UF

        uf.union(2, 5);

    	return false;
    }


}
