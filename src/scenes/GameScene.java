package scenes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import M.Config;
import M.Main;
import prefabs.Bullet;
import prefabs.Character;
import prefabs.Enemie;
import prefabs.Player;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

	private Group bullets = null;

	Label player_info = null;

	public GameScene(Stage primaryStage, Color bg_color) {
		super();
		super.setBackground(new Background(new BackgroundFill(bg_color, CornerRadii.EMPTY, Insets.EMPTY)));

		menupane = new GridPane();

		// First: bullets are now layered under the player and enemies
		bullets = new Group();
		super.getChildren().add(bullets);

		// Second: add Player
		player = new Player(15, Color.GREEN, primaryStage, this);
		super.getChildren().add(player);

		// Third: add some Enemies
		enemies = new ArrayList<Enemie>();
		for (int i = 0; i < 200; i++) {
			Enemie enemie = new Enemie(15, Color.RED);
			enemie.setTranslateX((int) (Math.random() * (Config.getWidth() * .9) + Config.getWidth() * .05));
			enemie.setTranslateY((int) (Math.random() * (Config.getHeight() * .6) + Config.getHeight() * .3));
			super.getChildren().add(enemie);
			enemies.add(enemie);
		}

		this.setButtonAndKeyActions(primaryStage);

		// Fourth: add menu at the top layer
		super.getChildren().add(menupane);

		player_info = new Label();
		player_info.setTranslateX(Config.getWidth() / 2);
		player_info.setTranslateY(0);
		super.getChildren().add(player_info);
	}

	/*
	 * Every frame this function will run
	 * 
	 * @see SuperScene#update()
	 */
	@Override
	public void update() {
		updateBullets();

		updatePlayerLabel();
	}

	private void setButtonAndKeyActions(Stage primaryStage) {
		btnBackToMenu = new Button("Back to menu");
		btnBackToMenu.setOnAction(e -> {
			System.out.println("Starting game scene");
			Main.SwitchScene(Config.state.MENU, primaryStage);
		});

		btnQuit = new Button("Quit");
		btnQuit.setOnAction(e -> {
			Main.QuitApp();
		});

		// Attach buttons to this pane
		menupane.add(btnBackToMenu, 0, 0);
		menupane.add(btnQuit, 1, 0);

		// Keyboard events
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
	}

	private void updateBullets() {
		List<Enemie> toRemoveEnemies = new ArrayList<Enemie>();
		List<Bullet> toRemoveBulls = new ArrayList<Bullet>();
		Iterator<Enemie> it_enemie = enemies.iterator();
		while (it_enemie.hasNext()) {
			Enemie enemie = it_enemie.next();
			Iterator<Node> it_bull = bullets.getChildren().iterator();
			while (it_bull.hasNext()) {
				Bullet bull = (Bullet) it_bull.next();
				if (bull.getTranslateX() > enemie.getTranslateX() - enemie.getRadius(2)
						&& bull.getTranslateX() < enemie.getTranslateX() + enemie.getRadius(2)
						&& bull.getTranslateY() > enemie.getTranslateY() - enemie.getRadius(2)
						&& bull.getTranslateY() < enemie.getTranslateY() + enemie.getRadius(2)) {
					enemie.selfDestruct(this);
					bull.selfDestruct(this);
					toRemoveEnemies.add(enemie);
					toRemoveBulls.add(bull);
				}
			}
		}

		for (int i = 0; i < toRemoveEnemies.size(); i++) {
			enemies.remove(toRemoveEnemies.get(i));
		}

		for (int i = 0; i < toRemoveBulls.size(); i++) {
			bullets.getChildren().remove(toRemoveBulls.get(i));
		}
	}

	private void updatePlayerLabel() {
		String value = "";
		value += "Current bullets on screen:	" + bullets.getChildren().size() + "\n";
		value += "Enemies left:				" + enemies.size();

		player_info.setText(value);
	}

	public void addBullet(Bullet newBull) {
		bullets.getChildren().add(newBull);
	}

	public void removeBullet(Bullet newBull) {
		bullets.getChildren().remove(newBull);
	}

	public List<Bullet> getBullets(Character character) {
		List<Bullet> temp = new ArrayList<Bullet>();
		Iterator<Node> it = bullets.getChildren().iterator();
		while (it.hasNext()) {
			Bullet b = (Bullet) it.next();
			if (b.getShooter().equals(character)) {
				temp.add(b);
			}
		}
		return temp;
	}

	public static Player getPlayer() {
		return player;
	}
}
