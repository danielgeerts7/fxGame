package fxGame.prefabs;

import fxGame.scenes.GameScene;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Character extends Prefab {
		
	private int moveSpeed = 0;
	public int getMoveSpeed() { return moveSpeed; }
	public void setMoveSpeed(int i) { moveSpeed = i; }

	public Character (int radius, Color col) {
		super(radius);
		Circle body = new Circle(radius, col);
		body.setStroke(Color.BLACK);
		
		Rectangle l_arm = createArm(col);
		l_arm.setTranslateY(-radius-2);
		Rectangle r_arm = createArm(col);
		r_arm.setTranslateY(radius+2);
		
		Circle l_eye = new Circle(radius/5, Color.BLACK);
		l_eye.setTranslateY(-5);
		Circle r_eye = new Circle(radius/5, Color.BLACK);
		r_eye.setTranslateY(5);
		
		super.getChildren().addAll(l_arm, r_arm, body, l_eye, r_eye);
	}
	
	@Override
	protected void update() {
	
	}
	
	public void lookAtPos(Point2D mouse_pos) {
		Point2D player_pos = new Point2D(this.getTranslateX(), this.getTranslateY());

		Point2D p3 = mouse_pos.subtract(player_pos);
		Point2D p4 = new Point2D(1, 0);
		
		if (mouse_pos.getY() < player_pos.getY()) {
			super.setRotate(p4.angle(p3) *-1);
		} else {
			super.setRotate(p4.angle(p3));
		}
	}
	
	private Rectangle createArm(Color col) {
		Rectangle r = new Rectangle();
		r.setRotate(90);
		r.setTranslateX(15);
		r.setWidth(7);
		r.setHeight(25);
		r.setFill(col);
		r.setStroke(Color.BLACK);
		return r;
	}
	
	public void addPos(double x, double y) {
		super.setTranslateX(super.getTranslateX() + x);
		super.setTranslateY(super.getTranslateY() + y);
	}
	
	public int getRadius(int multiplayer) {
		return super.radius * multiplayer;
	}
	

	protected void fireBullet(GameScene parent, Gun gun) {
		// Give parentScene to Bullet for self destruct
		Bullet b = new Bullet(Color.YELLOW, parent, this);
		b.setTranslateX(gun.getGunMuzzle().getLocalToSceneTransform().getTx());
		b.setTranslateY(gun.getGunMuzzle().getLocalToSceneTransform().getTy());
		b.lookAtPos(new Point2D(gun.getBulletPointer().getLocalToSceneTransform().getTx(), gun.getBulletPointer().getLocalToSceneTransform().getTy()));
		parent.addBullet(b);
	}
}
