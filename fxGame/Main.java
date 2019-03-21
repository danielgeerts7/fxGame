package fxGame;

import fxGame.scenes.GameScene;
import fxGame.scenes.HelpScene;
import fxGame.scenes.MenuScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static enum state {MENU, GAME, HELP};
	
	private static int width = 1280;
	private static int height = 720;
	
	public static int getWidth() { return width; }
	public static int getHeight() { return height; }
	
	@Override
	public void start(Stage primaryStage) throws Exception {		
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		
		MenuScene menu = new MenuScene(primaryStage, Color.LIGHTBLUE);
		Scene scene = new Scene(menu);
		primaryStage.setTitle("DIDGY shooter game - javaFX"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
		primaryStage.show(); // Display the stage
	}
	
	public static void SwitchScene(state newstate, Stage primaryStage) {
		switch (newstate) {
			case MENU:  // Go back to menu
				System.out.println("----> main menu");
				MenuScene menu = new MenuScene(primaryStage, Color.LIGHTBLUE);
			    primaryStage.getScene().setRoot(menu);
			    
			break;
			case GAME: // Start game here
				System.out.println("----> game scene");
				GameScene gamescene = new GameScene(primaryStage, Color.LIGHTGREEN);
			    primaryStage.getScene().setRoot(gamescene);

				break;
			case HELP:  // Help scene
				System.out.println("----> help scene");
				HelpScene helpscene = new HelpScene(primaryStage, Color.LIGHTSALMON);
			    primaryStage.getScene().setRoot(helpscene);
				
			    break;
			default:
				System.out.println("ERROR, state unknown");
				break;
		}
	}
	
	public static void QuitApp() {
		System.out.println("Quiting application. Bye...");
		Platform.exit();
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
