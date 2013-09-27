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

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PuntuacionActor extends Actor {

	private int nivel;

	private int puntuacion;

	private BitmapFont font;

	public PuntuacionActor(Stage s, BitmapFont font) {
		s.addActor(this);
		this.font = font;
		puntuacion = 0;
		nivel = 1;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		font.draw(batch, "NIVEL: " + nivel, getX(), getY());
		font.draw(batch, "PUNTOS: " + puntuacion, getX(), getY() - 20);
	}

	public void subirNivel() {
		nivel++;
	}
	
	public void subirPuntuacion(int sum){
		puntuacion+=sum;
	}
	
	public void subirPuntuacion(){
		puntuacion++;
	}
}
