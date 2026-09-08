public class GameConfig {

    // =========================
    // GAME WINDOW
    // =========================

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;


    // =========================
    // GAME GRID
    // =========================

    public static final int CELL_SIZE = 15;


    // =========================
    // UI
    // =========================

    public static final int HUD_HEIGHT = 45;


    // =========================
    // GAME SPEED
    // =========================

    public static final int INITIAL_SPEED = 5;
    public static final int MAX_SPEED = 10;


    // =========================
    // SCORING
    // =========================

    public static final int SCORE_PER_FOOD = 10;
    public static final int POINTS_PER_LEVEL = 50;


    // Prevent object creation
    private GameConfig() {
    }
}