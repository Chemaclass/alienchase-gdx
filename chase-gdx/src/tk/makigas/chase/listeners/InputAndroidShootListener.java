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
package tk.makigas.chase.listeners;

import java.util.List;

import tk.makigas.chase.AlienChase;
import tk.makigas.chase.actor.BulletActor;
import tk.makigas.chase.actor.NaveActor;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputAndroidShootListener extends InputListener {
	
	/** Escenario donde ocurre la acción. */
	private Stage stage;
	
	/** Nave que hay en el escenario. */
	private NaveActor nave;
	
	private List<BulletActor> bullets;
	
	public InputAndroidShootListener(Stage stage, NaveActor nave, List<BulletActor> bullets) {
		this.nave = nave;
		this.stage = stage;
		this.bullets = bullets;
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y,
			int pointer, int button) {
		BulletActor bullet = new BulletActor();
		bullet.setPosition(10 + nave.getWidth(), nave.getY() + nave.getHeight() / 2);
		stage.addActor(bullet);
		bullets.add(bullet);
		AlienChase.MANAGER.get("shoot.ogg", Sound.class).play();
		return true;
	}
}
