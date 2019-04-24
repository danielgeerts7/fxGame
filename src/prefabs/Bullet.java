package prefabs;

import scenes.SuperScene;
import M.Config;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends Prefab {
	
	private Rectangle body = null;
	private static Point2D size = new Point2D(10,5);
	
	private int speed = 20;
	private SuperScene parent = null;
	private Character shooter = null;

	public Bullet(Color col, SuperScene parent, Character shooter) {
		super((int) (size.getX() + size.getY()));

		this.body = new Rectangle(size.getX(), size.getY(), col);
		this.getChildren().add(body);
		this.parent = parent;
		this.shooter = shooter;
	}

	protected void update() {
		double x = (double) Math.cos(Math.toRadians(this.getRotate()));
		double y = (double) Math.sin(Math.toRadians(this.getRotate()));
		Point2D direction = new Point2D(x, y);
		Point2D velocity = new Point2D(direction.getX() * speed, direction.getY() * speed);

		super.setTranslateX(this.getTranslateX() + velocity.getX());
		super.setTranslateY(this.getTranslateY() + velocity.getY());
	}

	public void lookAtPos(Point2D mouse_pos) {
		Point2D player_pos = new Point2D(this.getTranslateX(), this.getTranslateY());
		Point2D p3 = mouse_pos.subtract(player_pos);
		Point2D p4 = new Point2D(1, 0);

		if (mouse_pos.getY() < player_pos.getY()) {
			super.setRotate(p4.angle(p3) * -1);
		} else {
			super.setRotate(p4.angle(p3));
		}
	}

	@Override
	protected void StayWithinScreen() {
		if (super.getTranslateX() < 0 ||
			super.getTranslateX() > Config.getWidth() ||
			super.getTranslateY() < 0 ||
			super.getTranslateY() > Config.getHeight()) {
			super.selfDestruct(parent);
		}
	}
	
	public Character getShooter() {
		return shooter;
	}
}
