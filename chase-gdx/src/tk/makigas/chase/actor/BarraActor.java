package tk.makigas.chase.actor;

import tk.makigas.chase.AlienChase;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BarraActor extends Actor {
	
	private Texture barra;
	
	private HealthActor h;

	public BarraActor(HealthActor h) {
		barra = AlienChase.MANAGER.get("vida.png", Texture.class);
		setSize(barra.getWidth(), barra.getHeight());
		this.h = h;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(barra, getX(), getY(), 0, 0, (int) (getWidth() * h.getHealth()), (int) getHeight());
	}
}
