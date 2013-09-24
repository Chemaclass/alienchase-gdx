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
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Alien.
 * 
 * @author danirod
 * @author chema
 */
public class AlienActor extends DisparadorActor {

	/* Número de aliens que tendremos*/
	public static final int NUM_COLUM = 10;
	public static final int NUM_FILAS = 2;
	
	private static final int MOVIMIENTO_X = 4;
	private static final int MOVIMIENTO_Y = 20;
	
	private int xv, yv;

	public AlienActor(Stage stage) {
		super(stage);
		texture = new TextureRegion(AlienChase.MANAGER.get("alien.gif",
				Texture.class), 43, 29);
		setSize(texture.getRegionWidth(), texture.getRegionHeight());
		bb = new Rectangle(getX(), getY(), getWidth(), getHeight());
		xv = MOVIMIENTO_X;
		yv = 0;
	}

	@Override
	public void act(float delta) {

		translate(xv, yv);

		if (getX() < 0) {
			xv = MOVIMIENTO_X;
			translate(0, -MOVIMIENTO_Y);
		} else if (getRight() > getStage().getWidth()) {
			xv = -MOVIMIENTO_X;
			translate(0, -MOVIMIENTO_Y);
		}

		if (getY() < 0) {
			translate(0, stage.getHeight());
			// GAMEOVER
		}

		bb.x = getX();
		bb.y = getY();
		bb.width = getWidth();
		bb.height = getHeight();

		// De forma aleatoria disparará
		if (AlienChase.random(0, 100) == 1)
			disparar();
	}

	@Override
	public void disparar() {
		BulletActor bullet = new BulletActor(this, -120);
		float x = getX() - 16 + getWidth() / 2;
		float y = getY() + getHeight() - 10;
		bullet.setPosition(x, y);
		stage.addActor(bullet);
		bullets.add(bullet);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
}
