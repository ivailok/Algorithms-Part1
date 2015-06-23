
public class PercolationStats {
	private double _mean, _stddev, _confLo, _confHi;
	
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
			
			percThresholds[t] = (double)openSites / (N * N);
		}
		
		_mean = StdStats.mean(percThresholds);
		_stddev = StdStats.stddev(percThresholds);
		
		double delta = (1.96 * _stddev) / Math.sqrt(T);
		_confLo = _mean + delta;
		_confHi = _mean + delta;
	}
	
	public double mean() {
		return _mean;
	}
	
	public double stddev() {
		return _stddev;
	}
	
	public double confidenceLo() {
		return _confLo;
	}
	
	public double confidenceHi() {
		return _confHi;
	}
	
	public static void main(String[] args) {
		PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		
		StdOut.println("mean = " + percStats.mean());
		StdOut.println("stddev = " + percStats.stddev());
		StdOut.println("95% confidence interval = " + percStats.confidenceLo() + ", " + percStats.confidenceHi());
	}
}
