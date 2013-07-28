package tk.makigas.chase.actor;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PuntuacionActor extends Actor {
	
	public int puntuacion;
	
	private BitmapFont font;

	public PuntuacionActor(BitmapFont font) {
		this.font = font;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		font.draw(batch, "PUNTOS: " + puntuacion, getX(), getY());
	}
}
