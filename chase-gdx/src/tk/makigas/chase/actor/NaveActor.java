package tk.makigas.chase.actor;

import tk.makigas.chase.AlienChase;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NaveActor extends Actor {
	
	private TextureRegion nave;
	
	public Vector2 velocidad = new Vector2(0, 0);

	public NaveActor() {
		nave = new TextureRegion(AlienChase.MANAGER.get("cohete.png",
				Texture.class), 100, 79);
		setSize(nave.getRegionWidth(), nave.getRegionHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(velocidad.x * delta, velocidad.y * delta);
		
		if(getX() < 0) {
			setX(0);
			velocidad.x = 0;
		} else if(getRight() > getStage().getWidth()) {
			setX(getStage().getWidth() - getWidth());
			velocidad.x = 0;
		}
		
		if(getY() < 0) {
			setY(0);
			velocidad.y = 0;
		} else if(getTop() > getStage().getHeight()) {
			setY(getStage().getHeight() - getHeight());
			velocidad.y = 0;
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(nave, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
}
