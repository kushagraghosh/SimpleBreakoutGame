package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {
  public Rectangle rectangleBrick;

  public Brick(double xPosition, double yPosition, double width, double height, Color color){
    //Initialize the rectangle for the Ball class
    rectangleBrick = new Rectangle(xPosition, yPosition, width, height);
    rectangleBrick.setFill(color);
    rectangleBrick.setStrokeWidth(0.1);
    rectangleBrick.setStroke(Color.BLACK);
  }
  public void recolor(Color c){
    rectangleBrick.setFill(c);
  }
}
