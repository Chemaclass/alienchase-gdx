package tk.makigas.chase.listeners;

import tk.makigas.chase.actor.BulletActor;
import tk.makigas.chase.actor.NaveActor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputDesktopListener extends InputListener {
	
	private NaveActor nave;
	
	private Stage stage;
	
	public InputDesktopListener(NaveActor nave, Stage stage) {
		this.nave = nave;
		this.stage = stage;
	}
	
	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		switch(keycode) {
		case Input.Keys.UP:
			nave.velocidad.y = 250;
			return true;
		case Input.Keys.DOWN:
			nave.velocidad.y = -250;
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		switch(keycode) {
		case Input.Keys.UP:
		case Input.Keys.DOWN:
			nave.velocidad.y = 0;
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean keyTyped(InputEvent event, char character) {
		if(character != ' ')
			return false;
		
		BulletActor bullet = new BulletActor();
		bullet.setPosition(10 + nave.getWidth(), nave.getY() + nave.getHeight() / 2);
		stage.addActor(bullet);
		
		return true;
	}
}
