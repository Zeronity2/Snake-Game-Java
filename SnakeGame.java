import java.util.ArrayList;

import processing.core.PApplet;

public class SnakeGame extends PApplet{
  //intial windows setup
  // int snakeX = 300;
  // int snakeY = 200;

  int xSpeed = 0;
  int ySpeed = 0;

  int foodX = 100;
  int foodY=100;

  ArrayList<Integer> snakeX = new ArrayList<>();
  ArrayList<Integer> snakeY = new ArrayList<>();

  public void settings(){
    size(600,400);
  }
  
  public void setup(){
    snakeX.add(300);
    snakeY.add(200);
    background(30, 30, 40);
    noStroke();
    frameRate(8);
  }

  public void draw() {

    background(30,30,40);

    // Move body
    for(int i = snakeX.size() - 1; i > 0; i--){
        snakeX.set(i, snakeX.get(i - 1));
        snakeY.set(i, snakeY.get(i - 1));
    }

    // Move head
    snakeX.set(0, snakeX.get(0) + xSpeed);
    snakeY.set(0, snakeY.get(0) + ySpeed);

    // Draw snake
    fill(255,0,0);

    for(int i = 0; i < snakeX.size(); i++){
        rect(snakeX.get(i), snakeY.get(i),30,30);
    }

    // Draw food
    fill(0,200,255);
    ellipse(foodX,foodY,20,20);

    // Collision with food
    if(snakeX.get(0) < foodX + 20 &&
       snakeX.get(0) + 30 > foodX &&
       snakeY.get(0) < foodY + 20 &&
       snakeY.get(0) + 30 > foodY){

        // Random food
        foodX = (int)random(width - 20);
        foodY = (int)random(height - 20);

        // Grow snake
        snakeX.add(snakeX.get(snakeX.size()-1));
        snakeY.add(snakeY.get(snakeY.size()-1));
    }
}
  
  public static void main(String[] args){
      PApplet.main("SnakeGame");
  }

  
  public void keyPressed(){
    if(keyCode == RIGHT){
      xSpeed = 10;
      ySpeed = 0;
    }
    if(keyCode == LEFT){
      xSpeed = -10;
      ySpeed = 0;
    }
    if(keyCode == UP){
      xSpeed = 0;
      ySpeed = -10;
    }
    if(keyCode == DOWN){
      xSpeed = 0;
      ySpeed = 10;
    }
  }
} 