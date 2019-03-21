package fxGame.scenes;

import fxGame.Main;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public abstract class SuperScene extends Pane {
	
	private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;
    Label label = null;

	public SuperScene() {
		AnimationTimer animator = new AnimationTimer()
	    {
	        @Override
	        public void handle(long now) 
	        {
	        	if (label != null) {
	        		label.requestFocus();
	        	} else {
	        		label = new Label();
	                label.setText(String.format("FPS null"));
	        	    label.setTranslateX(Main.getWidth()*0.9);
	        	}
	            update();
	            showFPS(now);
	        }
	    };
	    animator.start();
	    
	    label = new Label();
        label.setText(String.format("FPS null"));
	    label.setTranslateX(Main.getWidth()*0.9);
	    this.getChildren().add(label);
	    label.requestFocus();
	}
	
	private void showFPS(long now) {
		long oldFrameTime = frameTimes[frameTimeIndex];
        frameTimes[frameTimeIndex] = now;
        frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
        if (frameTimeIndex == 0) {
            arrayFilled = true;
        }
        if (arrayFilled) {
            long elapsedNanos = now - oldFrameTime;
            long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
            double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame;
            if (label != null) {
	            label.setText(String.format("FPS %.1f", frameRate));
	            label.requestFocus();
            }
        }
	}

	protected abstract void update();
}
