package tk.makigas.chase.listeners;

import java.util.List;

import tk.makigas.chase.AlienChase;
import tk.makigas.chase.actor.BulletActor;
import tk.makigas.chase.actor.NaveActor;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputAndroidShootListener extends InputListener {
	
	/** Escenario donde ocurre la acción. */
	private Stage stage;
	
	/** Nave que hay en el escenario. */
	private NaveActor nave;
	
	private List<BulletActor> bullets;
	
	public InputAndroidShootListener(Stage stage, NaveActor nave, List<BulletActor> bullets) {
		this.nave = nave;
		this.stage = stage;
		this.bullets = bullets;
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y,
			int pointer, int button) {
		BulletActor bullet = new BulletActor();
		bullet.setPosition(10 + nave.getWidth(), nave.getY() + nave.getHeight() / 2);
		stage.addActor(bullet);
		bullets.add(bullet);
		AlienChase.MANAGER.get("shoot.ogg", Sound.class).play();
		return true;
	}
}
