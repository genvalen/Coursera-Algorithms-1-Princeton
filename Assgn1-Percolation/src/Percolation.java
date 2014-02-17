

public class Percolation {
    public static final int CLOSED = 1;
    public static final int OPEN   = 1;
    public static final int FULL   = 1;

	private int[][] sites;

    public Percolation(int N) {
    	this.sites = new int[N][N];
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<N; j++) {
    			this.sites[i][j] = CLOSED;
    		}
    	}
    }

    public void open(int i, int j) {
    	if (this.sites[i][j] != OPEN) {
    		this.sites[i][j] = OPEN;
    	}
    }

    public boolean isOpen(int i, int j) {
		return this.sites[i][j] == OPEN;
    }

    public boolean isFull(int i, int j) {
        return this.sites[i][j] == FULL;
    }

    public boolean percolates() {
    	UF uf = new UF(10); //sample test for UF
        uf.union(2, 5);

    	return false;
    }


}
