
public class GameofLifeView {

    private int height;
    private int width;
    public GameofLifeView(int height, int width) {
        this.height = height;
        this.width = width;
        StdDraw.setScale(0, height);
        StdDraw.setPenColor(StdDraw.BLUE);
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return width;
    }

    public void drawGridLines() {
        for (int l = 1; l < this.height; l++) {
            StdDraw.line(l, 0, l, this.height );
        }
        for (int l = 1; l < this.width; l++) {
            StdDraw.line(0, l, this.width, l);
        }
    }

    public void drawGeneration(boolean[][] generation) {
        StdDraw.show(0);
        StdDraw.clear();
        for (int i = 0; i < generation.length; i++) {
            for (int j = 0; j < generation[0].length; j++) {
                if (generation[i][j]) {
                    StdDraw.filledSquare(j + 0.5, i + 0.5, 0.5);
                }
            }

        }
        drawGridLines();
        StdDraw.show(0);
    }

    // for debugging purposes
    public void prettyPrint(boolean[][] generation) {
        for (int i = 0; i < generation.length; i++) {
            StringBuilder currRow = new StringBuilder();
            for (int j = 0; j < generation[0].length; j++) {
                if (generation[i][j]) {
                    currRow.append(" 1 ");
                } else {
                    currRow.append(" 0 ");
                }
            }
            System.out.println(currRow);
        }
    }

}