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

        generate(width, height);
    }


    // =========================
    // GENERATE FOOD
    // =========================

    public void generate(int width, int height) {

        int topOffset = 45;

        x = (int) (Math.random() * (width / cellSize))
                * cellSize;

        int availableHeight = height - topOffset;

        y = topOffset +
            (int) (Math.random() *
            (availableHeight / cellSize))
            * cellSize;
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