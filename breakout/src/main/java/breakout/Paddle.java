package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle {
  public Rectangle rectanglePaddle;

  public Paddle(double xPosition, double yPosition, double width, double height, Color color){
    //Initialize the rectangle for the Paddle class
    rectanglePaddle = new Rectangle(xPosition, yPosition, width, height);
    rectanglePaddle.setFill(color);
  }
  public void recolor(Color c){
    rectanglePaddle.setFill(c);
  }
  public void resize(double factor){
    if(rectanglePaddle.getWidth()+factor > 0){
      rectanglePaddle.setWidth(rectanglePaddle.getWidth()+factor);
      rectanglePaddle.setX(rectanglePaddle.getX()-factor/2);
    }
  }
  public void updateX(double x){
    if (rectanglePaddle.getX()+x <= 0 ){
      rectanglePaddle.setX(0);
    }
    else if(rectanglePaddle.getX()+x+rectanglePaddle.getWidth() >= Main.SIZE_X){
      rectanglePaddle.setX(Main.SIZE_X-rectanglePaddle.getWidth());
    }
    else{
      rectanglePaddle.setX(rectanglePaddle.getX()+x);
    }

  }
  public void setNewX(double x){
    if (x <= 0) {
      rectanglePaddle.setX(0);
    }
    else if (x >= Main.SIZE_X-rectanglePaddle.getWidth()-1){
      rectanglePaddle.setX(Main.SIZE_X-rectanglePaddle.getWidth());
    }
    else{
      rectanglePaddle.setX(x);
    }

  }

  /*public void updateY(double y){
    rectanglePaddle.setY(rectanglePaddle.getY()-y);
  }*/
}
