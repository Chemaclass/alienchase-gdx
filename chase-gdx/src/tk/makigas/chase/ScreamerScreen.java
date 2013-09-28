package tk.makigas.chase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ScreamerScreen extends AbstractScreen {

	public ScreamerScreen(AlienChase game) {
		super(game);
	}

	private float timer;

	@Override
	public void render(float delta) {
		game.SB.begin();
		game.SB.draw(scream, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		game.SB.end();
		timer += delta;
		if (timer >= 4 && Gdx.input.isTouched()) {
			game.setScreen(game.MAIN);
		}
	}

	private Texture scream;

	@Override
	public void show() {
		timer = 0;
		AlienChase.MANAGER.get("sound/scream.wav", Sound.class).play();
		scream = AlienChase.MANAGER.get(
				"images/screamer.png", Texture.class);
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
