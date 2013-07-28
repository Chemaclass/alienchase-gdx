package tk.makigas.chase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GameOverScreen extends AbstractScreen {

	public GameOverScreen(AlienChase game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		game.SB.begin();
		game.SB.draw(gameover, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.SB.end();
		
		if(Gdx.input.isTouched()) {
			game.setScreen(game.MAIN);
		}
	}
	
	private Texture gameover;

	@Override
	public void show() {
		gameover = AlienChase.MANAGER.get("gameover.png", Texture.class);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
