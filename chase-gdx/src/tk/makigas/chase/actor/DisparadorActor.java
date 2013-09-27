package tk.makigas.chase.actor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * @author chema
 *
 */
public abstract class DisparadorActor extends CuerpoActor {

	protected final List<BulletActor> bullets = new LinkedList<BulletActor>();
	
	protected String bala;
	
	public DisparadorActor(Stage stage) {
		super(stage);
	}

	public abstract void disparar();

	public void limpiarBullets(){
		Iterator<BulletActor> itBullets = bullets.iterator();
		while(itBullets.hasNext()){
			BulletActor bullet = itBullets.next();
			// Eliminamos todas las balas del stage
			stage.getRoot().removeActor(bullet);
		}
		//Limpiamos todas las balas de la lista
		bullets.clear();
	}
	
	public List<BulletActor> getBullets() {
		return bullets;
	}	
	
}
