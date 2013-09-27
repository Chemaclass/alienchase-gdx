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
 * Escudo terrestre.
 * 
 * @author danirod
 * @author chema
 */
public class EscudoActor extends CuerpoActor implements HealthActor {

	/** Número de escudos que tendremos inicialmente */
	public static final int NUM_ESCUDOS = 3;

	private static final int MOVIMIENTO_X = 2;

	private int xv;
	private Stage stage;
	private float health;

	public EscudoActor(Stage stage) {
		super(stage);
		stage.addActor(this);
		this.stage = stage;		
		health = 1;
		texture = new TextureRegion(AlienChase.MANAGER.get("muro.png",
				Texture.class), 0, 0, 64, 32);
		setSize(texture.getRegionWidth(), texture.getRegionHeight());		
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
		if (health > 1)
			health = 1;
		if(health < 0 ){
			stage.getRoot().removeActor(this);
			remove();
		}
	}

	private float timer = 0;

	@Override
	public void act(float delta) {

		timer += delta;
		if (timer > 1 && health < 1) {
			health += 0.03f; // Añade un porcentaje de vida al escudo.
			timer = 0;
		}

		translate(xv, 0);

		if (getX() < 0) {
			xv = MOVIMIENTO_X;

		} else if (getRight() > getStage().getWidth()) {
			xv = -MOVIMIENTO_X;
		}		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
}
