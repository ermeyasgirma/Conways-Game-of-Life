import java.util.Random;

public class GOL2 {

    private boolean[][] currGrid;
    private int rows;
    private int cols;

    public GOL2(int h, int w, int gens) {
        this.currGrid = new boolean[h][w];
        this.rows = h; this.cols = w;
        StdDraw.setScale(0, h);
        StdDraw.setPenColor(StdDraw.BLUE);
    }

    public void drawGridLines() {
        for (int l = 1; l < this.rows; l++) {
            StdDraw.line(l, 0, l, this.rows );
        }
        for (int l = 1; l < this.rows; l++) {
            StdDraw.line(0, l, this.rows, l);
        }
    }

    public void initialiseBlinkerGrid() {
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
                    StdDraw.filledSquare(j + 0.5 , i + 0.5 , 0.5);
                }
            }
        }
        this.drawGridLines();
        StdDraw.show(0);
    }

    public void initialiseToadGrid() {
        for (int i = 0; i <= this.rows - 1; i++) {
            for (int j = 0; j <= this.cols - 1; j++) {
                if (i == 2 && (j == 2 || j == 3 || j == 4)) {
                    this.currGrid[i][j] = true;
                } else if (i == 3 && (j == 1 || j == 2 || j == 3)) {
                    this.currGrid[i][j] = true;
                } else {
                    this.currGrid[i][j] = false;
                }
                //this.currGrid[i][j] = (random.nextInt() % 2 == 0);
                if (this.currGrid[i][j]) {
                    StdDraw.filledSquare(j + 0.5 , i + 0.5 , 0.5);
                }
            }
        }
        this.drawGridLines();
        StdDraw.show(0);
    }


    public void calcNextGen() {
        StdDraw.show(0);
        StdDraw.clear();
        boolean[][] nextGrid = new boolean[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                nextGrid[i][j] = this.fateOfCell(i, j, this.currGrid[i][j]);
                if (nextGrid[i][j]) {
                    StdDraw.filledSquare(j + 0.5 , i + 0.5 , 0.5);
                }
            }
        }

        this.currGrid = nextGrid;
        this.drawGridLines();
        StdDraw.show(0);
    }


    public boolean fateOfCell(int i, int j, boolean state) {
        if (i == 1 && j == 2) {
            //System.out.println("Changing point value is " + this.currGrid[1][2] + " state param is " + state);
        }
        int liveNeighbours = 0;
        int rowBounds = this.rows - 1;
        int colBounds = this.cols - 1;
        for (int x = Math.max(0, i-1); x <= Math.min(i+1, rowBounds); x++) {
            for (int y = Math.max(0, j-1); y <= Math.min(j+1, colBounds); y++) {
                if (x != i | y != j) {
                    if (this.currGrid[x][y]) {
                        liveNeighbours += 1;
                    }
                }
            }
        }
        if (i == 1 && j == 2) {
            //System.out.println("At end of for loop I am " + this.currGrid[1][2]);
        }
        if (i == 2 && j ==1) {
            //System.out.println("Live neightbours are " + liveNeighbours + " dead neightbours are " + deadNeighbours);
        }

        if (liveNeighbours == 2 && state) {
            return true;
        }
        if (liveNeighbours == 3) {
            return true;
        }
        return false;
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
    }


    public static void main(String[] args) {
        GOL2 g1 = new GOL2(6, 6, 300);
        g1.initialiseToadGrid();
        //g1.pprint(g1.currGrid);
        //System.out.println("\n");
        //g1.calcNextGen();
        for (int epoch = 1; epoch <= 300; epoch++) {
            g1.calcNextGen();
        }
        //System.out.println("Finished for loop");
        StdDraw.clear();
    }

}
