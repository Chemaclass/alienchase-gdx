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
 * Bala.
 * 
 * @author danirod
 * @author chema
 */
public class BulletActor extends Actor {
	
	private TextureRegion texture;
	public Rectangle bb;
	private float yv, xv;

	private BulletActor() {
		texture = new TextureRegion(AlienChase.MANAGER.get("bala.png",
				Texture.class), 16, 16);
		setSize(texture.getRegionWidth(), texture.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public BulletActor(DisparadorActor disparador, int yv) {
		this();
		setX(disparador.getX());
		setY(disparador.getY());
		this.yv = yv;
		this.xv = 0;
	}

	public BulletActor(DisparadorActor disparador, int yv, int xv) {
		this();
		setX(disparador.getX());
		setY(disparador.getY());
		this.yv = yv;
		this.xv = xv;
	}

	@Override
	public void act(float delta) {

		translate(xv * delta, yv * delta);

		bb.x = getX();
		bb.y = getY();
		bb.width = getWidth();
		bb.height = getHeight();

		// Si la bala sale por la izquierda o derecha o arriba o abajo
		if (getX() < 0 || getRight() > getStage().getWidth() || getY() < -20
				|| getTop() > getStage().getHeight()) {			
			remove();
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
}
