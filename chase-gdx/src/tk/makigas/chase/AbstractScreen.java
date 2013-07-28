package tk.makigas.chase;

import com.badlogic.gdx.Screen;

/**
 * Pantalla abstracta que podemos extender para crear pantallas nuevas.
 * 
 * @author danirod
 */
public abstract class AbstractScreen implements Screen {
	
	/** Instancia de juego en ejecución. */
	protected AlienChase game;

	public AbstractScreen(AlienChase game) {
		this.game = game;
	}
	
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}
