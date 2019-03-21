package fxGame.prefabs;

import java.util.Date;
import java.util.List;
import fxGame.scenes.GameScene;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Player extends Character {

	private boolean moveTowards = false;
	private boolean moveDownwards = false;
	private boolean moveLeftwards = false;
	private boolean moveRightwards = false;

	private MouseEvent mouseEventMoved = null;
	private Gun lgun = null;
	private Gun rgun = null;

	private boolean mustShoot = false;
	private long delayNextBulletMS = 1000 / 8;
	private long counterBulletMS = 0;
	private long startTime = 0;

	private GameScene parent = null;

	public Player(int radius, Color col, Stage primaryStage, GameScene parent) {
		super(radius, col);

		this.parent = parent;
		super.setTranslateX(100);
		super.setTranslateY(100);
		super.setRotate(0);
		super.setMoveSpeed(3);

		this.lgun = new Gun(Color.PURPLE, new Point2D(20, 5));
		this.rgun = new Gun(Color.PURPLE, new Point2D(20, 5));
		super.addChild(lgun, 30, -16);
		super.addChild(rgun, 30, 16);

		this.addMouseEventHandlers(primaryStage, parent);
	}

	private void addMouseEventHandlers(Stage primaryStage, GameScene parent) {
		primaryStage.getScene().addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				mouseEventMoved = mouseEvent;
			}
		});

		primaryStage.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				mustShoot = true;
			}
		});

		primaryStage.getScene().addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				mustShoot = false;
			}
		});
	}

	protected void update() {

		this.updateMovement();

		this.updateBullets();

	}

	private void updateMovement() {
		if (mouseEventMoved != null) {
			super.lookAtPos(new Point2D(mouseEventMoved.getX(), mouseEventMoved.getY()));
		}
		if (moveTowards) {
			super.addPos(0, -this.getMoveSpeed());
		}
		if (moveDownwards) {
			super.addPos(0, this.getMoveSpeed());
		}
		if (moveLeftwards) {
			super.addPos(-this.getMoveSpeed(), 0);
		}
		if (moveRightwards) {
			super.addPos(this.getMoveSpeed(), 0);
		}
	}

	private void updateBullets() {
		List<Bullet> bullets = parent.getBullets((Character) this);
		for (int i = 0; i < bullets.size(); i++) {
			if (!bullets.get(i).isAlive()) {
				parent.removeBullet(bullets.get(i));
				bullets.remove(i);
				i--;
			}
		}

		counterBulletMS = (new Date()).getTime() - startTime;
		if (mustShoot) {
			if (counterBulletMS > delayNextBulletMS) {
				fireBullet(parent, lgun);
				fireBullet(parent, rgun);
				counterBulletMS = 0;
				startTime = System.currentTimeMillis();
			}
		} else {
			counterBulletMS = 0;
			startTime = 0;
		}
	}

	public void hanldeKeyPressed(KeyEvent input) {
		if (input.getCode() == KeyCode.W) {
			moveTowards = true;
		}
		if (input.getCode() == KeyCode.S) {
			moveDownwards = true;
		}
		if (input.getCode() == KeyCode.A) {
			moveLeftwards = true;
		}
		if (input.getCode() == KeyCode.D) {
			moveRightwards = true;
		}
	}

	public void hanldeKeyReleased(KeyEvent input) {
		if (input.getCode() == KeyCode.W) {
			moveTowards = false;
		}
		if (input.getCode() == KeyCode.S) {
			moveDownwards = false;
		}
		if (input.getCode() == KeyCode.A) {
			moveLeftwards = false;
		}
		if (input.getCode() == KeyCode.D) {
			moveRightwards = false;
		}
	}
}
