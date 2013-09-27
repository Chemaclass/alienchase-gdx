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
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Bala.
 * 
 * @author danirod
 * @author chema
 */
public class BulletActor extends CuerpoActor {

	private DisparadorActor disparador;

	private BulletActor(Stage stage) {
		super(stage);
		stage.addActor(this);
		texture = new TextureRegion(AlienChase.MANAGER.get("bala.png",
				Texture.class), 16, 16);
		setSize(texture.getRegionWidth(), texture.getRegionHeight());
	}

	public BulletActor(Stage stage, DisparadorActor disparador, int yv) {
		this(stage);
		this.disparador = disparador;
		setX(disparador.getX());
		setY(disparador.getY());
		velocidad.y = yv;
		velocidad.x = 0;
	}

	public BulletActor(Stage stage, DisparadorActor disparador, int yv, int xv) {
		this(stage);
		this.disparador = disparador;
		setX(disparador.getX());
		setY(disparador.getY());
		velocidad.y = yv;
		velocidad.x = xv;
	}

	@Override
	public void act(float delta) {

		translate(velocidad.x * delta, velocidad.y * delta);

		// Si la bala sale por la izquierda o derecha o arriba o abajo
		if (getX() < 0 || getRight() > getStage().getWidth() || getY() < 0
				|| getTop() > getStage().getHeight()) {
			stage.getRoot().removeActor(this); // Eliminamos la bala del stage
			disparador.getBullets().remove(this);// Eliminamos la bala de la
													// lista de su disparador
			remove(); // eliminamos la bala
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}

	/** Devuelve verdadero si se produce una colisión entre la bala y el actor */
	public boolean collision(CuerpoActor actor) {
		return getBounds().overlaps(actor.getBounds());
	}
}
