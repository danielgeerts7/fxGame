package prefabs;

import scenes.GameScene;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Enemie extends Character {

	public Enemie(int radius, Color col) {
		super(radius, col);
	}

	@Override
	protected void update() {
		Point2D player_pos = new Point2D(GameScene.getPlayer().getTranslateX(), GameScene.getPlayer().getTranslateY());
		super.lookAtPos(player_pos);
	}

}
