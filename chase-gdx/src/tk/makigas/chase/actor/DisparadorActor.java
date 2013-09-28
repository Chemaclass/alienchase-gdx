/*
 * Alien Chase 2013 Chema Edition -- a remake of Alien Chase 2013 by danirod
 * Copyright (C) 2013 Dani Rodríguez <danirod@outlook.com> Twitter: @danirod93
 * & José María Valera Reales <chemaclass@outlook.es> Twitter: @Chemaclass
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tk.makigas.chase.actor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Representa un actor disparador.
 * 
 * @author chemaclass
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
