package fxGame.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;

public abstract class SuperScene {

	public SuperScene() {
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
}
