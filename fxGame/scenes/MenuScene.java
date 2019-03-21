package fxGame.scenes;

import fxGame.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuScene extends SuperScene {

	private static GridPane mainpane = null;
	private Button btnStart = null;
	private Button btnHelp = null;
	private Button btnQuit = null;

	private Text title = null;
	private Text credits = null;

	public MenuScene(Stage primaryStage, Color bg_color) {
		super();
		
		mainpane = new GridPane();
		mainpane.setAlignment(Pos.CENTER);
		mainpane.setMinSize(Main.getWidth(), Main.getHeight());
		mainpane.setBackground(new Background(new BackgroundFill(bg_color, CornerRadii.EMPTY, Insets.EMPTY)));
		mainpane.setPadding(new Insets(10, 10, 10, 10));

		this.setButtonActions(primaryStage);
		this.setTextField();
		
		this.getChildren().add(mainpane);
	}
	
	@Override
	public void update() {

	}
	
	private void setTextField() {
		title = new Text();
		title.setText("This is my awesome java game!");
		title.setFill(Color.WHITE);
		mainpane.add(title, 0, 0);

		credits = new Text();
		credits.setText("Made by Daniël Geerts");
		credits.setFill(Color.WHITE);
		mainpane.add(credits, 3, 3);
	}

	private void setButtonActions(Stage primaryStage) {
		btnStart = new Button("Start game");
		btnStart.setOnAction(e -> {
			Main.SwitchScene(Main.state.GAME, primaryStage);
		});

		btnHelp = new Button("Help!");
		btnHelp.setOnAction(e -> {
			Main.SwitchScene(Main.state.HELP, primaryStage);
		});

		btnQuit = new Button("Quit");
		btnQuit.setOnAction(e -> {
			Main.QuitApp();
		});

		// Attach buttons to the pane
		mainpane.add(btnStart, 1, 1);
		mainpane.add(btnHelp, 1, 2);
		mainpane.add(btnQuit, 1, 3);
	}
}
