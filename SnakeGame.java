import processing.core.PApplet;

public class SnakeGame extends PApplet {

    int cellSize = GameConfig.CELL_SIZE;

    int score = 0;
    int highScore = 0;

    int level = 1;
    int gameSpeed = GameConfig.INITIAL_SPEED;

    boolean showLevelUp = false;
    int levelUpTimer = 0;

    GameState gameState = GameState.START;

    Snake snake;
    Food food;
    GameUI ui;

    public void settings() {
        size(
            GameConfig.WIDTH,
            GameConfig.HEIGHT
        );
    }

    public void setup() {

        snake = new Snake(
            GameConfig.WIDTH / 2,
            GameConfig.HEIGHT / 2
        );

        food = new Food(
            width,
            height
        );

        food.generate(
            width,
            height,
            snake
        );

        ui = new GameUI();

        frameRate(gameSpeed);
        noStroke();
    }

    public void draw() {

        background(30, 30, 40);

        if (gameState == GameState.START) {

            ui.drawStartScreen(
                this,
                highScore
            );

        } else if (gameState == GameState.PLAYING) {

            updateLevel();

            snake.move();

            checkFoodCollision();
            checkWallCollision();
            checkSelfCollision();

            // HUD
            ui.drawScore(
                this,
                score,
                highScore,
                level
            );

            // Game board
            ui.drawGameBoard(this);

            // Level-up message
            if (showLevelUp) {

                ui.drawLevelUp(
                    this,
                    level
                );

                if (millis() - levelUpTimer > 1500) {
                    showLevelUp = false;
                }
            }

            // Snake and food
            snake.draw(this);
            food.draw(this);

            // IMPORTANT:
            // Draw touch buttons LAST
            ui.drawTouchControls(this);

        } else if (gameState == GameState.PAUSED) {

            ui.drawScore(
                this,
                score,
                highScore,
                level
            );

            ui.drawGameBoard(this);

            snake.draw(this);
            food.draw(this);

            // Keep controls visible while paused
            ui.drawTouchControls(this);

            ui.drawPauseScreen(this);

        } else if (gameState == GameState.GAME_OVER) {

            ui.drawGameOverScreen(
                this,
                score,
                highScore
            );
        }
    }

    // =========================
    // LEVEL SYSTEM
    // =========================

    public void updateLevel() {

        int newLevel =
            (score / GameConfig.POINTS_PER_LEVEL) + 1;

        if (newLevel > level) {

            level = newLevel;

            showLevelUp = true;
            levelUpTimer = millis();
        }

        gameSpeed =
            GameConfig.INITIAL_SPEED + (level - 1);

        if (gameSpeed > GameConfig.MAX_SPEED) {
            gameSpeed = GameConfig.MAX_SPEED;
        }

        frameRate(gameSpeed);
    }

    // =========================
    // FOOD COLLISION
    // =========================

    public void checkFoodCollision() {

        if (snake.getHeadX() < food.getX() + cellSize &&
                snake.getHeadX() + cellSize > food.getX() &&
                snake.getHeadY() < food.getY() + cellSize &&
                snake.getHeadY() + cellSize > food.getY()) {

            score += GameConfig.SCORE_PER_FOOD;

            if (score > highScore) {
                highScore = score;
            }

            food.generate(
                width,
                height,
                snake
            );

            snake.grow();
        }
    }

    // =========================
    // WALL COLLISION
    // =========================

    public void checkWallCollision() {

        int playableBottom =
            height - GameConfig.CONTROL_HEIGHT;

        if (snake.getHeadX() < 0 ||
                snake.getHeadX() + cellSize > width ||
                snake.getHeadY() < GameConfig.HUD_HEIGHT ||
                snake.getHeadY() + cellSize > playableBottom) {

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
    // RESTART
    // =========================

    public void restartGame() {

        score = 0;
        level = 1;
        gameSpeed = GameConfig.INITIAL_SPEED;

        showLevelUp = false;

        snake = new Snake(
            GameConfig.WIDTH / 2,
            GameConfig.HEIGHT / 2
        );

        food.generate(
            width,
            height,
            snake
        );

        frameRate(gameSpeed);

        gameState = GameState.PLAYING;
    }

    // =========================
    // MAIN MENU
    // =========================

    public void goToMainMenu() {

        score = 0;
        level = 1;
        gameSpeed = GameConfig.INITIAL_SPEED;

        showLevelUp = false;

        snake = new Snake(
            GameConfig.WIDTH / 2,
            GameConfig.HEIGHT / 2
        );

        food.generate(
            width,
            height,
            snake
        );

        frameRate(gameSpeed);

        gameState = GameState.START;
    }

    // =========================
    // KEYBOARD CONTROLS
    // =========================

    public void keyPressed() {

        if (gameState == GameState.START &&
                keyCode == ENTER) {

            gameState = GameState.PLAYING;
            return;
        }

        if (gameState == GameState.GAME_OVER &&
                (key == 'r' || key == 'R')) {

            restartGame();
            return;
        }

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
    // MOUSE / TOUCH CONTROLS
    // =========================

    public void mousePressed() {

        // PLAY button
        if (gameState == GameState.START &&
                ui.isPlayButtonClicked(this)) {

            gameState = GameState.PLAYING;
            return;
        }

        // GAME OVER buttons
        if (gameState == GameState.GAME_OVER) {

            if (ui.isRestartButtonClicked(this)) {
                restartGame();
                return;
            }

            if (ui.isMenuButtonClicked(this)) {
                goToMainMenu();
                return;
            }
        }

        // ON-SCREEN ARROW BUTTONS
        if (gameState == GameState.PLAYING) {

            int button =
                ui.getControlButton(
                    this,
                    mouseX,
                    mouseY
                );

            if (button == 1) {

                // LEFT
                snake.setDirection(
                    -cellSize,
                    0
                );

            } else if (button == 2) {

                // UP
                snake.setDirection(
                    0,
                    -cellSize
                );

            } else if (button == 3) {

                // DOWN
                snake.setDirection(
                    0,
                    cellSize
                );

            } else if (button == 4) {

                // RIGHT
                snake.setDirection(
                    cellSize,
                    0
                );
            }
        }
    }

    public static void main(String[] args) {

        PApplet.main("SnakeGame");
    }
}