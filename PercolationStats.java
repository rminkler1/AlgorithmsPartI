import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;

    private double[] mean;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be greater than 0.");
        }

        // Track the mean of each trial
        mean = new double[trials];
        this.trials = trials;

        // Run T trials and record the mean of each trial
        for (int i = 0; i < trials; i++) {
            int openSites = percolate(n);
            mean[i] = (double) openSites / (n * n);
        }
    }

    // Create a percolation and open random locations until percolation
    // Returns the number of open locations
    private int percolate(int n) {

        // Create a new Percolation grid
        Percolation percGrid = new Percolation(n);

        // Open Random locations until it percolates
        while (!percGrid.percolates()) {
            int x = StdRandom.uniformInt(1, n + 1);
            int y = StdRandom.uniformInt(1, n + 1);
            percGrid.open(x, y);
        }

        return percGrid.numberOfOpenSites();
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(mean);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(mean);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        Stopwatch stopwatch = new Stopwatch();

        PercolationStats test = new PercolationStats(n, t);
        double time = stopwatch.elapsedTime();

        System.out.println(time);

        n = 100;
        t = 1000;
        Stopwatch stopwatch2 = new Stopwatch();
        PercolationStats test2 = new PercolationStats(n, t);
        time = stopwatch2.elapsedTime();
        System.out.println(time);

        n = 1000;
        t = 100;
        Stopwatch stopwatch3 = new Stopwatch();
        PercolationStats test3 = new PercolationStats(n, t);
        time = stopwatch3.elapsedTime();
        System.out.println(time);

        System.out.println("mean = " + test.mean());
        System.out.println("stddev = " + test.stddev());
        System.out.println(
                "95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi()
                        + "]\n");
    }
}