package fxGame;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Player extends Character {
	
	private boolean moveTowards = false;
	private boolean moveDownwards = false;
	private boolean moveLeftwards = false;
	private boolean moveRightwards = false;

	public Player(int radius, Color col) {
		super(radius, col);
	}
	
	public void lookAtPos(Point2D mouse_pos) {
		Point2D player_pos = new Point2D(this.getTranslateX(), this.getTranslateY());

		Point2D p3 = mouse_pos.subtract(player_pos);
		Point2D p4 = new Point2D(1, 0);
		
		if (mouse_pos.getY() < player_pos.getY()) {
			this.setRotate(p4.angle(p3) *-1);
		} else {
			this.setRotate(p4.angle(p3));
		}
	}
	
	protected void update() {		
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
