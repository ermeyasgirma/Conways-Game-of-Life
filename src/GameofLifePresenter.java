import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import patterns.*;

public class GameofLifePresenter {

    GameofLifeView view;
    private boolean[][] currentGeneration;
    private int height;
    private int width;

    private TimerTask task;

    public GameofLifePresenter(GameofLifeView view) {
        this.view = view;
        this.height = view.getHeight(); this.width = view.getWidth();
        currentGeneration = new boolean[height][width];
        final Random random = new Random();
        /*for (int i = 0; i < currentGeneration.length; i++) {
            for (int j = 0; j < currentGeneration[0].length; j++) {
                currentGeneration[i][j] = random.nextBoolean();
            }
        }*/
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 2 && (j == 1 || j == 2 || j == 3)) {
                    currentGeneration[i][j] = true;
                } else {
                    currentGeneration[i][j] = false;
                }
            }
        }
    }

    // fill out the rest of the pattern cases later using switch statement
    public GameofLifePresenter(String pattern) {
        if (pattern.equals("Gliding Gun")) {
            patterns.GlidingGun gun = new GlidingGun();
        }
    }


    public void start() {
        task = new TimerTask() {
            @Override
            public void run() {
                view.drawGeneration(currentGeneration);
                calcNextGen();
            }
        };
        new Timer().schedule(task, 0, 25);

    }

    public void calcNextGen() {
        boolean[][] nextGeneration = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                nextGeneration[i][j] = fateOfCell(i, j, currentGeneration[i][j]);
            }
        }
        currentGeneration = nextGeneration;
    }


    public boolean fateOfCell(int i, int j, boolean state) {
        int liveNeighbours = 0;
        int rowBounds = height - 1;
        int colBounds = width - 1;
        for (int x = Math.max(0, i-1); x <= Math.min(i+1, rowBounds); x++) {
            for (int y = Math.max(0, j-1); y <= Math.min(j+1, colBounds); y++) {
                if (x != i | y != j) {
                    if (currentGeneration[x][y]) {
                        liveNeighbours += 1;
                    }
                }
            }
        }
        if (liveNeighbours == 2 && state) {
            return true;
        }
        if (liveNeighbours == 3) {
            return true;
        }
        return false;
    }

    public void stop() {
        if (task == null) {
            return;
        }
        task.cancel();
    }

}