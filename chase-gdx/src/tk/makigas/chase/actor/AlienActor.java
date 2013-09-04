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

import tk.makigas.chase.AlienChase;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * El alien es un actor que se desplaza hacia la izquierda con el objetivo
 * de colisionar contra el actor nave o contra el actor escudo. También
 * puede colisionar con el actor bala en cualquier momento.
 * 
 * @author danirod
 */
public class AlienActor extends Actor {
	
	/** Textura usada por el alien. */
	private TextureRegion alien;
	
	/** Hitbox del alien. */
	public Rectangle bb;
	
	/** Velocidad de desplazamiento del alien. */
	private static final Vector2 VELOCIDAD = new Vector2(-300, 0);
	
	public AlienActor() {
		alien = new TextureRegion(AlienChase.MANAGER.get("alien.gif",
				Texture.class), 43, 29);
		
		// necesario definir tamaños para el actor y su hitbox.
		setSize(alien.getRegionWidth(), alien.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(VELOCIDAD.x * delta, VELOCIDAD.y * delta);
		// actualiza la posición de su hitbox.
		bb.set(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(alien, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
}
