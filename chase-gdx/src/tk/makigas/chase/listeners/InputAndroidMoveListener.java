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

import tk.makigas.chase.actor.NaveActor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Listener encargado del movimiento de la nave en móviles.
 * 
 * Este listener genérico controla el movimiento. Si es hacia arriba o hacia
 * abajo, esto ya se decide en el momento de llamar al constructor. Con esto
 * se evita tener que crear distintas clases para hacer la misma operación,
 * ya que los botones del pad que controlan el movimiento de la nave no dejan
 * de ser actores independientes los unos de los otros.
 * 
 * @author danirod
 */
public class InputAndroidMoveListener extends InputListener {
	
	/**
	 * Nave que va a ser controlada por el listener.
	 */
	private NaveActor nave;
	
	/**
	 * Velocidad que espera que alcance la nave cuando el pad sea pulsado.
	 */
	private float targetSpeed;
	
	/**
	 * Crea un nuevo listener.
	 * 
	 * @param nave nave que va a ser controlada por el listener
	 * @param targetSpeed velocidad que deberá llevar la nave
	 */
	public InputAndroidMoveListener(NaveActor nave, float targetSpeed) {
		this.nave = nave;
		this.targetSpeed = targetSpeed;
	}
	
	// TODO: La nave podría acelerar y decelerar en vez de parar de golpe.
	
	/** Cuando el botón se pulsa, la nave se pone en marcha. */
	@Override
	public boolean touchDown(InputEvent event, float x, float y,
			int pointer, int button) {
		nave.velocidad.y = targetSpeed;
		return true;
	}

	/** Cuando el botón se levanta, la nave deja de moverse. */
	@Override
	public void touchUp(InputEvent event, float x, float y,
			int pointer, int button) {
		nave.velocidad.y = 0;
	}
	
}
