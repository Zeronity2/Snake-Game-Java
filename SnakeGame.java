import processing.core.PApplet;

public class SnakeGame extends PApplet{
  //intial windows setup
  int snakeX = 300;
  int snakeY = 200;

  int xSpeed = 0;
  int ySpeed = 0;

  int foodX = 100;
  int foodY=100;

  public void settings(){
    size(600,400);
  }
  
  public void setup(){
    background(30, 30, 40);
    noStroke();
    frameRate(8);
  }

  public void draw(){
    //container
    fill(30, 30, 40);
    rect(0, 0, width, height);
    
    snakeX += xSpeed;
    snakeY += ySpeed;

    //snake movement
    fill(255,0,0);
    rect(snakeX, snakeY, 30,30);
    //food
    fill(0, 200, 255); 
    ellipse(foodX, foodY, 20, 20);

    //to check if snake eats food
    if(snakeX < foodX + 20 &&
      snakeX + 30 > foodX &&
      snakeY < foodY + 20 &&
      snakeY + 30 > foodY){

        //move food to random location
        foodX = (int) random(20, width-20);
        foodY = (int) random(20, height - 20);
      }
  }
  
  public static void main(String[] args){
      PApplet.main("SnakeGame");
  }

  
  public void keyPressed(){
    if(keyCode == RIGHT){
      xSpeed = 5;
      ySpeed = 0;
    }
    if(keyCode == LEFT){
      xSpeed = -5;
      ySpeed = 0;
    }
    if(keyCode == UP){
      xSpeed = 0;
      ySpeed = -5;
    }
    if(keyCode == DOWN){
      xSpeed = 0;
      ySpeed = 5;
    }
  }
} 