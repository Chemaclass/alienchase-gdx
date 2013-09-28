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

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Nave.
 * 
 * @author danirod
 * @author chemaclass
 */
public class NaveActor extends DisparadorActor implements HealthActor {

	private TextureRegion sangre;
	
	public NaveActor(Stage stage) {
		super(stage);
		texture = new TextureRegion(AlienChase.MANAGER.get("images/nave.png",
				Texture.class), 79, 79);
		sangre = new TextureRegion(AlienChase.MANAGER.get("images/sangre.png",
				Texture.class), 225, 188);
		setSize(texture.getRegionWidth(), texture.getRegionHeight());
		health = 1;
		bala = "balaNave";
	}

	private float timer = 0;
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		timer += delta;
		if(timer > 1 && health < 1) {
			health += 0.04f; // Añade un porcentaje de vida a la nave.
			timer = 0;
		}
		
		translate(velocidad.x * delta, velocidad.y * delta);

		if (getX() < 0) {
			setX(0);
			velocidad.x = 0;
		} else if (getRight() > getStage().getWidth()) {
			setX(getStage().getWidth() - getWidth());
			velocidad.x = 0;
		}

		if (getY() < 0) {
			setY(0);
			velocidad.y = 0;
		} else if (getTop() > getStage().getHeight()) {
			setY(getStage().getHeight() - getHeight());
			velocidad.y = 0;
		}
	}

	@Override
	public void disparar() {
		BulletActor bullet = new BulletActor(stage, this, 250);
		float x = getX() - 8 + getWidth() / 2;
		float y = getY() + getHeight() - 10;
		
		bullet.setPosition(x, y);
		stage.addActor(bullet);		
		
		bullets.add(bullet);
		if (GameplayScreen.isSoundEffects())
			AlienChase.MANAGER.get("sound/shoot.ogg", Sound.class).play(0.3f);
	}	

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
		if(health <=0.4f ){
			batch.draw(sangre, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
		}
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
		if (health < 0)
			health = 0;
		if (health > 1)
			health = 1;		
	}

}
