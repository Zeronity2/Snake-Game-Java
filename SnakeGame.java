import processing.core.PApplet;

public class SnakeGame extends PApplet {

    int cellSize = 15;

    int score = 0;
    int highScore = 0;

    GameState gameState = GameState.START;

    Snake snake;
    Food food;
    GameUI ui;


    // =========================
    // WINDOW SETTINGS
    // =========================

    public void settings() {
        size(600, 400);
    }


    // =========================
    // SETUP
    // =========================

    public void setup() {

        snake = new Snake(
            300,
            200,
            cellSize
        );

        food = new Food(
            cellSize,
            width,
            height
        );

        // Generate food away from the snake
        food.generate(
            width,
            height,
            snake
        );

        ui = new GameUI();

        frameRate(5);
        noStroke();
    }


    // =========================
    // DRAW
    // =========================

    public void draw() {

        background(30, 30, 40);


        // =========================
        // START SCREEN
        // =========================

        if (gameState == GameState.START) {

            ui.drawStartScreen(
                this,
                highScore
            );
        }


        // =========================
        // PLAYING
        // =========================

        else if (gameState == GameState.PLAYING) {

            snake.move();

            checkFoodCollision();
            checkWallCollision();
            checkSelfCollision();

            ui.drawScore(
                this,
                score,
                highScore
            );

            ui.drawGameBoard(this);

            snake.draw(this);
            food.draw(this);
        }


        // =========================
        // PAUSED
        // =========================

        else if (gameState == GameState.PAUSED) {

            ui.drawScore(
                this,
                score,
                highScore
            );

            ui.drawGameBoard(this);

            snake.draw(this);
            food.draw(this);

            ui.drawPauseScreen(this);
        }


        // =========================
        // GAME OVER
        // =========================

        else if (gameState == GameState.GAME_OVER) {

            ui.drawGameOverScreen(
                this,
                score,
                highScore
            );
        }
    }


    // =========================
    // FOOD COLLISION
    // =========================

    public void checkFoodCollision() {

        if (snake.getHeadX() < food.getX() + cellSize &&
                snake.getHeadX() + cellSize > food.getX() &&
                snake.getHeadY() < food.getY() + cellSize &&
                snake.getHeadY() + cellSize > food.getY()) {

            score += 10;

            // Update high score
            if (score > highScore) {
                highScore = score;
            }

            // Generate new food away from snake
            food.generate(
                width,
                height,
                snake
            );

            // Grow snake
            snake.grow();
        }
    }


    // =========================
    // WALL COLLISION
    // =========================

    public void checkWallCollision() {

        if (snake.getHeadX() < 0 ||
                snake.getHeadX() + cellSize > width ||
                snake.getHeadY() < 45 ||
                snake.getHeadY() + cellSize > height) {

            gameState = GameState.GAME_OVER;
        }
    }


    // =========================
    // SELF COLLISION
    // =========================

    public void checkSelfCollision() {

        if (snake.getSize() < 3) {
            return;
        }

        for (int i = 1; i < snake.getSize(); i++) {

            if (snake.getHeadX() == snake.getX(i) &&
                    snake.getHeadY() == snake.getY(i)) {

                gameState = GameState.GAME_OVER;

                return;
            }
        }
    }


    // =========================
    // RESTART GAME
    // =========================

    public void restartGame() {

        score = 0;

        snake = new Snake(
            300,
            200,
            cellSize
        );

        // Generate food away from new snake
        food.generate(
            width,
            height,
            snake
        );

        gameState = GameState.PLAYING;
    }


    // =========================
    // RETURN TO MAIN MENU
    // =========================

    public void goToMainMenu() {

        score = 0;

        snake = new Snake(
            300,
            200,
            cellSize
        );

        // Generate food away from new snake
        food.generate(
            width,
            height,
            snake
        );

        gameState = GameState.START;
    }


    // =========================
    // KEYBOARD CONTROLS
    // =========================

    public void keyPressed() {


        // START SCREEN → PLAY
        if (gameState == GameState.START &&
                keyCode == ENTER) {

            gameState = GameState.PLAYING;

            return;
        }


        // GAME OVER → RESTART
        if (gameState == GameState.GAME_OVER &&
                (key == 'r' || key == 'R')) {

            restartGame();

            return;
        }


        // PAUSE / RESUME
        if (key == 'p' || key == 'P') {

            if (gameState == GameState.PLAYING) {

                gameState = GameState.PAUSED;

                return;
            }

            if (gameState == GameState.PAUSED) {

                gameState = GameState.PLAYING;

                return;
            }
        }


        // MOVEMENT
        if (gameState == GameState.PLAYING) {

            if (keyCode == RIGHT) {

                snake.setDirection(
                    cellSize,
                    0
                );
            }

            if (keyCode == LEFT) {

                snake.setDirection(
                    -cellSize,
                    0
                );
            }

            if (keyCode == UP) {

                snake.setDirection(
                    0,
                    -cellSize
                );
            }

            if (keyCode == DOWN) {

                snake.setDirection(
                    0,
                    cellSize
                );
            }
        }
    }


    // =========================
    // MOUSE CONTROLS
    // =========================

    public void mousePressed() {

        // START SCREEN → PLAY
        if (gameState == GameState.START &&
                ui.isPlayButtonClicked(this)) {

            gameState = GameState.PLAYING;

            return;
        }


        // GAME OVER BUTTONS
        if (gameState == GameState.GAME_OVER) {

            // RESTART
            if (ui.isRestartButtonClicked(this)) {

                restartGame();

                return;
            }


            // MAIN MENU
            if (ui.isMenuButtonClicked(this)) {

                goToMainMenu();

                return;
            }
        }
    }


    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        PApplet.main("SnakeGame");
    }
}