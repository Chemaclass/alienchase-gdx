package tk.makigas.chase;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Juego de aliens.
 * 
 * @author danirod
 */
public class AlienChase extends Game {
	
	/** Gestor de recursos usado por el juego. */
	public static final AssetManager MANAGER = new AssetManager();

	@Override
	public void create() {
		// Cargamos todos los elementos externos que usará el juego.
		MANAGER.load("alien.gif", Texture.class);
		MANAGER.load("bala.png", Texture.class);
		MANAGER.load("cohete.png", Texture.class);
		MANAGER.load("defensa.png", Texture.class);
		MANAGER.load("fondo.png", Texture.class);
		MANAGER.load("pad.png", Texture.class);
		MANAGER.load("vida.png", Texture.class);
		MANAGER.load("hit.ogg", Sound.class);
		MANAGER.load("explosion.ogg", Sound.class);
		MANAGER.load("shoot.ogg", Sound.class);
		while(!MANAGER.update()) {
			// TODO: Mostrar pantalla de carga
		}
		
		// Pasamos a la pantalla principal del juego.
		// TODO: Implementar pantalla de menú inicio.
		setScreen(new GameplayScreen(this));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		MANAGER.dispose();
	}
	
}
