package fxGame.scenes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fxGame.Character;
import fxGame.Enemie;
import fxGame.Main;
import fxGame.Player;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameScene extends SuperScene {

	private static GridPane menupane = null;
	private Button btnBackToMenu = null;
	private Button btnQuit = null;

	private static Player player = null;
	private List<Enemie> enemies = null;
	
	public static Player getPlayer() { return player; }

	public GameScene(Stage primaryStage, Color bg_color) {
		super();
		this.setBackground(new Background(new BackgroundFill(bg_color, CornerRadii.EMPTY, Insets.EMPTY)));

		menupane = new GridPane();

		player = new Player(15, Color.GREEN, primaryStage);
		player.setTranslateX(100);
		player.setTranslateY(100);
		player.setRotate(0);
		player.setMoveSpeed(3);
		this.getChildren().add(player);

		enemies = new ArrayList<Enemie>();

		for (int i = 0; i < 200; i++) {
			Enemie enemie = new Enemie(15, Color.RED);
			enemie.setTranslateX((int) (Math.random() * (Main.getWidth()*.9) + Main.getWidth()*.05));
			enemie.setTranslateY((int) (Math.random() * (Main.getHeight()*.6) + Main.getHeight()*.3));
			this.getChildren().add(enemie);
			enemies.add(enemie);
		}

		this.setButtonActions(primaryStage);

		this.requestFocus();
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				player.hanldeKeyPressed(ke);
			}
		});
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				player.hanldeKeyReleased(ke);
			}
		});
		this.getChildren().add(menupane);
	}

	/*
	 * Every frame this function will run
	 * 
	 * @see fxGame.SuperScene#update()
	 */
	@Override
	public void update() {
		Iterator<Enemie> it = enemies.iterator();
		Point2D temp = new Point2D(player.getTranslateX(), player.getTranslateY());
		while (it.hasNext()) {
			Character e = (Character) it.next();
			e.lookAtPos(temp);
		}
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
		menupane.add(btnBackToMenu, 0, 0);
		menupane.add(btnQuit, 1, 0);
	}
}
