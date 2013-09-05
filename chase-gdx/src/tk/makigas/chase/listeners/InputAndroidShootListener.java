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

import tk.makigas.chase.AlienChase;
import tk.makigas.chase.screen.GameplayScreen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Listener encargado de disparar balas en la versión móvil del juego.
 * Este listener es usado por el actor que gestiona los disparos de bala.
 * 
 * @author danirod
 */
public class InputAndroidShootListener extends InputListener {
	
	private GameplayScreen screen;
	
	/**
	 * Crea un nuevo listener de disparos
	 * 
	 * @param stage stage en el que el listener se incluirá.
	 * @param nave nave espacial que se encargará de disparar.
	 * @param bullets lista dinámica de balas en la que las balas se añadirán
	 */
	public InputAndroidShootListener(Stage stage, GameplayScreen screen) {
		this.screen = screen;
	}
	
	/** Al pulsarse el botón, debe dispararse una bala nueva. */
	@Override
	public boolean touchDown(InputEvent event, float x, float y,
			int pointer, int button) {
		screen.spawnBullet();		
		AlienChase.MANAGER.get("shoot.ogg", Sound.class).play();
		return true;
	}
}
