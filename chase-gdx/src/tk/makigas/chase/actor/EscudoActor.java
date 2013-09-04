/*
 * Alien Chase 2013 -- a remake of Alien Chase 2012 (also developed by me)
 * Copyright (C) 2012, 2013 Dani Rodríguez <danirod@outlook.com>
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

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Escudo terrestre. Es una entidad invisible pero está ahí y puede colisionar
 * con los aliens. Cuando su vida alcanza el valor 0, entonces el escudo se
 * considera muerto. Esto tiene la consecuencia de que cualquier alien que
 * consiga esquivar la nave acabará la partida.
 * 
 * La vida del escudo se regenera lentamente pero de forma automática.
 * 
 * @author danirod
 */
public class EscudoActor extends Actor implements HealthActor {
	
	private float health;

	public EscudoActor() {
		health = 1;
	}

	public float getHealth() {
		return health;
	}
	
	public void setHealth(float health) {
		this.health = health;
		checkHealth();
	}
	
	public void sumHealth(float sum) {
		health += sum;
		checkHealth();
	}
	
	private void checkHealth() {
		if(health < 0) health = 0;
		if(health > 1) health = 1;
	}
	
	/** Número de segundos desde la última vez que se incrementó la vida. */
	private float timer = 0;
	
	@Override
	public void act(float delta) {
		timer += delta;
		if(timer > 1 && health < 1) { // incrementar la vida si es posible
			health += 0.01f; // Añade un porcentaje de vida a la nave.
			timer = 0;
		}
	}
}
