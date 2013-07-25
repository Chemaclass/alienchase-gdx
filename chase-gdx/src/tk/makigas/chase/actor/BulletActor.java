package tk.makigas.chase.actor;

import tk.makigas.chase.AlienChase;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BulletActor extends Actor {
	
	private TextureRegion bullet;

	public BulletActor() {
		bullet = new TextureRegion(AlienChase.MANAGER.get("bala.png",
				Texture.class), 16, 16);
		setSize(bullet.getRegionWidth(), bullet.getRegionHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(300 * delta, 0);
		if(getX() > getStage().getWidth())
			remove();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(bullet, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
}
