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
public abstract class CuerpoActor extends Actor implements HealthActor {

	/** Imagen */
	protected TextureRegion texture;
	
	/** Stage */
	protected Stage stage;
	
	/** Vida */
	protected float health = 1;

	/** Velocidad. */
	public Vector2 velocidad = new Vector2(0, 0);
	
	public CuerpoActor(Stage stage){
		this.stage = stage;
		stage.addActor(this);
	}
	
	protected Rectangle getBounds(){
		return new Rectangle(getX(),getY(),getWidth(),getHeight());
	}
	
	/**
	 * Obtiene la vida actual de la entidad.
	 * @return vida actual.
	 */
	public float getHealth(){
		return health;		
	}
	
	/**
	 * Cambia la vida actual.
	 * @param health nuevo valor de la vida.
	 */
	public void setHealth(float health){
		this.health = health;
	}
	
	/**
	 * Suma (o resta) vida a la nave.
	 * @param sum cantidad de vida sumada o restada.
	 */
	public void sumHealth(float sum){
		health += sum;
	}
}
