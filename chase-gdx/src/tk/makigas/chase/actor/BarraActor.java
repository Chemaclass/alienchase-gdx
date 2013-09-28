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

import tk.makigas.chase.AlienChase;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

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

	public BarraActor(Stage s,HealthActor entidad) {
		s.addActor(this);
		barra = AlienChase.MANAGER.get("images/vida.png", Texture.class);
		setSize(barra.getWidth(), barra.getHeight());
		this.entidad = entidad;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(barra, getX(), getY(), 0, 0,
				(int) (getWidth() * entidad.getHealth()), (int) getHeight());
	}
}
