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
package tk.makigas.chase;

import java.util.ArrayList;
import java.util.List;

import tk.makigas.chase.actor.AlienActor;
import tk.makigas.chase.actor.BarraActor;
import tk.makigas.chase.actor.BulletActor;
import tk.makigas.chase.actor.EscudoActor;
import tk.makigas.chase.actor.NaveActor;
import tk.makigas.chase.actor.PadActor;
import tk.makigas.chase.actor.PuntuacionActor;
import tk.makigas.chase.listeners.InputAndroidMoveListener;
import tk.makigas.chase.listeners.InputAndroidShootListener;
import tk.makigas.chase.listeners.InputDesktopListener;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Pantalla de juego principal.
 * 
 * @author danirod
 * @author chema
 */
public class GameplayScreen extends AbstractScreen {

	/** Escenario usado por el juego. */
	private Stage stage;

	/** Nave usada por el jugador. */
	private NaveActor nave;

	/** Escudo de la Tierra. */
	private List<EscudoActor> escudos;

	/** HUD usado para mostrar la vida de la nave. */
	private BarraActor vidaNave;

	/** Pads usados para controlar el juego en Android. */
	private PadActor padArriba, padAbajo, padShoot;

	/** Puntuación */
	private PuntuacionActor puntuacion;

	/** Lista de aliens */
	private List<AlienActor> aliens;

	public GameplayScreen(AlienChase game) {
		super(game);
	}

	@Override
	public void show() {
		init(); // Inicializamos los componentes
		crearListeners(); // Preparamos los listeners
	}

	/** Inicializamos los componentes */
	private void init() {

		// Creamos un nuevo escenario y lo asociamos a la entrada.
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, true, game.SB);
		Gdx.input.setInputProcessor(stage);

		// Crear fondo.
		Image imgFondo = new Image(AlienChase.MANAGER.get("fondo.png",
				Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);

		// Creamos una nave.
		nave = new NaveActor(stage);
		nave.setPosition(stage.getWidth() / 2 - nave.getHeight() / 2, 10);
		stage.addActor(nave);

		// Creamos los HUD de las naves.
		vidaNave = new BarraActor(nave);
		vidaNave.setPosition(stage.getWidth() - 150, stage.getHeight() - 20);
		stage.addActor(vidaNave);

		// Creamos la puntuación.
		puntuacion = new PuntuacionActor(new BitmapFont());
		puntuacion.setPosition(10, stage.getHeight() - 10);
		puntuacion.puntuacion = 0;
		stage.addActor(puntuacion);

		// Creamos los escudos.
		crearEscudos();

		// Creamos los aliens.
		crearAliens();
	}

	/** Creamos los sistemas de entrada/salida */
	private void crearListeners() {
		// Creamos los sistemas de entrada. En escritorio tendremos que usar
		// un listener que lo hace todo, mientras que para Android tendremos
		// que usar tres botones asociados cada uno a algo.
		if (Gdx.app.getType() == ApplicationType.Desktop) {
			stage.setKeyboardFocus(nave); // damos foco a nave.
			nave.addListener(new InputDesktopListener(nave));
		} else if (Gdx.app.getType() == ApplicationType.Android) {
			// Creamos los pads.
			padArriba = new PadActor(0, 0);
			padAbajo = new PadActor(1, 0);
			padShoot = new PadActor(0, 1);

			// Los colocamos.
			padArriba.setPosition(10, 50);
			padAbajo.setPosition(10, 10);
			padShoot.setPosition(stage.getWidth() - 50, 10);

			// Añadimos los listeners.
			padArriba.addListener(new InputAndroidMoveListener(nave, 250f));
			padAbajo.addListener(new InputAndroidMoveListener(nave, 250f));
			padShoot.addListener(new InputAndroidShootListener(nave));

			// Los añadimos al escenario.
			stage.addActor(padArriba);
			stage.addActor(padAbajo);
			stage.addActor(padShoot);
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		// Revisamos las colisiones
		colisionesAliensNave();
		colisionesNave();
		// Revisamos si destruimos a todos los aliens
		if (aliens.size() <= 0)
			win();
		stage.draw();
	}

	/** Ganar partida */
	private void win() {

		// Creamos los aliens.
		crearAliens();
	}

	/** Colisiones de los disparos de la nave con los aliens */
	private void colisionesNave() {
		List<BulletActor> copyBullets = new ArrayList<BulletActor>(nave.getBullets());
		List<AlienActor> copyAliens = new ArrayList<AlienActor>(aliens);
		
		for (BulletActor bulletNave : nave.getBullets()) {			
			for (AlienActor alien : aliens) {				
				// Se produce una colisión entre la balaNave y el alien
				if (bulletNave.collision(alien)) {
					bulletNave.remove();
					copyBullets.remove(bulletNave);
					alien.remove();
					copyAliens.remove(alien);
					puntuacion.puntuacion++;
				}
			}
		}
		nave.setBullets(copyBullets);
		aliens = copyAliens;
	}

	/** Colisiones de los disparos de cada alien con la nave */
	private void colisionesAliensNave() {
		List<AlienActor> copyAliens = new ArrayList<AlienActor>(aliens);
		for (AlienActor alien : aliens) {
			List<BulletActor> copyBullets = new ArrayList<BulletActor>(
					alien.getBullets());
			// Para todas las balas del alien
			for (BulletActor bulletAlien : alien.getBullets()) {
				// Colisiones balaAlien con nava
				if (bulletAlien.collision(nave)) {
					bulletAlien.remove();
					copyBullets.remove(bulletAlien);
					//nave.sumHealth(-0.3f); <----------------------------------------------
					AlienChase.MANAGER.get("hit.ogg", Sound.class).play();
					if (nave.getHealth() <= 0) {
						game.setScreen(game.GAMEOVER);
					}
				}
				// Colisiones balaAlien con escudos
				else {
					List<EscudoActor> copyEscudos = new ArrayList<EscudoActor>(
							escudos);
					for (EscudoActor escudo : escudos) {
						// Colisión balaAlien con escudo
						if (bulletAlien.collision(escudo) && escudo.isVisible()) {
							bulletAlien.remove();
							copyBullets.remove(bulletAlien);
							escudo.sumHealth(-0.3f);
						}
					}
				}
			}
			alien.setBullets(copyBullets);
		}
		aliens = copyAliens;
	}

	/** Creamos los escudos */

	private void crearEscudos() {
		escudos = new ArrayList<EscudoActor>();
		int numEscudos = EscudoActor.NUM_ESCUDOS; // Creamos los aliens
		for (int i = 0; i < numEscudos; i++) {
			EscudoActor escudo = new EscudoActor();
			float x = i * 32;
			float y = 110;
			escudo.setPosition(x, y);
			escudo.bb.x = escudo.getX();
			escudo.bb.y = escudo.getY();
			stage.addActor(escudo);
			escudos.add(escudo);
		}
	}

	/** Creamos los aliens */
	private void crearAliens() {
		aliens = new ArrayList<AlienActor>();
		int colum = AlienActor.NUM_COLUM;
		int filas = AlienActor.NUM_FILAS;
		// Creamos los aliens
		for (int i = 0; i < colum; i++) {
			for (int j = 0; j < filas; j++) {
				AlienActor alien = new AlienActor(stage);
				float x = 20 + (i * 50);
				float y = stage.getHeight() - (50 + (j * 40));
				alien.setPosition(x, y);
				alien.bb.x = alien.getX();
				alien.bb.y = alien.getY();
				stage.addActor(alien);
				aliens.add(alien);
			}
		}
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		vidaNave.setPosition(stage.getWidth() - 150, stage.getHeight() - 20);
		// vidaEscudo.setPosition(stage.getWidth() - 150, stage.getHeight() -
		// 28);
		if (Gdx.app.getType() == ApplicationType.Android && padShoot != null)
			padShoot.setPosition(stage.getWidth() - 50, 10);
	}
}
