package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


/**
 * Main class to create what will be displayed
 *
 * @author Kushagra Ghosh
 */
public class Main extends Application {

    // useful names for constant values used
    public static String TITLE = "Breakout Game: JavaFX Animation";
    public static final int SIZE_X = 400;
    public static final int SIZE_Y = 600;
    public static final Paint BACKGROUND = Color.BLACK;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    // instance variables
    private Breakout myGame;

    public static Timeline animation;

    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start (Stage stage) {
        /*
        Circle shape = new Circle(190, 190, 20);
        shape.setFill(Color.LIGHTSTEELBLUE);

        Group root = new Group();
        root.getChildren().add(shape);

        Scene scene = new Scene(root, SIZE, SIZE, Color.DARKBLUE);
        stage.setScene(scene);

        stage.setTitle(TITLE);
        stage.show();*/

        // create game to be played
        myGame = new Breakout();

        // attach scene to the stage and display it
        Scene scene = myGame.setupGame(SIZE_X, SIZE_Y, BACKGROUND);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> myGame.step(SECOND_DELAY)));
        animation.play();
    }
}
