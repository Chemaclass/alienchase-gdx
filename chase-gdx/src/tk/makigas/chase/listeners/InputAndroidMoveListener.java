package tk.makigas.chase.listeners;

import tk.makigas.chase.actor.NaveActor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InputAndroidMoveListener extends InputListener {
	
	private NaveActor nave;
	
	private float targetSpeed;
	
	public InputAndroidMoveListener(NaveActor nave, float targetSpeed) {
		this.nave = nave;
		this.targetSpeed = targetSpeed;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y,
			int pointer, int button) {
		nave.velocidad.y = targetSpeed;
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y,
			int pointer, int button) {
		nave.velocidad.y = 0;
	}
	
}
