
public class Percolation {
    // used to quickly form the neighbors of a site
    private static final int[][] DELTA 
        = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
    
    // the number of sites that are next to each site
    private static final int NEIGHBORS = 4;
    
    private final int N; // size of the grid
    private final boolean[][] sites; // N-by-N grid
    private final WeightedQuickUnionUF uf; // Union-Find data structure
    private final int sink; // top site, connected to all sites on row 1
    private final int source; // bottom site, connected to all sites on row N

    /**
     * Creates N-by-N grid, with all sites blocked.
     * @param N size of the grid
     * @throws java.lang.IllegalArgumentException if N <= 0
    */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        sites = new boolean[N][N];
        
        uf = new WeightedQuickUnionUF(N * N + 2);
        sink = N * N;
        source = N * N + 1;
        for (int i = 0; i < N; i++) {
            uf.union(source, getIndex(N - 1, i));
            uf.union(sink, getIndex(0, i));
        }
    }
    
    private void validate(int i, int j) {
        if (i < 1 || i > N) {
            throw new IndexOutOfBoundsException(
                "i index " + i + " is not in range 1 - " + N);
        }
        if (j < 1 || j > N) {
            throw new IndexOutOfBoundsException(
                "j index " + j + " is not in range 1 - " + N);
        }
    }
    
    private boolean isInside(int i, int j) {
        return i >= 1 && i <= N && j >= 1 && j <= N;
    }

    private int getIndex(int i, int j)
    {
        return i * N + j;
    }
    
    /**
     * Opens the site <tt>(i, j)</tt>.
     * @param i row
     * @param j column
     * @throws java.lang.IndexOutOfBoundsException unless 1 <= i, j <= N
     */
    public void open(int i, int j) {
        if (!isOpen(i, j)) {
            sites[i - 1][j - 1] = true;

            int currSiteIndex = getIndex(i - 1, j - 1);
            for (int k = 0; k < NEIGHBORS; k++) {
            
                int nextI = i + DELTA[k][0], nextJ = j + DELTA[k][1];
                if (isInside(nextI, nextJ) && isOpen(nextI, nextJ)) {
                    int nextIdx = getIndex(nextI - 1, nextJ - 1);
                    uf.union(currSiteIndex, nextIdx);
                }
            }
        }
    }
    
    /**
     * Checks whether the site <tt>(i, j)</tt> is open.
     * @param i row
     * @param j column
     * @return <tt>true</tt> if the site <tt>(i, j)</tt> is open, 
     * and <tt>false</tt> otherwise
     * @throws java.lang.IndexOutOfBoundsException unless 1 <= i, j <= N
     */
    public boolean isOpen(int i, int j) {
        validate(i, j);
        return sites[i - 1][j - 1];
    }

    /**
     * Checks whether the site <tt>(i, j)</tt> is full.
     * @param i row
     * @param j column
     * @return <tt>true</tt> if the site <tt>(i, j)</tt> is an open site 
     * that can be connected to an open site in the top row 
     * via a chain of neighboring (left, right, up, down) open sites, 
     * and <tt>false</tt> otherwise
     * @throws java.lang.IndexOutOfBoundsException unless 1 <= i, j <= N
     */
    public boolean isFull(int i, int j) {
        if (isOpen(i, j)) {
            return uf.connected(getIndex(i - 1, j - 1), sink);
        }
        return false;
     }

    /**
     * Checks whether the system percolates.
     * @return <tt>true</tt>, if the system percolates, 
     * and <tt>false</tt> otherwise
     */
    public boolean percolates() {
        return uf.connected(source, sink);
    }
}
