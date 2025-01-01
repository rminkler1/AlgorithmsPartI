import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF grid;
    private boolean[] isOpen;
    private int n;
    private int openCount = 0;
    private int length;

    /*
     * Bottom Root Position row n, col 1
     * Top Root Position row n, col 0
     */

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("n must be larger than 0.");
        }

        this.n = n;
        length = n * n;

        grid = new WeightedQuickUnionUF(length + 2);

        // Track which nodes are open
        isOpen = new boolean[length];   // Each id's open or closed status


        // connect top nodes to top root (next to last entry)
        for (int i = 0; i < n; i++) {
            grid.union(length, i);
        }
        // connect bottom nodes to bottom root (last node)
        for (int i = length - 1; i >= length - n; i--) {
            grid.union(length + 1, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        // Throw Exception if a value is passed outside the grid boundaries.
        if (invalidGridLoc(row, col)) {
            throw new IllegalArgumentException("Outside Grid Boundaries.full");
        }

        int indexPosition = getIndexPosition(row, col);

        // Set if not already open, and increment the openCounter
        if (!isOpen(row, col)) {
            isOpen[indexPosition] = true;
            openCount++;
        }

        // checks for 4 neighboring cells
        // if open, perform union

        // Union Cell above
        if (row > 1 && isOpen(row - 1, col)) {
            // if not top row and the cell above is open, union
            grid.union(indexPosition, indexPosition - n);
        }

        // Union Cell below
        if (row < n && isOpen(row + 1, col)) {
            // if not bottom row and the cell below is open
            grid.union(indexPosition, indexPosition + n);
        }

        // Union Cell to the right
        if (col < n && isOpen(row, col + 1)) {
            // if not in the right col and the cell to the right is open
            grid.union(indexPosition, indexPosition + 1);
        }

        // Union Cell to the left
        if (col > 1 && isOpen(row, col - 1)) {
            // if not in the left col and the cell to the left is open
            grid.union(indexPosition, indexPosition - 1);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        // Throw Exception if a value is passed outside the grid boundaries.
        if (invalidGridLoc(row, col)) {
            throw new IllegalArgumentException("Outside Grid Boundaries.full");
        }

        return isOpen[getIndexPosition(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        // Throw Exception if a value is passed outside the grid boundaries.
        if (invalidGridLoc(row, col)) {
            throw new IllegalArgumentException("Outside Grid Boundaries.full");
        }

        // See if the current position is open and connected to the top root node
        return isOpen[getIndexPosition(row, col)]
                && (grid.find(getIndexPosition(row, col)) == grid.find(getIndexPosition(n + 1, 1)));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        // True if bottom root is full - aka bottom root connects to the top root
        return grid.find(length) == grid.find(length + 1);
    }

    // convert row and col into an index position
    private int getIndexPosition(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private boolean invalidGridLoc(int row, int col) {
        return row <= 0 || col <= 0 || row > n || col > n;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation testObject = new Percolation(3);
        testObject.open(1, 1);
        testObject.open(2, 1);
        testObject.open(3, 3);
        testObject.open(3, 1);

        System.out.println("3, 3 open: " + testObject.isOpen(3, 3));
        System.out.println("OpenCount: " + testObject.openCount);
        System.out.println("Percolates: " + testObject.percolates());

    }
}