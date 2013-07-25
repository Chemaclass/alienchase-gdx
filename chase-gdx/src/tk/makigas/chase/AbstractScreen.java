package tk.makigas.chase;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {
	
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
