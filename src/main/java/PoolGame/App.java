/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package PoolGame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import PoolGame.Items.Pocket;
import PoolGame.Items.PoolTable;
import javafx.scene.control.Button;
import org.json.simple.parser.ParseException;

import PoolGame.ConfigReader.ConfigKeyMissingException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.util.Duration;

/** The JavaFX application */
public class App extends Application {

    public static final double FRAMETIME = 1.0 / 60.0;

    private ConfigReader loadConfig(List<String> args) {
        String configPath;
        boolean isResourcesDir = false;
		if (args.size() > 0) {
			configPath = args.get(0);
		} else {
//			 configPath = "src/main/resources/config_easy.json";
			configPath = "/config_easy.json";
            isResourcesDir = true;
		}
		// parse the file:
        ConfigReader config = null;
        try {
            config = new ConfigReader(configPath, isResourcesDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (ConfigKeyMissingException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        }
        return config;
    }

    @Override
    public void start(Stage stage) throws ConfigKeyMissingException, IOException, ParseException {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("PoolGame");
        stage.show();
        
        ConfigReader config = loadConfig(getParameters().getRaw());
        Game game = new Game(config, root, scene);
        
        Canvas canvas = new Canvas(game.getWindowDimX(), game.getWindowDimY());

        stage.setWidth(game.getWindowDimX());
        stage.setHeight(game.getWindowDimY() +
                        Pocket.RADIUS +
                        PoolTable.POCKET_OFFSET +
                        4); // Magic number to get bottom to align

        root.getChildren().add(canvas);
        // GraphicsContext gc = canvas.getGraphicsContext2D();
        game.addDrawables(root);
        
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(FRAMETIME),
        (actionEvent) -> {
                game.tick();
            });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    /**
     * The entry point of the program
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
