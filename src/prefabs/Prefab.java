package prefabs;

import scenes.SuperScene;
import M.Config;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public abstract class Prefab extends StackPane {

	public int radius = 0;
	private boolean alive = true;

	public boolean isAlive() {
		return alive;
	}

	protected boolean seesWindowEdge = true;

	public Prefab(int radius) {
		this.radius = radius;
		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				update();
				StayWithinScreen();
			}
		}.start();
	}

	protected abstract void update();

	public void selfDestruct(SuperScene parent) {
		parent.getChildren().remove(this);
		alive = false;
	}

	protected void addChild(Node child, int x, int y) {
		child.setTranslateX(x);
		child.setTranslateY(y);
		super.getChildren().add(child);
	}

	protected void StayWithinScreen() {
		if (seesWindowEdge) {
			if (super.getTranslateX() < 0) {
				super.setTranslateX(0);
			}
			if (super.getTranslateX() + radius > Config.getWidth()) {
				super.setTranslateX(Config.getWidth() - radius);
			}
			if (super.getTranslateY() < 0) {
				super.setTranslateY(0);
			}
			if (super.getTranslateY() + (radius * 2) > Config.getHeight()) {
				super.setTranslateY(Config.getHeight() - (radius * 2));
			}
		}
	}
}
