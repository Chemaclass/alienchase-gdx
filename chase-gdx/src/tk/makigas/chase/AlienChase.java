package tk.makigas.chase;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Juego de aliens.
 * 
 * @author danirod
 */
public class AlienChase extends Game {
	
	/** Gestor de recursos usado por el juego. */
	public static final AssetManager MANAGER = new AssetManager();
	
	public SpriteBatch SB;
		
	public final AbstractScreen GAMEOVER, GAMEPLAY, LOADING, MAIN;
	
	public AlienChase() {
		GAMEOVER = new GameOverScreen(this);
		GAMEPLAY = new GameplayScreen(this);
		LOADING = new LoadingScreen(this);
		MAIN = new MainScreen(this);
	}

	@Override
	public void create() {
		SB = new SpriteBatch();
		
		// Cargamos todos los elementos externos que usará el juego.
		MANAGER.load("cargando.png", Texture.class);
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
		MANAGER.load("gameover.png", Texture.class);
		MANAGER.load("title.png", Texture.class);
		MANAGER.load("jugar.png", Texture.class);
		MANAGER.load("salir.png", Texture.class);
		
		setScreen(LOADING);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		MANAGER.dispose();
		SB.dispose();
	}
	
}
