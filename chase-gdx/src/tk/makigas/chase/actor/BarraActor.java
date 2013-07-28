package tk.makigas.chase.actor;

import tk.makigas.chase.AlienChase;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Barra de vida usada para mostrar la vida de un elemento compatible.
 * 
 * @author danirod
 */
public class BarraActor extends Actor {
	
	/** Textura de la barra de vida. */
	private Texture barra;
	
	/** Elemento del que se hace un seguimiento. */
	private HealthActor entidad;

	public BarraActor(HealthActor entidad) {
		barra = AlienChase.MANAGER.get("vida.png", Texture.class);
		setSize(barra.getWidth(), barra.getHeight());
		this.entidad = entidad;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(barra, getX(), getY(), 0, 0,
				(int) (getWidth() * entidad.getHealth()), (int) getHeight());
	}
}
