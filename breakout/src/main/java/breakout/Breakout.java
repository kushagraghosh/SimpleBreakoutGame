package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

/**
 * An example class, separate from the basic application, that manages the game interactions.
 *
 * @author Kushagra Ghosh
 */
public class Breakout {

  public static Brick[][] bricksArray = new Brick[4][10];
  public static int bricksLeft=0;
  public static Color[] colorOfBricks = {Color.RED, Color.ORANGE, Color.GREEN, Color.YELLOW};
  public static Paddle paddle;
  public static Ball ball;
  public static Text text;

  public static double paddleWidth;
  public static double paddleHeight;
  public static double ballSide;
  public static double brickWidth;
  public static double brickHeight;
  public static Group root;
  public static int lives = 3;

  /**
   * Create the game's "scene": what shapes will be in the game and their starting properties
   * */
  public Scene setupGame(int width, int height, Paint background) {
    // create one top level collection to organize the things in the scene
    root = new Group();
    paddleWidth = 2 * width / 10;
    paddleHeight = height / 10 / 6;
    ballSide = paddleHeight;
    // make some shapes and set their properties
    paddle = new Paddle(width / 2 - paddleWidth / 2, (50 * height / 10 / 6), paddleWidth,
        paddleHeight, Color.BLUE);
    ball = new Ball(width / 2 - ballSide / 2, (30 * height / 10 / 6), ballSide, Color.GRAY);

    //I could create a new group just for the bricks, but I am not sure what functionality/advantages/disadvantages that would add.
    //Creating the bricks and adding it to appropriate locations
    brickWidth = width / 10;
    brickHeight = height / 10 / 2;
    Levels.currentLevel=1;
    Levels.setLevel();

    //Creating a Text object
    text = new Text();
    text.setText("Level "+Levels.currentLevel+ ", "+Breakout.lives+ " Lives Remaining");
    //Setting font to the text
    text.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
    //setting the position of the text
    text.setX(0);
    text.setY(Main.SIZE_Y-10);
    //Setting the color
    text.setFill(Color.WHITE);
    Breakout.root.getChildren().add(text);

    // order added to the group is the order in which they are drawn
    root.getChildren().add(paddle.rectanglePaddle);
    root.getChildren().add(ball.rectangleBall);
    // create a place to see the shapes
    Scene scene = new Scene(root, width, height, background);
    // respond to input
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    scene.setOnMouseMoved(e -> handleMouseInput(e.getX(), e.getY()));
    return scene;
  }

  /**
   * Updates ball position with step
   * @param elapsedTime
   */
  public void step(double elapsedTime) {
    ball.updateBallPosition();
  }

  /**
   * Handles the key inputs from the user to make cheat key and paddle functionality.
   * @param code
   */
  private void handleKeyInput(KeyCode code) {
    if (code == KeyCode.RIGHT) {
      paddle.updateX(10);
    } else if (code == KeyCode.LEFT) {
      paddle.updateX(-10);
    } else if (code == KeyCode.UP) {
      paddle.resize(10);
      //paddle.updateY(10);
    } else if (code == KeyCode.DOWN) {
      paddle.resize(-10);
      //paddle.updateY(-10);
    } else if (code == KeyCode.W) {
      ball.resize(2);
      //paddle.updateY(-10);
    } else if (code == KeyCode.S) {
      ball.resize(-2);
      //paddle.updateY(-10);
    } else if (code == KeyCode.A) {
      ball.recolor(Color.color(Math.random(), Math.random(), Math.random()));
    } else if (code == KeyCode.D) {
      paddle.recolor(Color.color(Math.random(), Math.random(), Math.random()));
    }
    else if (code == KeyCode.L) {
      lives++;
      text.setText("Level "+Levels.currentLevel+ ", "+Breakout.lives+ " Lives Remaining");
    }
    else if (code == KeyCode.R) {
      ball.resetBall();
    }
  }

  /**
   * Handles the mouse inputs for more paddle functionality.
   * @param x
   * @param y
   */
  private void handleMouseInput(double x, double y) {
    paddle.setNewX(x);
    //System.out.println(x);
  }

  /**
   * Ends the game after losing or completing all levels from Levels class.
   */
  public static void endGame(){
    paddle.recolor(Color.GRAY);
    ball.recolor(Color.GRAY);
    for (int i = 0; i < bricksArray.length; i++) {
      for (int j = 0; j < bricksArray[i].length; j++) {
        if(bricksArray[i][j]!=null){
          bricksArray[i][j].recolor(Color.GRAY);
        }
      }
    }
    text.setText("Game Over");
    Main.animation.stop();
    System.out.println("End of Game");
  }
}
