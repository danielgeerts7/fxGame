package fxGame.scenes;

import fxGame.Main;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelpScene {

	private static GridPane mainpane = null;
	private Button btnBackToMenu = null;
	private Button btnQuit = null;

	private Text title = null;
	private Text credits = null;

	public HelpScene(Stage primaryStage, Color bg_color) {
		mainpane = new GridPane();
		mainpane.setBackground(new Background(new BackgroundFill(bg_color, CornerRadii.EMPTY, Insets.EMPTY)));
		mainpane.setPadding(new Insets(10, 10, 10, 10));

		btnBackToMenu = new Button();
		btnQuit = new Button();

		this.setButtonActions(primaryStage);

		title = new Text();
		title.setText("This is my awesome java game!");
		title.setFill(Color.WHITE);
		mainpane.add(title, 3, 2);
		
		title = new Text();
		title.setText("Lorem ipsum\n"
				+ "Lorem ipsum\n"
				+ "Lorem ipsum\n"
				+ "Lorem ipsum\n"
				+ "Lorem ipsum\n"
				+ "Lorem ipsum\n"
				+ "Lorem ipsum");
		title.setFill(Color.WHITE);
		mainpane.add(title, 3, 3);

		credits = new Text();
		credits.setText("Made by Daniël Geerts");
		credits.setFill(Color.WHITE);
		mainpane.add(credits, 3, 10);
	}

	private void setButtonActions(Stage primaryStage) {
		btnBackToMenu = new Button("Back to menu");
		btnBackToMenu.setOnAction(e -> {
			System.out.println("Starting game scene");
			Main.SwitchScene(Main.state.MENU, primaryStage);
		});
		
		
		btnQuit = new Button("Quit");
		btnQuit.setOnAction(e -> {
			Main.QuitApp();
		});
		
		// Attach buttons to this pane
		mainpane.add(btnBackToMenu, 0, 0);
		mainpane.add(btnQuit, 0, 1);
	}

	public GridPane getMainPane() {
		return mainpane;
	}
}
