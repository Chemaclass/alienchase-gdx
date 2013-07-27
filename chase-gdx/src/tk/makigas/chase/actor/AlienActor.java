package tk.makigas.chase.actor;

import tk.makigas.chase.AlienChase;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AlienActor extends Actor {
	
	private TextureRegion alien;

	public AlienActor() {
		alien = new TextureRegion(AlienChase.MANAGER.get("alien.gif",
				Texture.class), 43, 29);
		setSize(alien.getRegionWidth(), alien.getRegionHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(-300 * delta, 0);
		if(getRight() < 0)
			remove();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(alien, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
}
