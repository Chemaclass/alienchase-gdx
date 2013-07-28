package tk.makigas.chase.listeners;

import java.util.List;

import tk.makigas.chase.AlienChase;
import tk.makigas.chase.actor.BulletActor;
import tk.makigas.chase.actor.NaveActor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputDesktopListener extends InputListener {
	
	private NaveActor nave;
	
	private Stage stage;
	
	private List<BulletActor> bullets;
	
	public InputDesktopListener(NaveActor nave, Stage stage, List<BulletActor> bullets) {
		this.nave = nave;
		this.stage = stage;
		this.bullets = bullets;
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
		bullets.add(bullet);
		AlienChase.MANAGER.get("shoot.ogg", Sound.class).play();
		return true;
	}
}
