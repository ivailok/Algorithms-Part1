
public class PercolationStats {
    private double mean, stddev, confLo, confHi;

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

            percThresholds[t] = (double) openSites / (N * N);
        }

        mean = StdStats.mean(percThresholds);
        stddev = StdStats.stddev(percThresholds);
        
        double delta = (1.96 * stddev) / Math.sqrt(T);
        confLo = mean + delta;
        confHi = mean + delta;
    }
    
    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confLo;
    }

    public double confidenceHi() {
        return confHi;
    }

    public static void main(String[] args) {
        PercolationStats percStats 
            = new PercolationStats(Integer.parseInt(args[0]), 
                                   Integer.parseInt(args[1]));

        StdOut.println("mean = " + percStats.mean());
        StdOut.println("stddev = " + percStats.stddev());
        StdOut.println("95% confidence interval = " 
            + percStats.confidenceLo() + ", " + percStats.confidenceHi());
    }
}
