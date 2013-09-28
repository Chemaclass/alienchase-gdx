/*
 * Alien Chase 2013 [SURVIVAL HORROR] Chema Edition 
 * -- a remake of Alien Chase 2013 Chema Edition
 * Copyright (C) 2013 José María Valera Reales <chemaclass@outlook.es> 
 * Twitter: @Chemaclass
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

import tk.makigas.chase.actor.NaveActor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
/**
 * @author danirod
 * @author chemaclass
 *
 */
public class InputAndroidMoveListener extends InputListener {

	/** Nave. */
	private NaveActor nave;

	/** Velocidad del cambio. */
	private float targetSpeed;

	/** Saber si es un movimiento horizontal */
	private boolean horizontal;

	public InputAndroidMoveListener(NaveActor nave, float targetSpeed,
			boolean horizontal) {
		this.nave = nave;
		this.targetSpeed = targetSpeed;
		this.horizontal = horizontal;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		if (horizontal)
			nave.velocidad.x = targetSpeed;
		else
			nave.velocidad.y = targetSpeed;
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		if (horizontal)
			nave.velocidad.x = 0;
		else
			nave.velocidad.y = 0;
	}

}
