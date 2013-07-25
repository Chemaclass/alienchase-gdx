package tk.makigas.chase;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class AlienChase extends Game {
	
	public static final AssetManager MANAGER = new AssetManager();

	@Override
	public void create() {
		setScreen(new GameplayScreen(this));
	}
	
	
	
}
