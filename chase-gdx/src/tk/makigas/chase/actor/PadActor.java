package tk.makigas.chase.actor;

import tk.makigas.chase.AlienChase;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PadActor extends Actor {
	
	/** Textura usada por el botón. */
	private TextureRegion button;

	/**
	 * Crea un nuevo pad. La textura del pad contiene una tabla con las
	 * distintas texturas que pueden usar los pads. La textura que usa
	 * este pad se indica con los parámetros.
	 * 
	 * @param x columna de la textura.
	 * @param y fila de la textura.
	 */
	public PadActor(int x, int y) {
		button = new TextureRegion(AlienChase.MANAGER.get("pad.png", Texture.class), 32 * x, 32 * y, 32, 32);
		setSize(32, 32);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(button, getX(), getY());
	}
}
