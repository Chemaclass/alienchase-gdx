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

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Representa un Actor con cuerpo.
 * 
 * @author chemaclass
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
