import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameofLife {
    public GameofLife(final int height, final int width) {
        if (height < 1|| width < 1) {
            throw new IllegalArgumentException("Height and width of the grid must be positive");
        }
        final GameofLifeView golView = new GameofLifeView(height, width);
        final GameofLifePresenter golPresenter = new GameofLifePresenter(golView);
        golPresenter.start();
    }

    public GameofLife(String pattern) {
        final GameofLifePresenter golPresenter = new GameofLifePresenter(pattern);
    }

    public static void main(String[] args) {
        new GameofLife(5, 5);
    }
}