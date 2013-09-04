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

/**
 * Sistema de entrada para escritorio.
 * 
 * El escritorio utiliza este sistema para gestionar la entrada en el juego.
 * Aquí se gestionan todos los eventos: las pulsaciones de teclas para
 * mover la nave arriba y abajo y las pulsaciones de teclas para disparar
 * una bala. 
 * 
 * @author danirod
 *
 */
public class InputDesktopListener extends InputListener {
	
	/**
	 * La nave que será controlada por este sistema.
	 * Necesario para poder darle instrucciones de movimiento y para obtener
	 * su posición en el momento de disparar balas.
	 */
	private NaveActor nave;
	
	/**
	 * Escenario. Necesario para permitir que las balas hagan spawn.
	 */
	private Stage stage;
	
	/**
	 * Lista dinámica de balas. Necesario para que el sistema
	 * pueda controlar más adelante la bala cuando tenga que desaparecer.
	 */
	private List<BulletActor> bullets;
	
	/**
	 * Crea un nuevo sistema de entrada.
	 * 
	 * @param nave nave que se moverá.
	 * @param stage escenario donde se añadirá la bala.
	 * @param bullets lista dinámica de balas
	 */
	public InputDesktopListener(NaveActor nave, Stage stage, List<BulletActor> bullets) {
		this.nave = nave;
		this.stage = stage;
		this.bullets = bullets;
	}

	/**
	 * Gestiona eventos para mover la nave arriba y abajo. Es necesario
	 * hacerlos en keyDown para que la nave pueda moverse DURANTE todo
	 * el tiempo que esas teclas estén pulsadas.
	 */
	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		switch(keycode) {
		case Input.Keys.UP: // mover arriba -> velocidad positiva
			nave.velocidad.y = 250;
			return true;
		case Input.Keys.DOWN: // mover abajo -> velocidad negativa
			nave.velocidad.y = -250;
			return true;
		default:
			return false;
		}
	}

	/**
	 * Necesario para desactivar el movimiento de la nave cuando se suelten
	 * las teclas arriba y abajo. De otro modo continuaría moviéndose de
	 * forma casi infinita.
	 */
	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		switch(keycode) {
		case Input.Keys.UP:			// sólo nos interesa detenerla si se pulsa
		case Input.Keys.DOWN:		// UP y DOWN (no otras teclas como SPACE).
			nave.velocidad.y = 0;
			return true;
		default:
			return false;
		}
	}

	/**
	 * Dispara una bala cuando se pulsa la tecla ESPACIO.
	 */
	@Override
	public boolean keyTyped(InputEvent event, char character) {
		if(character != ' ')
			return false;

		// crea la bala y la añade al escenario
		BulletActor bullet = new BulletActor();
		bullet.setPosition(10 + nave.getWidth(), nave.getY() + nave.getHeight() / 2 - bullet.getHeight() / 2);
		stage.addActor(bullet);
		bullets.add(bullet);
		
		AlienChase.MANAGER.get("shoot.ogg", Sound.class).play();
		return true;
	}
}
