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
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Escudo terrestre.
 * 
 * @author danirod
 * @author chema
 */
public class EscudoActor extends Actor implements HealthActor {

	/** Número de escudos que tendremos */
	public static final int NUM_ESCUDOS = 20;

	private static final int MOVIMIENTO_X = 2;
	private TextureRegion texture;
	public Rectangle bb;
	private int xv;
	private float health;

	public EscudoActor() {
		health = 1;
		texture = new TextureRegion(AlienChase.MANAGER.get("muro.png",
				Texture.class), 0, 0, 128, 32);
		setSize(texture.getRegionWidth(), texture.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(), getHeight());
		xv = MOVIMIENTO_X;
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
		if (health < 0)
			health = 0;
		if (health > 1)
			health = 1;
	}

	@Override
	public void act(float delta) {
		translate(xv, 0);

		if (getX() < 0) {
			xv = MOVIMIENTO_X;

		} else if (getRight() > getStage().getWidth()) {
			xv = -MOVIMIENTO_X;
		}

		bb.x = getX();
		bb.y = getY();
		bb.width = getWidth();
		bb.height = getHeight();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
}
