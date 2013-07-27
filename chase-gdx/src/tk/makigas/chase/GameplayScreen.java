package tk.makigas.chase;

import tk.makigas.chase.actor.*;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameplayScreen extends AbstractScreen {
	
	private final class InputAndroidShootListener extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			BulletActor bullet = new BulletActor();
			bullet.setPosition(10 + nave.getWidth(), nave.getY() + nave.getHeight() / 2);
			stage.addActor(bullet);
			return true;
		}
	}

	private final class InputAndroidDownListener extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			nave.velocidad.y = -250;
			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y,
				int pointer, int button) {
			nave.velocidad.y = 0;
		}
	}

	private final class InputAndroidUpListener extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			nave.velocidad.y = 250;
			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y,
				int pointer, int button) {
			nave.velocidad.y = 0;
		}
	}

	private final class InputDesktopListener extends InputListener {
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

	private Stage stage;

	public GameplayScreen(AlienChase game) {
		super(game);
	}
	
	private NaveActor nave;
	
	private PadActor padArriba, padAbajo, padShoot;

	private BarraActor vidaNave, vidaEscudo;
	
	private EscudoActor escudo;
	
	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		nave = new NaveActor();
		stage.addActor(nave);
		
		nave.setPosition(10, 10);
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			stage.setKeyboardFocus(nave);
			nave.addListener(new InputDesktopListener());
		} else if(Gdx.app.getType() == ApplicationType.Android) {
			padArriba = new PadActor(0, 0);
			padAbajo = new PadActor(1, 0);
			padShoot = new PadActor(0, 1);
		
			padArriba.setPosition(10, 50);
			padAbajo.setPosition(10, 10);
			padShoot.setPosition(stage.getWidth() - 50, 10);
		
			padArriba.addListener(new InputAndroidUpListener());
			padAbajo.addListener(new InputAndroidDownListener());
			padShoot.addListener(new InputAndroidShootListener());
		
			stage.addActor(padArriba);
			stage.addActor(padAbajo);
			stage.addActor(padShoot);
		}
		
		escudo = new EscudoActor();
		
		vidaNave = new BarraActor(nave);
		vidaEscudo = new BarraActor(escudo);		
		
		vidaNave.setPosition(stage.getWidth() - 150, 10);
		vidaEscudo.setPosition(stage.getWidth() - 150, 18);
		
		stage.addActor(vidaNave);
		stage.addActor(vidaEscudo);
		
		nave.setHealth(0.4f);
		
		timer = 2 + (float) Math.random();
	}
	
	private float timer;

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		timer -= delta;
		if(timer < 0) {			
			AlienActor alien = new AlienActor();
			alien.setPosition(stage.getWidth(), stage.getHeight() * (float) Math.random());
			stage.addActor(alien);
			
			timer = 2 + (float) Math.random();
		}
		
		stage.draw();
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		if(Gdx.app.getType() == ApplicationType.Android && padShoot != null)
			padShoot.setPosition(stage.getWidth() - 50, 10);
	}
}
