package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Ball {

  public Rectangle rectangleBall;
  //Ball vector in x and y
  public int xV = 0;
  public int yV = 2;

  public Ball(double xPosition, double yPosition, double side, Color color) {
    //Initialize the rectangle for the Ball class
    rectangleBall = new Rectangle(xPosition, yPosition, side, side);
    rectangleBall.setFill(color);
  }

  public void recolor(Color c) {
    rectangleBall.setFill(c);
  }

  public void updateBallPosition() {
    checkBounds();
    checkPaddle();
    checkBricks();
    rectangleBall.setX(rectangleBall.getX() + xV);
    rectangleBall.setY(rectangleBall.getY() + yV);

  }

  private void checkBricks() {
    int xIndex =  ((int) rectangleBall.getX()/(int)Breakout.brickWidth);
    int yIndex = ( (int) (rectangleBall.getY()-Breakout.brickHeight) / (int) Breakout.brickHeight );
    if(xIndex<=9 && xIndex>=0 && yIndex>=0 &&  yIndex <4 && Breakout.bricksArray[yIndex][xIndex]!=null){
      if (Shape.intersect(rectangleBall, Breakout.bricksArray[yIndex][xIndex].rectangleBrick).getBoundsInLocal().isEmpty()
          == false) {
        yV = -yV;
        Breakout.root.getChildren().remove(Breakout.bricksArray[yIndex][xIndex].rectangleBrick);
        Breakout.bricksArray[yIndex][xIndex]=null;
        Breakout.bricksLeft--;
        if(Breakout.bricksLeft==0){
          if(Levels.currentLevel<3){
            Levels.currentLevel+=1;
            Levels.setLevel();
            Breakout.text.setText("Level "+Levels.currentLevel+ ", "+Breakout.lives+ " Lives Remaining");
          }
          else{
            Breakout.endGame();
          }
        }
      }
    }
  }

  private void checkPaddle() {
    if (Shape.intersect(rectangleBall, Breakout.paddle.rectanglePaddle).getBoundsInLocal().isEmpty()
        == false) {
      yV = -yV;
      if (rectangleBall.getX() < (Breakout.paddle.rectanglePaddle.getX()
          + Breakout.paddle.rectanglePaddle.getWidth() / 3)) {
        xV = -2;
      } else if (rectangleBall.getX() > (Breakout.paddle.rectanglePaddle.getX()
          + 2 * Breakout.paddle.rectanglePaddle.getWidth() / 3)) {
        xV += 2;
      } else {
      }
    }
  }

  private void checkBounds() {
    if (rectangleBall.getX() <= 0) {
      xV = -xV;
    } else if (rectangleBall.getX() >= Main.SIZE_X - rectangleBall.getWidth() - 1) {
      xV = -xV;
    } else if (rectangleBall.getY() <= 0) {
      yV = -yV;
    } else if (rectangleBall.getY() >= Main.SIZE_Y - rectangleBall.getHeight() - 1) {
      Breakout.lives -= 1;
      Breakout.text.setText("Level "+Levels.currentLevel+ ", "+Breakout.lives+ " Lives Remaining");
      if (Breakout.lives == 0) {
        Breakout.endGame();
      }
      resetBall();
    }
  }

  public void resetBall(){
    xV = 0;
    yV = 2;
    rectangleBall.setX(Main.SIZE_X / 2 - Main.SIZE_Y / 10 / 6 / 2);
    rectangleBall.setY(30 * Main.SIZE_Y / 10 / 6);
  }
  public void resize(double factor) {
    if (rectangleBall.getWidth() + factor > 0) {
      rectangleBall.setWidth(rectangleBall.getWidth() + factor);
      rectangleBall.setHeight(rectangleBall.getHeight() + factor);
    }
  }

}
