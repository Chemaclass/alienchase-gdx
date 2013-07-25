package tk.makigas.chase;

import tk.makigas.chase.actor.*;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameplayScreen extends AbstractScreen {
	
	private Stage stage;

	public GameplayScreen(AlienChase game) {
		super(game);
	}
	
	private NaveActor nave;
	private BulletActor bullet;
	private AlienActor alien;

	@Override
	public void show() {
		stage = new Stage();
		
		nave = new NaveActor();
		bullet = new BulletActor();
		alien = new AlienActor();
		
		nave.setPosition(10, 10);
		bullet.setPosition(150, 30);
		alien.setPosition(300, 300);
		
		stage.addActor(nave);
		stage.addActor(bullet);
		stage.addActor(alien);
	}

	@Override
	public void render(float delta) {
		stage.act();
		stage.draw();
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
