package M;

import scenes.GameScene;
import scenes.HelpScene;
import scenes.MenuScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public final class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {		
		primaryStage.setWidth(Config.getWidth());
		primaryStage.setHeight(Config.getHeight());
		
		MenuScene menu = new MenuScene(primaryStage, Color.MIDNIGHTBLUE);
		Scene scene = new Scene(menu);
		primaryStage.setTitle("DIDGY shooter game - javaFX"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
		primaryStage.show(); // Display the stage
	}
	
	public static void SwitchScene(Config.state newstate, Stage primaryStage) {
		switch (newstate) {
			case MENU:  // Go back to menu
				System.out.println("----> main menu");
				MenuScene menu = new MenuScene(primaryStage, Color.MIDNIGHTBLUE);
			    primaryStage.getScene().setRoot(menu);
			    
			break;
			case GAME: // Start game here
				System.out.println("----> game scene");
				GameScene gamescene = new GameScene(primaryStage, Color.LIGHTGREEN);
			    primaryStage.getScene().setRoot(gamescene);

				break;
			case HELP:  // Help scene
				System.out.println("----> help scene");
				HelpScene helpscene = new HelpScene(primaryStage, Color.MIDNIGHTBLUE);
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
