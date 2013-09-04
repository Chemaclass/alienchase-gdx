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
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Barra de vida usada para mostrar la vida de un elemento compatible.
 * 
 * @author danirod
 */
public class BarraActor extends Actor {
	
	/** Textura de la barra de vida. */
	private Texture barra;
	
	/** Elemento del que se hace un seguimiento. */
	private HealthActor entidad;

	/**
	 * 
	 * @param entidad entidad que tiene vida a trackear.
	 */
	public BarraActor(HealthActor entidad) {
		barra = AlienChase.MANAGER.get("vida.png", Texture.class);
		setSize(barra.getWidth(), barra.getHeight());
		this.entidad = entidad;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// La llamada a draw usada en este ejemplo es aquella que deja usar
		// los parámetros srcX, srcY, srcWidth y srcHeight. Con los parámetros
		// src* podemos indicar sólo una región a dibujar. De este modo,
		// la textura de la barra contiene la barra completa (al 100%), pero
		// nosotros sólo mostramos una porción de ella: a medida que pierda
		// vida ocultamos la parte derecha de la barra para que no se muestre
		// la barra del todo llena.
		batch.draw(barra, getX(), getY(), 0, 0,
				(int) (getWidth() * entidad.getHealth()), (int) getHeight());
	}
}
