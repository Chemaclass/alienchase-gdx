package tk.makigas.chase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LoadingScreen extends AbstractScreen {

	public LoadingScreen(AlienChase game) {
		super(game);
	}
	
	@Override
	public void render(float delta) {
		if(AlienChase.MANAGER.update()) {
			game.setScreen(game.MAIN);
		}
		
		int width, height;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		if(AlienChase.MANAGER.isLoaded("cargando.png", Texture.class)) {
			game.SB.begin();
			game.SB.draw(AlienChase.MANAGER.get("cargando.png", Texture.class), 0, 0, width, height);
			game.SB.end();
		}
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	

}
