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

	/** Lista de balas. */
	protected final List<BulletActor> bullets = new LinkedList<BulletActor>();
	
	/** Tipo de bala. Se definirá en el hijo. {balaNave,balaAlien}*/
	protected String bala;
	
	public DisparadorActor(Stage stage) {
		super(stage);
	}

	/** Disparar. */
	public abstract void disparar();

	/** Elimina todas las balas liberando así todo su espacio. */
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
	
	/** Obtener la lista de balas. */
	public List<BulletActor> getBullets() {
		return bullets;
	}	
	
}
