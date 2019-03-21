package fxGame.prefabs;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Gun extends Prefab {
	
	Rectangle body = null;
	private Point2D size = null;
	
	private Rectangle gunMuzzle = null;
	private Rectangle bulletPointer = null;
	
	public Gun(Color col, Point2D newSize) {
		super((int)(newSize.getX() + newSize.getY()));
		this.size = newSize;
		super.seesWindowEdge = false;

		this.body = new Rectangle(size.getX(), size.getY(), col);
		super.getChildren().add(body);
        
        gunMuzzle = new Rectangle();
		bulletPointer = new Rectangle();
		gunMuzzle.setVisible(false);
		bulletPointer.setVisible(false);
		super.addChild(gunMuzzle, 10, 0);
		super.addChild(bulletPointer, 100, 0);
	}

	@Override
	protected void update() {
		
	}
	
	public Rectangle getGunMuzzle() {
		return gunMuzzle;
	}
	public Rectangle getBulletPointer() {
		return bulletPointer;
	}

}
