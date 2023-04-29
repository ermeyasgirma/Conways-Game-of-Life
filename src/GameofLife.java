import java.util.Random;
class GameofLife {

    private boolean[][] currGrid;
    private boolean[][] nextGrid;
    private long generations;
    private int rows;
    private int cols;

    public GameofLife(int h, int w, int gens) {
        this.currGrid = new boolean[h][w];
        this.nextGrid = new boolean[h][w];
        this.generations = gens;
        this.rows = h; this.cols = w;

        StdDraw.setScale(0, 4);
        //StdDraw.setPenRadius(0.5);
        StdDraw.setPenColor(StdDraw.BLUE);
        //StdDraw.show(0);
    }

    public void calcNextGen() {
        System.out.println("Checking what I am now " + this.currGrid[1][2]);
        StdDraw.show();
        StdDraw.clear();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.println("i and j are " + " " + i + " " + j + " " + this.currGrid[1][2]);
                this.nextGrid[i][j] = this.fateOfCell(i, j, this.currGrid[i][j]);
                if (i == 1 && j ==2) {
                    System.out.println("After new grid assignment I am " + this.currGrid[1][2]);
                }
                if (this.nextGrid[i][j]) {
                    StdDraw.filledSquare(j, i, 0.5);
                }
            }
        }
        if (this.currGrid == this.nextGrid) {
            //System.out.println("Stoppped changed ");
        }
        this.currGrid = this.nextGrid;
        StdDraw.show();
    }

    public boolean fateOfCell(int i, int j, boolean state) {
        if (i == 1 && j == 2) {
            System.out.println("Changing point value is " + this.currGrid[1][2] + " state param is " + state);
        }
        int liveNeighbours = 0;
        int deadNeighbours = 0;
        int rowBounds = this.rows - 1;
        int colBounds = this.cols - 1;
        for (int x = Math.max(0, i-1); x <= Math.min(i+1, rowBounds); x++) {
            for (int y = Math.max(0, j-1); y <= Math.min(j+1, colBounds); y++) {
                if (x != i | y != j) {
                    if (i == 2 && j ==1 && x == 1 && y == 2) {
                        //System.out.println("I am " + this.currGrid[1][2]);
                    }
                    if (this.currGrid[x][y]) {
                        liveNeighbours += 1;
                    }
                    else {
                        deadNeighbours += 1;
                    }
                }
            }
        }
        if (i == 1 && j == 2) {
            System.out.println("At end of for loop I am " + this.currGrid[1][2]);
        }
        if (i == 2 && j ==1) {
            System.out.println("Live neightbours are " + liveNeighbours + " dead neightbours are " + deadNeighbours);
        }

        if ((liveNeighbours == 2 || liveNeighbours == 3) && state) {
            return true;
        }
        if (liveNeighbours == 3 && !state) {
            return true;
        }
        return false;
    }

    public void initialiseGrid() {
        Random random = new Random();
        for (int i = 0; i <= this.rows - 1; i++) {
            for (int j = 0; j <= this.cols - 1; j++) {
                if (i == 2 && (j == 1 || j == 2 || j == 3)) {
                    this.currGrid[i][j] = true;
                } else {
                    this.currGrid[i][j] = false;
                }
                //this.currGrid[i][j] = (random.nextInt() % 2 == 0);
                if (this.currGrid[i][j]) {
                    StdDraw.filledSquare(j, i, 0.5);
                }
            }
        }
        StdDraw.show();
    }

    public void pprint(boolean[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            String currRow = "";
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]) {
                    currRow += " 1 ";
                } else {
                    currRow += " 0 ";
                }
            }
            System.out.println(currRow);
        }
        System.out.println("Grid[1][2] is " + this.currGrid[1][2]);
    }

    public static void main(String[] args) {
        GameofLife g1 = new GameofLife(5, 5, 5000);
        g1.initialiseGrid();
        g1.pprint(g1.currGrid);
        for (int epoch = 1; epoch <= 2; epoch++) {
            //System.out.printf("New grid \n");
            if (epoch == 2) {
                System.out.println(" What am i now " + g1.currGrid[1][2]);
            }
            g1.calcNextGen();
            if (epoch  == 1) {
                g1.pprint(g1.currGrid);

                /* Cell [1][2] is showing up as both false and true. Get to the bottom of this */
            }
        }
        StdDraw.clear();
    }
}