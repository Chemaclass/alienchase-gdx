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
 * Nave.
 * 
 * @author danirod
 */
public class NaveActor extends Actor implements HealthActor {
	
	/** Textura de la nave. */
	private TextureRegion nave;
	
	public Rectangle bb;
	
	private float health;

	/** Velocidad que lleva la nave. */
	public Vector2 velocidad = new Vector2(0, 0);

	public NaveActor() {
		nave = new TextureRegion(AlienChase.MANAGER.get("cohete.png",
				Texture.class), 100, 79);
		setSize(nave.getRegionWidth(), nave.getRegionHeight());
		health = 1;
		bb = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void act(float delta) {
		translate(velocidad.x * delta, velocidad.y * delta);
		
		if(getX() < 0) {
			setX(0);
			velocidad.x = 0;
		} else if(getRight() > getStage().getWidth()) {
			setX(getStage().getWidth() - getWidth());
			velocidad.x = 0;
		}
		
		if(getY() < 0) {
			setY(0);
			velocidad.y = 0;
		} else if(getTop() > getStage().getHeight()) {
			setY(getStage().getHeight() - getHeight());
			velocidad.y = 0;
		}
		
		bb.x = getX();
		bb.y = getY();
		bb.width = getWidth();
		bb.height = getHeight();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(nave, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
	
	@Override
	public float getHealth() {
		return health;
	}

	@Override
	public void setHealth(float health) {
		this.health = health;
		checkHealth();
	}

	@Override
	public void sumHealth(float sum) {
		health += sum;
		checkHealth();
	}
	
	private void checkHealth() {
		if(health < 0) health = 0;
		if(health > 1) health = 1;
	}
}
