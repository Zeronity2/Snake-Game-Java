import processing.core.PApplet;

public class Food {

    private int x;
    private int y;
    private int cellSize;


    // =========================
    // CONSTRUCTOR
    // =========================

    public Food(int cellSize, int width, int height) {

        this.cellSize = cellSize;

        // Temporary initial position
        x = cellSize;
        y = 60;
    }


    // =========================
    // GENERATE FOOD
    // =========================

    public void generate(
            int width,
            int height,
            Snake snake) {

        int topOffset = 45;

        // Keep food one cell inside the game border
        int minX = cellSize;
        int maxX = width - cellSize * 2;

        int minY = topOffset + cellSize;
        int maxY = height - cellSize * 2;

        int columns =
            (maxX - minX) / cellSize + 1;

        int rows =
            (maxY - minY) / cellSize + 1;

        do {

            x = minX +
                (int) (Math.random() * columns)
                * cellSize;

            y = minY +
                (int) (Math.random() * rows)
                * cellSize;

        } while (isOnSnake(snake));
    }


    // =========================
    // CHECK SNAKE COLLISION
    // =========================

    private boolean isOnSnake(Snake snake) {

        for (int i = 0; i < snake.getSize(); i++) {

            if (x == snake.getX(i) &&
                    y == snake.getY(i)) {

                return true;
            }
        }

        return false;
    }


    // =========================
    // DRAW FOOD
    // =========================

    public void draw(PApplet app) {

        // Shadow
        app.fill(120, 20, 20);

        app.ellipse(
            x + 1,
            y + 2,
            cellSize,
            cellSize
        );


        // Main food
        app.fill(255, 70, 70);

        app.ellipse(
            x,
            y,
            cellSize - 1,
            cellSize - 1
        );


        // Highlight
        app.fill(255, 180, 180);

        app.ellipse(
            x - 3,
            y - 3,
            4,
            4
        );
    }


    // =========================
    // GETTERS
    // =========================

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}