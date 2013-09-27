package tk.makigas.chase.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Representa un Actor con cuerpo 
 * @author chema
 *
 */
public abstract class CuerpoActor extends Actor {

	/** Imagen */
	protected TextureRegion texture;
	
	/** Stage */
	protected Stage stage;
	
	/** Vida */
	protected float health;

	/** Velocidad. */
	public Vector2 velocidad = new Vector2(0, 0);
	
	public CuerpoActor(Stage stage){
		this.stage = stage;
	}
	
	protected Rectangle getBounds(){
		return new Rectangle(getX(),getY(),getWidth(),getHeight());
	}
}
