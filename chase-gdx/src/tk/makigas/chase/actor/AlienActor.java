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
import tk.makigas.chase.GameplayScreen;
import static tk.makigas.chase.AlienChase.random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Alien.
 * 
 * @author danirod
 * @author chemaclass
 */
public class AlienActor extends DisparadorActor {

	/** Número de aliens que tendremos */
	public static final int NUM_COLUM = 10;
	public static final int NUM_FILAS = 3;
	
	/** Velocidad del movimiento */
	private static int movimientoX = 4;
	private static int movimientoY = 24;

	/** Número de aliens vivos */
	public static int nAliensVivos = 0;

	public AlienActor(Stage stage) {
		super(stage);
		texture = new TextureRegion(AlienChase.MANAGER.get("images/alien.gif",
				Texture.class), 43, 29);
		setSize(texture.getRegionWidth(), texture.getRegionHeight());

		velocidad.x = movimientoX;
		velocidad.y = 0;
		nAliensVivos++;
		bala = "balaAlien";
	}

	@Override
	public void act(float delta) {

		translate(velocidad.x, velocidad.y);

		if (getX() < 0) {
			velocidad.x = movimientoX;
			translate(0, -movimientoY);
		} else if (getRight() > getStage().getWidth()) {
			velocidad.x = -movimientoX;
			translate(0, -movimientoY);
		}

		if (getY() < 0) {
			translate(0, stage.getHeight());
			// GAMEOVER
			GameplayScreen.gameOver();
		}

		// De forma aleatoria disparará
		if (random(0, nAliensVivos * 15) == 4) {
			disparar();
		}
	}

	@Override
	public void disparar() {

		BulletActor bullet;
		int r = random(0, 2);
		if (r == 0)// Dirección: abajo-izquierda
			bullet = new BulletActor(stage, this, -120, -50);
		else if (r == 1) // Dirección: abajo-recto
			bullet = new BulletActor(stage, this, -120);
		else // Dirección: abajo-derecha
			bullet = new BulletActor(stage, this, -120, 50);
		
		float x = getX() - 16 + getWidth() / 2;
		float y = getY() + getHeight() - 10;
		bullet.setPosition(x, y);
		bullets.add(bullet);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}	
	
	public static void setMovimientoX(int i){
		movimientoX = i;
	}
	
	public static void setMovimientoY(int i){
		movimientoY = i;
	}
	
	public static void sumMovimientoX(int i){
		movimientoX += i;
	}

	public static int getMovimientoX() {
		return movimientoX;
	}
}
