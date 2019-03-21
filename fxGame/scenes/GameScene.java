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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameScene extends SuperScene {

	private static GridPane mainpane = null;
	private static Pane gamepane = null;
	private Button btnBackToMenu = null;
	private Button btnQuit = null;

	private Player player = null;
	private List<Enemie> enemies = null;
	private MouseEvent mouseEventMoved = null;
	
	public GameScene(Stage primaryStage, Color bg_color) {
		super();

		mainpane = new GridPane();

		gamepane = new Pane();
		gamepane.setBackground(new Background(new BackgroundFill(bg_color, CornerRadii.EMPTY, Insets.EMPTY)));

		player = new Player(15, Color.GREEN);
		player.setTranslateX(100);
		player.setTranslateY(100);
		player.setRotate(0);
		player.setMoveSpeed(3);
		gamepane.getChildren().add(player);

		enemies = new ArrayList<Enemie>();

		for (int i = 0; i < 10; i++) {
			Enemie enemie = new Enemie(15, Color.RED);
			enemie.setTranslateX((int) (Math.random() * Main.getWidth() * 0.85) + enemie.getRadius());
			enemie.setTranslateY((int) (Math.random() * Main.getHeight() * 0.85) + enemie.getRadius());
			gamepane.getChildren().add(enemie);
			enemies.add(enemie);
		}

		primaryStage.getScene().addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				mouseEventMoved = mouseEvent;
			}
		});

		this.setButtonActions(primaryStage);
		
        gamepane.requestFocus();
        gamepane.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent ke) {
        		player.hanldeKeyPressed(ke);
        	}
        });
        gamepane.setOnKeyReleased(new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent ke) {
        		player.hanldeKeyReleased(ke);
        	}
        });
	}

	/*
	 * Every frame this function will run
	 * 
	 * @see fxGame.SuperScene#update()
	 */
	@Override
	public void update() {
		if (mouseEventMoved != null) {
			player.lookAtPos(new Point2D(mouseEventMoved.getX(), mouseEventMoved.getY()));
		}
		
		Iterator<Enemie> it = enemies.iterator();
		while (it.hasNext()) {
			Character e = (Character) it.next();
			e.turnAround();
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
		mainpane.add(btnBackToMenu, 0, 0);
		mainpane.add(btnQuit, 1, 0);
		gamepane.getChildren().add(mainpane);
	}

	public Pane getMainPane() {
		return gamepane;
	}
}
