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

/**
 * Elemento que puede tener vida. La vida es un valor flotante comprendido
 * entre 0 y 1 que representa un porcentaje. 0 es un 0% de vida, 1 es un 100%
 * de vida y cualquier otro valor intermedio representa una vida intermedia.
 * Por ejemplo, 0.32 indica un 32% de vida.
 * 
 * @author danirod
 */
public interface HealthActor {

	/**
	 * Obtiene la vida actual de la entidad.
	 * @return vida actual.
	 */
	public float getHealth();
	
	/**
	 * Cambia la vida actual.
	 * @param health nuevo valor de la vida.
	 */
	public void setHealth(float health);
	
	/**
	 * Suma (o resta) vida a la nave.
	 * @param sum cantidad de vida sumada o restada.
	 */
	public void sumHealth(float sum);
	
}
