package breakout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 */
public class Levels {
  public static int currentLevel=1;
  public static int[][] readLevelFiles(){
    int[][] levelSpecification = new int[4][10];
    try {
      File myObj = new File(Levels.class.getResource("/level"+currentLevel+".txt").getFile());
      Scanner myReader = new Scanner(myObj);
      int counter = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] hasBlock = data.split(" ");
        for(int i=0;i<hasBlock.length;i++){
          levelSpecification[counter][i] = Integer.parseInt(hasBlock[i]);
        }
        counter++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return levelSpecification;
  }
  public static void setLevel(){
    int[][] levelSpecification = readLevelFiles();
    //I could create a new group just for the bricks, but I am not sure what functionality/advantages/disadvantages that would add.
    //Creating the bricks and adding it to appropriate locations

    for (int i = 0; i < Breakout.bricksArray.length; i++) {
      for (int j = 0; j < Breakout.bricksArray[i].length; j++) {
        if(levelSpecification[i][j]==1){
          Brick brick = new Brick(j * Breakout.brickWidth, (i + 1) * Breakout.brickHeight, Breakout.brickWidth, Breakout.brickHeight,
              Breakout.colorOfBricks[i]);
          Breakout.root.getChildren().add(brick.rectangleBrick);
          Breakout.bricksArray[i][j] = brick;
          Breakout.bricksLeft++;
        }
      }
      Breakout.ball.resetBall();
    }
  }

}
