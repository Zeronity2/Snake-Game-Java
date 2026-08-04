import java.util.ArrayList;
import processing.core.PApplet;

public class SnakeGame extends PApplet {

    // Game settings
    int cellSize = 15;
    int score = 0;

    boolean gameOver = false;

    int xSpeed = 0;
    int ySpeed = 0;

    int foodX = 100;
    int foodY = 100;

    ArrayList<Integer> snakeX = new ArrayList<>();
    ArrayList<Integer> snakeY = new ArrayList<>();

    public void settings() {
        size(600, 400);
    }

    public void setup() {
        snakeX.add(300);
        snakeY.add(200);

        frameRate(5);
        noStroke();
    }

    public void draw() {

        background(30, 30, 40);
    if(!gameOver){
        moveSnake();

        checkFoodCollision();
        checkWallCollision();
        checkSelfCollision();

        drawSnake();
        drawFood();
    }else{
        gameOverScreen();
    } 

        drawScore();
    }

    // -----------------------------
    // Snake Movement
    // -----------------------------
    public void moveSnake() {

        for (int i = snakeX.size() - 1; i > 0; i--) {
            snakeX.set(i, snakeX.get(i - 1));
            snakeY.set(i, snakeY.get(i - 1));
        }

        snakeX.set(0, snakeX.get(0) + xSpeed);
        snakeY.set(0, snakeY.get(0) + ySpeed);
    }

    // -----------------------------
    // Draw Snake
    // -----------------------------
    public void drawSnake() {

        fill(255, 0, 0);

        for (int i = 0; i < snakeX.size(); i++) {
            rect(snakeX.get(i), snakeY.get(i), cellSize, cellSize);
        }
    }

    // -----------------------------
    // Draw Food
    // -----------------------------
    public void drawFood() {

        fill(0, 200, 255);
        ellipse(foodX, foodY, cellSize, cellSize);
    }

    // -----------------------------
    // Draw Score
    // -----------------------------
    public void drawScore() {

        fill(0, 255, 0);
        textSize(20);
        text("Score : " + score, 40, 30);
    }

    // -----------------------------
    // Food Collision
    // -----------------------------
    public void checkFoodCollision() {

        if (snakeX.get(0) < foodX + cellSize &&
                snakeX.get(0) + cellSize > foodX &&
                snakeY.get(0) < foodY + cellSize &&
                snakeY.get(0) + cellSize > foodY) {

            score += 10;

            // Generate food on the grid
            foodX = (int) random(width / cellSize) * cellSize;
            foodY = (int) random(height / cellSize) * cellSize;

            // Grow snake
            snakeX.add(snakeX.get(snakeX.size() - 1));
            snakeY.add(snakeY.get(snakeY.size() - 1));
        }
    }

    // -----------------------------
    // Wall Collision
    // -----------------------------
    public void checkWallCollision() {

        if (snakeX.get(0) < 0 ||
                snakeX.get(0) + cellSize > width ||
                snakeY.get(0) < 0 ||
                snakeY.get(0) + cellSize > height) {

            gameOver = true;
        }
    }

    // -----------------------------
    // Self Collision
    // -----------------------------
    public void checkSelfCollision() {

        // A snake with only one body segment can't collide with itself
        if (snakeX.size() < 3)
            return;

        for (int i = 1; i < snakeX.size(); i++) {

            if (snakeX.get(0).equals(snakeX.get(i)) &&
                    snakeY.get(0).equals(snakeY.get(i))) {

                gameOver = true;
            }
        }
    }

    // -----------------------------
    // Game Over
    // -----------------------------
    public void gameOverScreen(){
            textAlign(CENTER,CENTER);
            fill(0,255,0);
            textSize(40);
            text("GAME OVER", width/2,height/2-40);
            //textAlign(CENTER,CENTER);   

            textSize(20);
            text("Score : "+score, width/2, height/2);
            text("Press R to Restart",width/2, height/2 + 40);
    }

    // -----------------------------
    // Restart game
    // -----------------------------
    public void restartGame(){
       gameOver = false;

       score = 0;

       snakeX.clear();
       snakeY.clear();

       snakeX.add(300);
       snakeY.add(200);

       xSpeed = 0;
       ySpeed = 0;

       foodX = (int)(random(width / cellSize)) * cellSize;
       foodY = (int)(random(height / cellSize)) * cellSize;
    }

    // -----------------------------
    // Keyboard Controls
    // -----------------------------
    public void keyPressed() {

        if(gameOver && (key == 'r' || key == 'R')){
           restartGame();
           return;
        }

        if (keyCode == RIGHT) {
            xSpeed = cellSize;
            ySpeed = 0;
        }

        if (keyCode == LEFT) {
            xSpeed = -cellSize;
            ySpeed = 0;
        }

        if (keyCode == UP) {
            xSpeed = 0;
            ySpeed = -cellSize;
        }

        if (keyCode == DOWN) {
            xSpeed = 0;
            ySpeed = cellSize;
        }

    }

    public static void main(String[] args) {
        PApplet.main("SnakeGame");
    }
}