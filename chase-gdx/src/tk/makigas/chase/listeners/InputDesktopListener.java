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
package tk.makigas.chase.listeners;

import java.awt.event.KeyEvent;

import tk.makigas.chase.AlienChase;
import tk.makigas.chase.GameplayScreen;
import tk.makigas.chase.actor.NaveActor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Listener de entradas desde un Desktop.
 * 
 * @author danirod
 * @author chemaclass
 * 
 */
public class InputDesktopListener extends InputListener {

	private AlienChase game;
	private NaveActor nave;

	public InputDesktopListener(AlienChase game, NaveActor nave) {
		this.game = game;
		this.nave = nave;
	}

	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		switch (keycode) {
		case Input.Keys.UP:
			nave.velocidad.y = 250;
			return true;
		case Input.Keys.DOWN:
			nave.velocidad.y = -250;
			return true;
		case Input.Keys.LEFT:
			nave.velocidad.x = -250;
			return true;
		case Input.Keys.RIGHT:
			nave.velocidad.x = 250;
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		switch (keycode) {
		case Input.Keys.UP:
		case Input.Keys.DOWN:
			nave.velocidad.y = 0;
			return true;
		case Input.Keys.LEFT:
		case Input.Keys.RIGHT:
			nave.velocidad.x = 0;
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean keyTyped(InputEvent event, char character) {
		// Pausar
		if (GameplayScreen.getEstado() == GameplayScreen.State.RUNNING
				&& character == 'p' || character == 'P')
			GameplayScreen.pausar();
		else if (GameplayScreen.getEstado() == GameplayScreen.State.PAUSED
				&& character == 'p' || character == 'P') {
			GameplayScreen.continuar();
		}
		// Disparar
		if (character == ' ')
			nave.disparar();
		// Recargar
		if (character == 'r' || character == 'R' || character == '\r')
			nave.recargar();
		// Salir
		if (character == KeyEvent.VK_ESCAPE) {
			game.setScreen(game.MAIN);
		}
		return true;
	}

	@Override
	public boolean mouseMoved(InputEvent event, float x, float y) {
		nave.setX(nave.getX() - (nave.getWidth() / 2) + x);
		nave.setY(nave.getY() - (nave.getHeight() / 2) + y);
		return true;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		nave.disparar();
		return true;
	}

}
