package fxGame;

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

	public Player(int radius, Color col, Stage primaryStage) {
		super(radius, col);
		
		primaryStage.getScene().addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				mouseEventMoved = mouseEvent;
			}
		});
	}
	
	protected void update() {
		if (mouseEventMoved != null) {
			this.lookAtPos(new Point2D(mouseEventMoved.getX(), mouseEventMoved.getY()));
		}
		
		if (moveTowards) {
			this.addPos(0, -this.getMoveSpeed());
		}
		if (moveDownwards) {
			this.addPos(0, this.getMoveSpeed());
		}
		if (moveLeftwards) {
			this.addPos(-this.getMoveSpeed(), 0);
		}
		if (moveRightwards) {
			this.addPos(this.getMoveSpeed(), 0);
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
