import java.util.ArrayList;
import processing.core.PApplet;

public class Snake {

    private int cellSize;

    private ArrayList<Integer> snakeX = new ArrayList<>();
    private ArrayList<Integer> snakeY = new ArrayList<>();

    private int xSpeed = 0;
    private int ySpeed = 0;


    // =========================
    // CONSTRUCTOR
    // =========================

    public Snake(int startX, int startY, int cellSize) {

        this.cellSize = cellSize;

        snakeX.add(startX);
        snakeY.add(startY);
    }


    // =========================
    // MOVE
    // =========================

    public void move() {

        for (int i = snakeX.size() - 1; i > 0; i--) {

            snakeX.set(
                i,
                snakeX.get(i - 1)
            );

            snakeY.set(
                i,
                snakeY.get(i - 1)
            );
        }

        snakeX.set(
            0,
            snakeX.get(0) + xSpeed
        );

        snakeY.set(
            0,
            snakeY.get(0) + ySpeed
        );
    }


    // =========================
    // DRAW SNAKE
    // =========================

    public void draw(PApplet app) {

        for (int i = snakeX.size() - 1; i >= 0; i--) {

            // Head
            if (i == 0) {
                app.fill(0, 230, 120);

                app.rect(
                    snakeX.get(i) + 1,
                    snakeY.get(i) + 1,
                    cellSize - 2,
                    cellSize - 2,
                    5
                );
            }

            // Body
            else {

                app.fill(0, 180, 90);

                app.rect(
                    snakeX.get(i) + 2,
                    snakeY.get(i) + 2,
                    cellSize - 4,
                    cellSize - 4,
                    4
                );
            }
        }

        drawEyes(app);
    }


    // =========================
    // DRAW EYES
    // =========================

    private void drawEyes(PApplet app) {

        app.fill(255);

        float eyeSize = 3;

        // Moving right
        if (xSpeed > 0) {

            app.ellipse(
                snakeX.get(0) + 11,
                snakeY.get(0) + 4,
                eyeSize,
                eyeSize
            );

            app.ellipse(
                snakeX.get(0) + 11,
                snakeY.get(0) + 11,
                eyeSize,
                eyeSize
            );
        }

        // Moving left
        else if (xSpeed < 0) {

            app.ellipse(
                snakeX.get(0) + 4,
                snakeY.get(0) + 4,
                eyeSize,
                eyeSize
            );

            app.ellipse(
                snakeX.get(0) + 4,
                snakeY.get(0) + 11,
                eyeSize,
                eyeSize
            );
        }

        // Moving up
        else if (ySpeed < 0) {

            app.ellipse(
                snakeX.get(0) + 4,
                snakeY.get(0) + 4,
                eyeSize,
                eyeSize
            );

            app.ellipse(
                snakeX.get(0) + 11,
                snakeY.get(0) + 4,
                eyeSize,
                eyeSize
            );
        }

        // Moving down
        else if (ySpeed > 0) {

            app.ellipse(
                snakeX.get(0) + 4,
                snakeY.get(0) + 11,
                eyeSize,
                eyeSize
            );

            app.ellipse(
                snakeX.get(0) + 11,
                snakeY.get(0) + 11,
                eyeSize,
                eyeSize
            );
        }
    }


    // =========================
    // GROW
    // =========================

    public void grow() {

        snakeX.add(
            snakeX.get(snakeX.size() - 1)
        );

        snakeY.add(
            snakeY.get(snakeY.size() - 1)
        );
    }


    // =========================
    // SET DIRECTION
    // =========================

    public void setDirection(int newXSpeed, int newYSpeed) {

    // Prevent reversing directly into the snake's body
    if (xSpeed != 0 && newXSpeed == -xSpeed) {
        return;
    }

    if (ySpeed != 0 && newYSpeed == -ySpeed) {
        return;
    }

    xSpeed = newXSpeed;
    ySpeed = newYSpeed;
}


    // =========================
    // GETTERS
    // =========================

    public int getHeadX() {
        return snakeX.get(0);
    }

    public int getHeadY() {
        return snakeY.get(0);
    }

    public int getSize() {
        return snakeX.size();
    }

    public int getX(int index) {
        return snakeX.get(index);
    }

    public int getY(int index) {
        return snakeY.get(index);
    }
}