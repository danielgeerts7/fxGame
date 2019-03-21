package fxGame;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public abstract class Character extends StackPane {
	
	private int radius = 0;
	
	private int moveSpeed = 0;
	public int getMoveSpeed() { return moveSpeed; }
	public void setMoveSpeed(int i) { moveSpeed = i; }

	public Character (int radius, Color col) {
		this.radius = radius;
		Circle body = new Circle(radius, col);
		body.setStroke(Color.BLACK);
		
		Rectangle l_arm = createRect(col);
		l_arm.setTranslateY(-radius-3);
		l_arm.setRotate(90);
		Rectangle r_arm = createRect(col);
		r_arm.setTranslateY(radius+3);
		r_arm.setRotate(90);
		
		Circle l_eye = new Circle(radius/5, Color.BLACK);
		l_eye.setTranslateY(-5);
		Circle r_eye = new Circle(radius/5, Color.BLACK);
		r_eye.setTranslateY(5);
		
	    this.getChildren().addAll(l_arm, r_arm, body, l_eye, r_eye);
		
	    AnimationTimer animator = new AnimationTimer()
		    {
		        @Override
		        public void handle(long arg0) 
		        {
		            update();
		        }
		    };
		    
		    animator.start();
		}

	protected abstract void update();
	
	private Rectangle createRect(Color col) {
		Rectangle r = new Rectangle();
		r.setTranslateX(7);
		r.setWidth(7);
		r.setHeight(30);
		r.setFill(col);
		r.setStroke(Color.BLACK);
		return r;
	}
	
	public void addPos(double x, double y) {
		this.setTranslateX(this.getTranslateX() + x);
		this.setTranslateY(this.getTranslateY() + y);
	}
	
	public void turnAround() {
		this.setRotate(this.getRotate()+1);
	}
	
	public void turnAround(int speed) {
		this.setRotate(this.getRotate()+speed);
	}
	
	public int getRadius() {
		return this.radius;
	}
}
