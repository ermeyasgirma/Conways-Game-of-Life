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

        StdDraw.setCanvasSize(w, h);
        StdDraw.setYscale(0, h);
        StdDraw.setXscale(0, w);
        StdDraw.setPenRadius(0);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.show(0);
    }

    public void calcNextGen() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.nextGrid[i][j] = this.fateOfCell(i, j, this.currGrid[i][j]);
            }
        }
        this.currGrid = this.nextGrid;
    }

    public boolean fateOfCell(int i, int j, boolean state) {
        int liveNeighbours = 0;
        int deadNeighbours = 0;
        int rowBounds = this.rows - 1;
        int colBounds = this.cols - 1;
        for (int x = Math.max(0, i-1); x <= Math.min(i+1, rowBounds); x++) {
            for (int y = Math.max(0, j-1); y <= Math.min(j+1, colBounds); y++) {
                if (x != i && y != j) {
                    var out = this.currGrid[x][y] ? liveNeighbours++ : deadNeighbours++;
                }
            }
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
                this.currGrid[i][j] = (random.nextInt() % 2 == 0);
            }
        }
    }

    public static void main(String[] args) {
        GameofLife g1 = new GameofLife(10, 10, 5000);
        g1.initialiseGrid();
        for (int epoch = 1; epoch <= 5000; epoch++) {
            g1.calcNextGen();
        }
    }
}