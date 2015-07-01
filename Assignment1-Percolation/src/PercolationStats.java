
public class PercolationStats {
    private double mean; // storing the computed mean
    private double stddev; // storing the computed standard deviation
    private double confLo; // storing the low  endpoint of 95% confidence interval
    private double confHi; // storing the high endpoint of 95% confidence interval

    /**
     * Performs T independent experiments on an N-by-N grid
     * @param N size of the grid
     * @param T number of expirements
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        
        double[] percThresholds = new double[T]; 
        for (int t = 0; t < T; t++) {

            int openSites = 0;
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {

                // choosing a random blocked site 
                int i, j;
                do {
                    i = StdRandom.uniform(1, N + 1);
                    j = StdRandom.uniform(1, N + 1);
                } while (perc.isOpen(i, j));

                perc.open(i, j);
                openSites++;
            }

            // the fraction of open sites
            percThresholds[t] = (double) openSites / (N * N);
        }

        // computing the results
        mean = StdStats.mean(percThresholds);
        stddev = StdStats.stddev(percThresholds);
        double delta = (1.96 * stddev) / Math.sqrt(T);
        confLo = mean - delta;
        confHi = mean + delta;
    }
    
    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return mean;
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return stddev;
    }

    /**
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return confLo;
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return confHi;
    }

    /**
     * Performs T independent computational experiments 
     * on an N-by-N grid and prints out the results.
     * @param args
     */
    public static void main(String[] args) {
        PercolationStats percStats 
            = new PercolationStats(Integer.parseInt(args[0]), 
                                   Integer.parseInt(args[1]));

        StdOut.println("mean\t\t\t= " + percStats.mean());
        StdOut.println("stddev\t\t\t= " + percStats.stddev());
        StdOut.println("95% confidence interval\t= " 
            + percStats.confidenceLo() + ", " + percStats.confidenceHi());
    }
}
