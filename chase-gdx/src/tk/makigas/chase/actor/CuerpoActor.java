package tk.makigas.chase.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Representa un Actor con cuerpo 
 * @author chema
 *
 */
public abstract class CuerpoActor extends Actor {

	protected TextureRegion texture;
	
	public CuerpoActor(){
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(getX(),getY(),getWidth(),getHeight());
	}
}
