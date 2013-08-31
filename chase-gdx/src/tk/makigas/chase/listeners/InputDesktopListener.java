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

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputDesktopListener extends InputListener {
	
	private NaveActor nave;
	
	private Stage stage;
	
	private List<BulletActor> bullets;
	
	public InputDesktopListener(NaveActor nave, Stage stage, List<BulletActor> bullets) {
		this.nave = nave;
		this.stage = stage;
		this.bullets = bullets;
	}
	
	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		switch(keycode) {
		case Input.Keys.UP:
			nave.velocidad.y = 250;
			return true;
		case Input.Keys.DOWN:
			nave.velocidad.y = -250;
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		switch(keycode) {
		case Input.Keys.UP:
		case Input.Keys.DOWN:
			nave.velocidad.y = 0;
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean keyTyped(InputEvent event, char character) {
		if(character != ' ')
			return false;
		
		BulletActor bullet = new BulletActor();
		bullet.setPosition(10 + nave.getWidth(), nave.getY() + nave.getHeight() / 2 - bullet.getHeight() / 2);
		stage.addActor(bullet);
		bullets.add(bullet);
		AlienChase.MANAGER.get("shoot.ogg", Sound.class).play();
		return true;
	}
}
