package tk.makigas.chase;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AlienChase extends Game {
	
	public static final AssetManager MANAGER = new AssetManager();

	@Override
	public void create() {
		MANAGER.load("alien.gif", Texture.class);
		MANAGER.load("bala.png", Texture.class);
		MANAGER.load("cohete.png", Texture.class);
		MANAGER.load("defensa.png", Texture.class);
		MANAGER.load("fondo.png", Texture.class);
		MANAGER.load("pad.png", Texture.class);
		MANAGER.load("vida.png", Texture.class);
		while(!MANAGER.update()) {
			// TODO: Mostrar pantalla de carga
		}
		
		setScreen(new GameplayScreen(this));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		MANAGER.dispose();
	}
	
}
