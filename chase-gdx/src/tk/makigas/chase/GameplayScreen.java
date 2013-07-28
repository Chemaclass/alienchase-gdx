package tk.makigas.chase;

import tk.makigas.chase.actor.*;
import tk.makigas.chase.listeners.InputAndroidMoveListener;
import tk.makigas.chase.listeners.InputAndroidShootListener;
import tk.makigas.chase.listeners.InputDesktopListener;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Pantalla de juego principal.
 * 
 * @author danirod
 */
public class GameplayScreen extends AbstractScreen {

	/** Escenario usado por el juego. */
	private Stage stage;
	
	/** Nave usada por el jugador. */
	private NaveActor nave;
	
	/** Escudo de la Tierra. */
	private EscudoActor escudo;
	
	/** HUDs usados para mostrar la vida de escudo y de nave. */
	private BarraActor vidaNave, vidaEscudo;
	
	/** Pads usados para controlar el juego en Android. */
	private PadActor padArriba, padAbajo, padShoot;
	
	/** Contador de tiempo usado para sincronizar algunos eventos. */
	private float timer;

	public GameplayScreen(AlienChase game) {
		super(game);
	}
	
	@Override
	public void show() {
		// Creamos un nuevo escenario y lo asociamos a la entrada.
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		// Creamos una nave.
		nave = new NaveActor();
		nave.setPosition(10, 10);
		stage.addActor(nave);

		// Creamos un escudo.
		escudo = new EscudoActor();
		escudo.setBounds(-5, 0, 5, stage.getHeight());
		stage.addActor(escudo);
		
		// Creamos los HUD de las naves.
		vidaNave = new BarraActor(nave);
		vidaEscudo = new BarraActor(escudo);		
		vidaNave.setPosition(stage.getWidth() - 150, stage.getHeight() - 20);
		vidaEscudo.setPosition(stage.getWidth() - 150, stage.getHeight() - 28);
		stage.addActor(vidaNave);
		stage.addActor(vidaEscudo);
		
		// Creamos los sistemas de entrada. En escritorio tendremos que usar
		// un listener que lo hace todo, mientras que para Android tendremos
		// que usar tres botones asociados cada uno a algo.
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			stage.setKeyboardFocus(nave); // damos foco a nave.
			nave.addListener(new InputDesktopListener(nave, stage));
		} else if(Gdx.app.getType() == ApplicationType.Android) {
			// Creamos los pads.
			padArriba = new PadActor(0, 0);
			padAbajo = new PadActor(1, 0);
			padShoot = new PadActor(0, 1);
		
			// Los colocamos.
			padArriba.setPosition(10, 50);
			padAbajo.setPosition(10, 10);
			padShoot.setPosition(stage.getWidth() - 50, 10);
		
			// Añadimos los listeners.
			padArriba.addListener(new InputAndroidMoveListener(nave, 250f));
			padAbajo.addListener(new InputAndroidMoveListener(nave, 250f));
			padShoot.addListener(new InputAndroidShootListener(stage, nave));
		
			// Los añadimos al escenario.
			stage.addActor(padArriba);
			stage.addActor(padAbajo);
			stage.addActor(padShoot);
		}
		
		// Finalmente inicializamos el contador de tiempo.
		timer = 2 + (float) Math.random();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		timer -= delta;
		if(timer < 0)		
			dispararAlien();
		stage.draw();
	}

	private void dispararAlien() {
		AlienActor alien = new AlienActor();
		alien.setPosition(stage.getWidth(), 0.1f * stage.getHeight() + 
				0.8f * stage.getHeight() * (float) Math.random());
		stage.addActor(alien);
		timer = 2 + (float) Math.random();
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
		vidaNave.setPosition(stage.getWidth() - 150, stage.getHeight() - 20);
		vidaEscudo.setPosition(stage.getWidth() - 150, stage.getHeight() - 28);
		if(Gdx.app.getType() == ApplicationType.Android && padShoot != null)
			padShoot.setPosition(stage.getWidth() - 50, 10);
	}
}
