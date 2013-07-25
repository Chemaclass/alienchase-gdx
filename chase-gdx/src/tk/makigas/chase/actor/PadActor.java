package tk.makigas.chase.actor;

import tk.makigas.chase.AlienChase;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PadActor extends Actor {
	
	private TextureRegion button;

	public PadActor(int x, int y) {
		button = new TextureRegion(AlienChase.MANAGER.get("pad.png", Texture.class), 32 * x, 32 * y, 32, 32);
		setSize(32, 32);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(button, getX(), getY());
	}
}
